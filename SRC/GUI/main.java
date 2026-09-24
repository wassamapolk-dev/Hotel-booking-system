import java.awt.*;
import javax.swing.*;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> {
                    JFrame window = new JFrame("KU.Hotel.com");
                    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    window.setSize(800, 500);
                    window.setLocationRelativeTo(null);

                    // สั่งเปิดหน้า Login
                    showLogin(window);
                    window.setVisible(true);
                }

        );

    }
    
    // เปิดหน้า Login พร้อม Background
    public static void showLogin(JFrame window) {

        JPanel BackGroud = new JPanel(new GridBagLayout());
        BackGroud.setBackground(new Color(10, 55, 85));
        BackGroud.add(Login.createPanel(window));
        
        window.setContentPane(BackGroud);

        window.revalidate();
        window.repaint();
    }
}