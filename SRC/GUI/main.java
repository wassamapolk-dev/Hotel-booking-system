import javax.swing.JFrame;

public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("KU.Hotel.com - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Login());
        frame.pack();
        frame.setVisible(true);
        
        Register register = new Register();
        register.setVisible(true);
    }
}