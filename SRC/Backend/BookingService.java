package Backend;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookingService 
{

    /**
     * บันทึกรายละเอียดการจองลงไฟล์ booking.csv
     * 
     * @param email อีเมลผู้ทำรายการจอง
     * @param name ชื่อผู้จอง
     * @param roomNumber หมายเลขห้องพักและประเภทห้อง
     * @param night จำนวนคืนพัก 
     * @param totalPrice ราคารวมทั้งหมด 
     * @param checkIn วันที่ Check-in
     * @param checkOut วันที่ Check-out 
     * @return void
     * @throws IOException หากการเปิดเขียนไฟล์ booking.csv ล้มเหลว
     * 
     * Pre-conditions:
     *   - ข้อมูลการจองได้รับการยืนยันและคำนวณราคารวมสำเร็จแล้ว
     * 
     * Post-conditions:
     *   - บันทึกแถวข้อมูลการจองใหม่ต่อท้ายไฟล์ booking.csv พร้อมประทับเวลาทำรายการ (BookingDate)
    */
    public static void saveBookingToCSV(String email, String name, String roomNumber, int night, int totalPrice, LocalDate checkIn, LocalDate checkOut)
    {
        File file = new File("data/booking.csv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true)))
        {
            // หากเป็นไฟล์ใหม่ ให้สร้าง Header ก่อน
            if (file.length() == 0)
            {
                bw.write("Email,Name,RoomNumber,Night,TotalPrice,BookingDate,CheckInDate,CheckOutDate");
                bw.newLine();
            }
            // บันทึกข้อมูลการจองพร้อมวันที่ทำรายการ
            String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            bw.write(email + "," + name + "," + roomNumber + "," + night + "," + totalPrice + "," + dateStr
                    + "," + checkIn + "," + checkOut);
            bw.newLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * ตรวจสอบว่าห้องพักถูกจองไปแล้วในช่วงวันที่เลือกหรือไม่
     * 
     * @param roomNumber หมายเลขห้องพักที่ต้องการเช็ก (เช่น "101")
     * @param checkIn วันที่ต้องการ Check-in
     * @param checkOut วันที่ต้องการ Check-out
     * @return boolean true ถ้าห้องถูกจองแล้ว, false หากห้องว่างสามารถจองได้
     * @throws Exception หากอ่านไฟล์หรือแปลงรูปแบบวันที่ผิดพลาด
     * 
     * Pre-conditions:
     *   - checkIn ต้องเป็นวันที่ก่อน checkOut
     * 
     * Post-conditions:
     *   - นำช่วงวันที่ไปเทียบ กับทุกแถวการจองใน booking.csv แล้วคืนค่าผลลัพธ์
    */
    public static boolean isRoomBooked(String roomNumber, LocalDate checkIn, LocalDate checkOut)
    {
        File file = new File("data/booking.csv");
        if (!file.exists()) return false;

        try
        {
            List<String> lines = Files.readAllLines(file.toPath());
            for (int i = 1; i < lines.size(); i++)
            {
                String[] d = lines.get(i).split(",");
                if (d.length < 8) continue; // ข้ามแถวที่รูปแบบไม่ถูกต้อง
                if (!d[2].trim().startsWith(roomNumber)) continue; // เช็กเฉพาะห้องที่สนใจ

                LocalDate bookedIn = LocalDate.parse(d[6].trim());
                LocalDate bookedOut = LocalDate.parse(d[7].trim());

                // เงื่อนไขตรวจสอบช่วงเวลาคาบเกี่ยวกัน (Date Overlapping Condition)
                if (bookedIn.isBefore(checkOut) && checkIn.isBefore(bookedOut)) return true; // มีคนจองแล้ว[cite: 7]
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false; // ห้องยังว่าง
    }

    /**
     * สร้างไฟล์ข้อความจำลองการส่งอีเมลยืนยันการจองไปยังผู้ใช้
     * 
     * @param email อีเมลผู้รับ
     * @param name ชื่อผู้จอง
     * @param room รายละเอียดห้องพัก
     * @param night จำนวนคืน
     * @param checkIn วันที่ Check-in
     * @param checkOut วันที่ Check-out
     * @param total ยอดเงินรวม
     * @return void
     * @throws IOException หากสร้างไดเรกทอรีหรือเขียนไฟล์ข้อความล้มเหลว
     * 
     * Pre-conditions:
     *   - การจองถูกบันทึกลงไฟล์ CSV สำเร็จแล้ว
     * 
     * Post-conditions:
     *   - สร้างไดเรกทอรี data/emails/ และสร้างไฟล์ data/emails/[email]_inbox.txt บรรจุรายละเอียดใบยืนยันการจอง
    */
    public static void sendEmailSimulation(String email, String name, String room, int night, LocalDate checkIn, LocalDate checkOut, int total)
    {
        File dir = new File("data/emails");
        if (!dir.exists()) dir.mkdirs();

        String fileName = "data/emails/" + email.replace("@", "_").replace(".", "_") + "_inbox.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true)))
        {
            writer.write("===============================================\n");
            writer.write("FROM: KU Hotel Reservation System <noreply@ku.hotel.com>\n");
            writer.write("TO: " + email + "\n");
            writer.write("DATE: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            writer.write("SUBJECT: Booking Confirmation - KU.Hotel.com\n");
            writer.write("-----------------------------------------------\n");
            writer.write("Dear " + name + ",\n\n");
            writer.write("Thank you for your booking with KU Hotel!\n");
            writer.write("Room Type : " + room + "\n");
            writer.write("Check-in  : " + checkIn + "\n");
            writer.write("Check-out : " + checkOut + "\n");
            writer.write("Nights    : " + night + "\n");
            writer.write("Total     : " + total + " THB\n\n");
            writer.write("We look forward to welcoming you.\n");
            writer.write("Best Regards,\nKU Hotel Team\n");
            writer.write("===============================================\n\n");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}