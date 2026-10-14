package Backend;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Admin 
{

    /**
     * เช็กล็อกอิน Admin
     * 
     * @param email อีเมล Admin ที่ป้อนเข้ามา
     * @param password รหัสผ่าน Admin ที่ป้อนเข้ามา
     * @return boolean true หากเข้าสู่ระบบสำเร็จ, false หากไม่ผ่านสิทธิ์หรือไฟล์ไม่พบ
     * @throws Exception หากอ่านไฟล์ admin.csv ผิดพลาด
     * 
     * Pre-conditions:
     *   - มีไฟล์ admin.csv จัดเก็บอยู่ในระบบ
     * 
     * Post-conditions:
     *   - คืนค่าการอนุมัติสิทธิ์แก่ Admin โดยไม่มีการแก้ไขไฟล์
    */
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

    /**
     * อ่านประวัติการจองทั้งหมดจากไฟล์ CSV คืนค่าเป็นโครงสร้างข้อมูลสำหรับตาราง
     * 
     * @param ไม่มี
     * @return List<String[]> List of Array ข้อความ แต่ละ Element แทนแถวข้อมูลในตาราง
     * @throws IOException หากไม่สามารถอ่านไฟล์ booking.csv ได้
     * 
     * Pre-conditions:
     *   - ไฟล์ booking.csv มีอยู่จริงในระบบ
     * 
     * Post-conditions:
     *   - อ่านข้อมูลทุกแถว ยกเว้น Header แล้วแปลงเป็น List<String[]> คืนค่ากลับไปวาดบน JTable
    */
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
                if (i == 0 && data[0].equalsIgnoreCase("Email")) 
                {
                    continue;
                }
                bookingList.add(data);
            }
        }

        return bookingList;
    }

    /**
     * ลบข้อมูลตาม Index แถวที่เลือกในตาราง
     * 
     * @param selectedRow ดัชนีตำแหน่งแถวที่ต้องการลบ (นับเริ่มจาก 0)
     * @return boolean true หากลบสำเร็จ, false หากเกิดข้อผิดพลาด
     * @throws IOException ถ้าการเขียนทับไฟล์ CSV ล้มเหลว
     * 
     * Pre-conditions:
     *   - selectedRow อยู่ในช่วงจำนวนแถวที่มีจริงในไฟล์
     * 
     * Post-conditions:
     *   - แถวข้อมูลในลำดับselectedRow ถูกลบออกจากไฟล์ booking.csv และทำการบันทึกไฟล์ใหม่
    */
    public static boolean deleteBookingRow(int selectedRow) throws IOException
    {
        File file = new File("data/booking.csv");
        if (!file.exists()) return false;

        List<String> lines = Files.readAllLines(file.toPath());
        List<String> updatedLines = new ArrayList<>();

        boolean hasHeader = false;
        if (!lines.isEmpty() && lines.get(0).toLowerCase().startsWith("email")) 
        {
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