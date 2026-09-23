import javax.swing.*;
import java.awt.*;

public class Register {
    public static JPanel createPanel(JFrame window)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Title
        JLabel titLabel = new JLabel("Register - KU.Hotel.com",SwingConstants.CENTER);
        titLabel.setFont(new Font("Serif", Font.BOLD,24));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titLabel,gbc);

        //input
        JTextField emailInput = new JTextField(20);
        JPasswordField passInput = new JPasswordField(20);
        JPasswordField conFirmPassInput = new JPasswordField(20);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        panel.add(new JLabel("Email: "), gbc);
        gbc.gridy = 2;
        panel.add(emailInput, gbc);

        gbc.gridy = 3;
        panel.add(new JLabel("Password: "), gbc);
        gbc.gridy = 4;
        panel.add(passInput, gbc);

        gbc.gridy = 5;
        panel.add(new JLabel("Comfirm Password: "), gbc);
        gbc.gridy = 6;
        panel.add(conFirmPassInput, gbc);

        //Button
        JButton YesBtn = new JButton("Yes");
        JButton BackBtn = new JButton("Back");

        JPanel buttonpanel = new JPanel();
        buttonpanel.add(YesBtn);
        buttonpanel.add(BackBtn);

        gbc.gridy = 7;
        panel.add(buttonpanel, gbc);

        //Event
        YesBtn.addActionListener
        (
            e -> 
            {
                String email = emailInput.getText().trim();
                String pass = new String(passInput.getPassword()).trim();
                String conFirm = new String(conFirmPassInput.getPassword()).trim();
                if (email.isEmpty() || pass.isEmpty()) 
                {
                    JOptionPane.showConfirmDialog(window, "Please enter the Email or Password", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if (!pass.equals(conFirm)) 
                {
                    JOptionPane.showConfirmDialog(window, "Password not correct", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (UserManager.isEmailExists(email)) 
                {
                    JOptionPane.showConfirmDialog(window, "This Email is already used", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    if (UserManager.saveUser(email, pass)) 
                    {
                        JOptionPane.showConfirmDialog(window, "Complete Regis");
                        //regis เสร็จกลับไปหน้าlogin
                        window.setContentPane(Login.createPanel(window));
                        window.revalidate();
                    }
                }
            }
        );
        BackBtn.addActionListener
        (
            e ->
            {
                window.setContentPane(Login.createPanel(window));
                window.revalidate();
            }
        );

        return panel;
    }
}
