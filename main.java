import javax.swing.*;
import javax.swing.SwingUtilities;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater
        (
                () -> 
            {
                JFrame window = new JFrame("KU.Hotel.com");
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setSize(800, 500);
                window.setLocationRelativeTo(null);

                // สั่งเปิดหน้า Login
                window.setContentPane(Login.createPanel(window));
                window.setVisible(true);
            }
        );
    }
}