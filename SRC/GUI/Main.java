package GUI;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

import javax.swing.JFrame;

public class Main 
{
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("KU.Hotel.com - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Login());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    
    }
}