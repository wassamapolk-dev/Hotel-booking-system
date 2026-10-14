package GUI;

import Backend.UserManager;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class Register extends JFrame 
{
    private static final Logger logger = Logger.getLogger(Register.class.getName());

    private JFrame loginFrame; // อ้างอิงหน้า Login เพื่อส่งกลับเมื่อสมัครเสร็จหรือยกเลิก
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


    /**
     * Constructor สร้างหน้าต่างสมัครสมาชิก
     * 
     * @param loginFrame JFrameของหน้า Login
     * @return void
     * @throws HeadlessException หากระบบไม่รองรับการแสดงผลหน้าต่าง
     * 
     * Pre-conditions:
     *   - loginFrame ถูกส่งมาจากหน้า Login เพื่อเก็บเป็น Reference ในการสลับหน้าต่างกลับ
     * 
     * Post-conditions:
     *   - หน้าต่าง Register ถูกสร้างขึ้นเต็มจอ/กึ่งกลาง พร้อมผูก WindowListener ดักจับการปิดหน้าต่าง
    */
    public Register(JFrame loginFrame) 
    {
        this.loginFrame = loginFrame;

        setTitle("Register");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); // จัดตำแหน่งกึ่งกลางหน้าจอ
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // เพิ่ม WindowListener เพื่อแสดงหน้า Login อีกครั้งหากผู้ใช้กดปิดหน้าต่าง Register
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

        buildForm(); // เรียกใช้ Method สร้างฟอร์ม
    }

    /**
     * //Method สำหรับสร้างช่องกรอกข้อมูลและปุ่มดำเนินการทั้งหมด ตรวจสอบข้อมูลผู้ใช้และส่งบันทึกลงระบบ
     * 
     * @param ไม่มี
     * @return void
     * @throws JOptionPaneException แสดงแจ้งเตือนหาก Input ผิดเงื่อนไข หรือการบันทึกไฟล์ล้มเหลว
     * 
     * Pre-conditions:
     *   - ช่องกรอก emailField, nameField, passwordField และ confirmPasswordField มีการป้อนข้อมูล
     * 
     * Post-conditions:
     *   - หากข้อมูลถูกต้อง (ไม่ว่าง, Password ตรงกัน, Email และ Name ไม่ซ้ำ): บันทึกผู้ใช้ลง user.csv, เคลียร์ช่องข้อมูล, แสดง Pop-up Success, ปิดหน้า Register และสั่งแสดงหน้า loginFrame อีกครั้ง
     *   - หากข้อมูลผิดพลาด: ไม่มีการบันทึกไฟล์ และแสดง Popup เตือนให้ผู้ใช้แก้ไข
    */
    private void buildForm() 
    {
        // จัด Layout หน้าจอด้วย GridBagLayout เพื่อให้อยู่กึ่งกลางหน้าจอ
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

        // ช่องกรอก E-mail
        emailLabel = new JLabel("E-mail");
        emailLabel.setForeground(Color.white);
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        gbc.gridy = 1;
        Background.add(emailLabel, gbc);

        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(280, 30));
        gbc.gridy = 2;
        Background.add(emailField, gbc);

        // ช่องกรอก Name
        nameLabel = new JLabel("Name");
        nameLabel.setForeground(Color.white);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        gbc.gridy = 3;
        Background.add(nameLabel, gbc);

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(280, 30));
        gbc.gridy = 4;
        Background.add(nameField, gbc);

        // ช่องกรอก Password
        passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.white);
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        gbc.gridy = 5;
        Background.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(280, 30));
        gbc.gridy = 6;
        Background.add(passwordField, gbc);

        // ช่องกรอก Confirm Password
        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setForeground(Color.white);
        confirmPasswordLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        gbc.gridy = 7;
        Background.add(confirmPasswordLabel, gbc);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setPreferredSize(new Dimension(280, 30));
        gbc.gridy = 8;
        Background.add(confirmPasswordField, gbc);

        // สร้างปุ่มยืนยันและปุ่มย้อนกลับ
        approveButton = new JButton("APPROVE");
        approveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        approveButton.setPreferredSize(new Dimension(105, 40));

        backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setPreferredSize(new Dimension(90, 40));

        // Listener จัดการเหตุการณ์เมื่อกดปุ่ม APPROVE
        approveButton.addActionListener(e -> 
        {
            String email = emailField.getText();
            String name = nameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            // ตรวจสอบว่ากรอกข้อมูลครบทุกช่องหรือไม่
            if (email.isEmpty() || name.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) 
            {
                JOptionPane.showMessageDialog(this, "Please fill in all the information.!!!", "Alert",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            // ตรวจสอบว่า Password ทั้งสองช่องตรงกันหรือไม่
            if (!password.equals(confirmPassword)) 
            {
                JOptionPane.showMessageDialog(this, "Passwords do not match.", "Alert PAssword",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            // ตรวจสอบว่า Email ถูกใช้ไปแล้วหรือยัง
            if (UserManager.isEmailExists(email)) 
            {
                JOptionPane.showMessageDialog(this, "Email is already registered", "Alert",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            // ตรวจสอบว่า Name ถูกใช้ไปแล้วหรือยัง
            if (UserManager.isEmailExists(name)) 
            {
                JOptionPane.showMessageDialog(this, "Name is already registered", "Alert", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // บันทึกข้อมูลสมาชิกลงฐานข้อมูล CSV ผ่าน Backend.UserManager
            boolean isSaved = UserManager.saveUser(email, name, password);

            if (isSaved) 
            {
                // เคลียร์ค่าในช่องกรอกเมื่อลงทะเบียนสำเร็จ
                emailField.setText("");
                nameField.setText("");
                passwordField.setText("");
                confirmPasswordField.setText("");
                JOptionPane.showMessageDialog(this, "Register Success", "Success", JOptionPane.INFORMATION_MESSAGE);

                // สลับกลับไปหน้า Login และปิดหน้า Register
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

        // Listener สำหรับปุ่ม ย้อนกลับ (Back)
        backButton.addActionListener(e -> 
        {
            if (loginFrame != null) loginFrame.setVisible(true);
            dispose();
        });

        // รวมปุ่ม APPROVE และ Back ไว้ใน Panel เดียวกัน
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