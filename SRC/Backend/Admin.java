package Backend;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Admin {

    // เช็กล็อกอิน Admin
    public static boolean checkAdminLogin(String email, String password)
    {
        File file = new File("data/admin.csv");
        if (!file.exists()) return false;

        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            boolean isFirstLine = true;
            for (String line = br.readLine(); line != null; line = br.readLine()) 
            {
                if (isFirstLine) 
                {
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(",");
                if (data.length >= 2) 
                {
                    String adminEmail = data[0].trim();
                    String adminPassword = data[1].trim();

                    if (adminEmail.equalsIgnoreCase(email.trim()) && adminPassword.equalsIgnoreCase(password)) 
                    {
                        return true;
                    }
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        return false;
    }

    // 1. อ่านข้อมูลการจองทั้งหมดคืนค่าเป็น List<String[]>
    public static List<String[]> loadBookingData() throws IOException
    {
        File file = new File("data/booking.csv");
        List<String[]> bookingList = new ArrayList<>();

        if (!file.exists()) 
        {
            return bookingList;
        }

        List<String> lines = Files.readAllLines(file.toPath());

        for (int i = 0; i < lines.size(); i++) 
        {
            String line = lines.get(i).trim();
            if (line.isEmpty()) continue;

            String[] data = line.split(",");
            if (data.length >= 6) 
            {
                // ข้าม Header แถวแรกถ้าเป็นคำว่า Email
                if (i == 0 && data[0].equalsIgnoreCase("Email")) {
                    continue;
                }
                bookingList.add(data);
            }
        }

        return bookingList;
    }

    // 2. ลบข้อมูลตาม Index แถวที่เลือกในตาราง
    public static boolean deleteBookingRow(int selectedRow) throws IOException
    {
        File file = new File("data/booking.csv");
        if (!file.exists()) return false;

        List<String> lines = Files.readAllLines(file.toPath());
        List<String> updatedLines = new ArrayList<>();

        boolean hasHeader = false;
        if (!lines.isEmpty() && lines.get(0).toLowerCase().startsWith("email")) {
            hasHeader = true;
        }

        // คำนวณตำแหน่งบรรทัดในไฟล์จริง
        int targetLineIndex = hasHeader ? (selectedRow + 1) : selectedRow;

        for (int i = 0; i < lines.size(); i++) 
        {
            if (i == targetLineIndex) 
            {
                continue; // ข้ามบรรทัดที่ต้องการลบ
            }
            updatedLines.add(lines.get(i));
        }

        // เขียนบันทึกไฟล์ใหม่
        Files.write(file.toPath(), updatedLines);
        return true;
    }
}