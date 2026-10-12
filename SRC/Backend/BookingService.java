package Backend;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookingService {

    // บันทึกข้อมูลการจองลง booking.csv (เพิ่ม CheckIn, CheckOut ท้ายแถว)
    public static void saveBookingToCSV(String email, String name, String room, int night, int total,
                                        LocalDate checkIn, LocalDate checkOut)
    {
        File file = new File("data/booking.csv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true)))
        {
            if (file.length() == 0)
            {
                bw.write("Email,Name,Room,Night,Total,Date,CheckIn,CheckOut");
                bw.newLine();
            }
            String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            bw.write(email + "," + name + "," + room + "," + night + "," + total + "," + dateStr
                    + "," + checkIn + "," + checkOut);
            bw.newLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // เช็กว่าห้องถูกจองในช่วงวันที่เลือกหรือไม่
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
                if (d.length < 8) continue;                        // แถวเก่าที่ไม่มีวัน check-in/out
                if (!d[2].trim().startsWith(roomNumber)) continue; // เช่น "101(Single Room) 1000 Bath"

                LocalDate bookedIn = LocalDate.parse(d[6].trim());
                LocalDate bookedOut = LocalDate.parse(d[7].trim());

                if (bookedIn.isBefore(checkOut) && checkIn.isBefore(bookedOut)) return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    // จำลองการส่งอีเมล (สร้างไฟล์ Receipt/Inbox)
    public static void sendEmailSimulation(String email, String name, String room, int night, int total)
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