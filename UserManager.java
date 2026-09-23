import java.io.*;
import java.nio.file.*;
import java.util.*;

public class UserManager {
    private static final String FILE_NAME = "data/user.csv"; //บรรทัดนี้เอาไว้ให้อ่านข้อมูลในfolder data file csv

    //อันนี้เอาไว้ตรวจสอบLogin 
    public static boolean checkLogin(String email, String password)
    {
        try 
        {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
            for (int i = 1; i < lines.size(); i++) 
            {
                String[] row = lines.get(i).split(",");
                if (row.length >= 2) 
                {
                    String savedEmail = row [0].trim();
                    String savedPassword = row[1].trim();
                    
                    if (savedEmail.equalsIgnoreCase(email) && savedPassword.equalsIgnoreCase(password)) 
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
    public static boolean saveUser(String email, String password)
    {
        try 
        {
            FileWriter fw = new FileWriter(FILE_NAME,true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(email + "," + password);
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
