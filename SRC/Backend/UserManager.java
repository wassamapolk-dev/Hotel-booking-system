package Backend;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.jar.Attributes.Name;

public class UserManager 
{
    private static final String FILE_NAME = "data/user.csv";

    //เอาไว้เช้ค การLogin ที่user ใส่ข้อมูลถูกต้องมั้ย
    public static boolean checkLogin(String email, String password)
    {
        File file = new File("data/user.csv");
        if (!file.exists()) 
        {
            return false;
        }
            try 
        {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
            for (int i = 1; i < lines.size(); i++) 
            {
                String[] row = lines.get(i).split(",");
                if (row.length >= 3) 
                {
                    String savedEmail = row [0].trim();
                    String savedName = row[1].trim();
                    String savedPassword = row[2].trim();
                    
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
                    String savedEmail = row [0].trim();         
                    if (savedEmail.equalsIgnoreCase(email)) 
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

    //อันนี้เอาไว้บันทึกข้อมูลUser
    public static boolean saveUser(String email, String name, String password)
    {
        try 
        {
            FileWriter fw = new FileWriter(FILE_NAME,true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(email + "," + name + "," + password);
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
