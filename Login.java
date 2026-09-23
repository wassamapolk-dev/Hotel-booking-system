import javax.swing.*;
import java.awt.*;

public class Login {
    public static JPanel createPanel(JFrame window)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //ทำหัวข้อ Title 
        JLabel titJLabel = new JLabel("KU.Hotel.com", SwingConstants.CENTER);
        titJLabel.setFont(new Font("Serif", Font.BOLD,28));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titJLabel,gbc);

        //Email field
        JTextField emailInput = new JTextField(20);
        emailInput.setToolTipText("@email");
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        panel.add(emailInput, gbc);

        // 3. Password Field
        JPasswordField passInput = new JPasswordField(20);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(passInput, gbc);

        // 4. Buttons
        JButton loginBtn = new JButton("LOGIN");
        JButton registerBtn = new JButton("Register");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginBtn);
        buttonPanel.add(registerBtn);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        //Events
        loginBtn.addActionListener
        (e -> 
            {
                String email = emailInput.getText().trim();
                String pass = new  String(passInput.getPassword()).trim();

                if (UserManager.checkLogin(email, pass)) 
                {
                    JOptionPane.showConfirmDialog(window, "Login Success");
                }
                else
                {
                    JOptionPane.showConfirmDialog(window, "Invalid email or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        );

        //Press the  button regis and swape to regis
        registerBtn.addActionListener
        (
            e ->
            {
                window.setContentPane(Register.createPanel(window));
                window.revalidate();
            }
        );
        return panel;
    }
}
