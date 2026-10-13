package GUI;

import Backend.UserManager;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class Register extends JFrame 
{

    private static final Logger logger = Logger.getLogger(Register.class.getName());

    private JFrame loginFrame;
    private JPanel Background;

    private JLabel titleLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel nameLabel;

    private JTextField emailField;
    private JTextField nameField;
    
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    private JButton approveButton;
    private JButton backButton;

    public Register(JFrame loginFrame) 
    {

        this.loginFrame = loginFrame; // เพื่มมาเพื่อให้ใช้เเยกหน้า login กับ register ได้

        setTitle("Register");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); // ให้หน้าต่างขึ้นตรงกลางจอ
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        addWindowListener(new java.awt.event.WindowAdapter() 
        {
            public void windowClosing(java.awt.event.WindowEvent e) 
            {
                if (loginFrame != null) 
                {
                    loginFrame.setVisible(true);
                }
            }
        });

        buildForm();
    }

    /**
     * สร้างช่องกรอกข้อมูลและปุ่มทั้งหมด
     */
    private void buildForm() 
    {

        // ใช้ GridBagLayout จัดทุกอย่างให้อยู่กลางจอ (เหมือนหน้า Login)
        Background = new JPanel(new GridBagLayout());
        Background.setBackground(new Color(13, 31, 35));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        // หัวข้อ "Register"
        titleLabel = new JLabel("Register");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 30, 0);
        Background.add(titleLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(4, 0, 4, 0);

        // ช่อง E-mail
        emailLabel = new JLabel("E-mail");
        emailLabel.setForeground(Color.white);
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        gbc.gridy = 1;
        Background.add(emailLabel, gbc);

        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(280, 30));
        gbc.gridy = 2;
        Background.add(emailField, gbc);

        // ช่อง Name
        nameLabel = new JLabel("Name");
        nameLabel.setForeground(Color.white);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        gbc.gridy = 3;
        Background.add(nameLabel, gbc);

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(280, 30));
        gbc.gridy = 4;
        Background.add(nameField, gbc);

        // ช่อง Password
        passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.white);
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        gbc.gridy = 5;
        Background.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(280, 30));
        gbc.gridy = 6;
        Background.add(passwordField, gbc);

        // ช่อง Confirm Password
        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setForeground(Color.white);
        confirmPasswordLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        gbc.gridy = 7;
        Background.add(confirmPasswordLabel, gbc);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setPreferredSize(new Dimension(280, 30));
        gbc.gridy = 8;
        Background.add(confirmPasswordField, gbc);

        // ปุ่ม APPROVE
        approveButton = new JButton("APPROVE");
        approveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        approveButton.setPreferredSize(new Dimension(105, 40));

        //ปุ่มBack
        backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setPreferredSize(new Dimension(90, 40));


        approveButton.addActionListener(e -> {
            String email = emailField.getText();
            String name = nameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (email.isEmpty() || name.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) 
            {
                JOptionPane.showMessageDialog(this, "Please fill in all the information.!!!", "Alert",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!password.equals(confirmPassword)) 
            {
                JOptionPane.showMessageDialog(this, "Passwords do not match.", "Alert PAssword",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (UserManager.isEmailExists(email)) 
            {
                JOptionPane.showMessageDialog(this, "Email is already registered", "Alert",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (UserManager.isEmailExists(name)) 
            {
                JOptionPane.showMessageDialog(this, "Name is already registered", "Alert", JOptionPane.WARNING_MESSAGE);
                return;
            }

      

            // JOptionPane.showMessageDialog(this,"Success","Success",JOptionPane.INFORMATION_MESSAGE);
            
            boolean isSaved = UserManager.saveUser(email, name, password);

            if (isSaved) 
            {
                // Clear ค่าในช่องเมื่อสมัครเสร็จ

                emailField.setText("");
                nameField.setText("");
                passwordField.setText("");
                confirmPasswordField.setText("");
                JOptionPane.showMessageDialog(this, "Register Success", "Success", JOptionPane.INFORMATION_MESSAGE);

                if (loginFrame != null) 
                {
                    loginFrame.setVisible(true);
                }
                dispose();
            } 
            else 
            {
                JOptionPane.showMessageDialog(this, "Failed to save user data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        backButton.addActionListener(e -> {
            if (loginFrame != null) loginFrame.setVisible(true);
            dispose();
        });

        // รวมปุ่มapproveButton,backButton
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(approveButton);
        buttonPanel.add(backButton);

        gbc.gridy = 9;
        gbc.insets = new Insets(30, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        Background.add(buttonPanel, gbc);

        add(Background, BorderLayout.CENTER);

    }

}