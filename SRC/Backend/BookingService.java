package Backend;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingService {

    // บันทึกข้อมูลการจองลง booking.csv
    public static void saveBookingToCSV(String email, String name, String room, int night, int total) 
    {
        File file = new File("data/booking.csv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) 
        {
            if (file.length() == 0) 
            {
                bw.write("Email,Name,Room,Night,Total,Date");
                bw.newLine();
            }
            String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            bw.write(email + "," + name + "," + room + "," + night + "," + total + "," + dateStr);
            bw.newLine();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
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