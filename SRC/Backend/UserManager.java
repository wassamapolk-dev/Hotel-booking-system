package Backend;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class UserManager 
{
    private static final String FILE_NAME = "data/user.csv"; 

    // ตรวจสอบการเข้าสู่ระบบว่า Email/Password ตรงกับในฐานข้อมูลCSVมั้ย
    public static boolean checkLogin(String email, String password)
    {
        File file = new File(FILE_NAME);
        if (!file.exists()) return false; // ถ้าไม่มีไฟล์ข้อมูลให้คืนค่า false

        try
        {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME)); // อ่านข้อมูลทุกบรรทัดจากไฟล์ user.csv
            for (int i = 1; i < lines.size(); i++) // ข้ามบรรทัด Header (i=0) บรรทัดเเรกสุดของ user.csv
            {
                String[] row = lines.get(i).split(","); // แยกข้อมูลด้วยเครื่องหมาย ,
                if (row.length >= 3) 
                {
                    String savedEmail = row[0].trim();
                    String savedPassword = row[2].trim();
                    
                    // เปรียบเทียบ Email (ไม่สนตัวพิมพ์เล็ก/ใหญ่) และ Password
                    if(savedEmail.equalsIgnoreCase(email) && savedPassword.equalsIgnoreCase(password))
                    {
                        return true; 
                    }
                }    
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return false; 
    }

    // ตรวจสอบว่า Email นี้มีในระบบอยู่แล้วหรือไม่
    public static boolean isEmailExists(String email)
    {
        try 
        {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
            for (int i = 1; i < lines.size(); i++) 
            {
                String[] row = lines.get(i).split(",");
                if (row.length >= 1) 
                {
                    if (row[0].trim().equalsIgnoreCase(email)) 
                    {
                        return true; 
                    }
                }    
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return false; 
    }

    // บันทึกข้อมูลสมาชิกใหม่ลงไฟล์ user.csv 
    public static boolean saveUser(String email, String name, String password)
    {
        File file = new File("data/user.csv");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true)))
        {
             // หากเป็นไฟล์ใหม่ ให้สร้าง Header ก่อน
            if (file.length() == 0)
            {
                bw.write("Email, Name, Password");
                bw.newLine();
            }

            bw.write(email + "," + name + "," + password); // เขียนข้อมูลคั่นด้วย ,
            bw.newLine();
            bw.close();

            return true;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
    }
}