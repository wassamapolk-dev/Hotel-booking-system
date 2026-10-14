package GUI;

import java.awt.*;
import java.rmi.registry.Registry;
import java.util.concurrent.Flow;

import Backend.*;
import javax.imageio.spi.RegisterableService;
import javax.swing.*;
 
public class Login extends JPanel 
{
    private JButton btnLogin;
    private JButton btnRegister;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JToggleButton chkShowPassword;

    // Constructor หลักของหน้า Login
    public Login() 
    {
        initComponents();
    }

    private void initComponents() 
    {
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        txtEmail = new JTextField();
        jLabel3 = new JLabel();
        txtPassword = new JPasswordField();
        chkShowPassword = new JCheckBox("Show Password"); // CheckBox สำหรับเปิด/ปิดการแสดง Password
        btnLogin = new JButton();
        btnRegister = new JButton();

        setBackground(new Color(13, 31, 35));
        setPreferredSize(new Dimension(500, 500));

        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setText("KU.Hotel.com");

        jLabel2.setForeground(Color.WHITE);
        jLabel2.setText("E-mail");

        jLabel3.setForeground(Color.WHITE);
        jLabel3.setText("Password");

        txtEmail.setPreferredSize(new Dimension(250, 30));
        txtPassword.setPreferredSize(new Dimension(180, 30));

        // ตั้งค่า CheckBox สลับการซ่อน/แสดงรหัสผ่าน (Observer Pattern ผ่าน ActionListener)
        chkShowPassword.setPreferredSize(new Dimension(65, 30));
        chkShowPassword.setFocusable(false);
        chkShowPassword.addActionListener(e ->
            {
                if (chkShowPassword.isSelected()) 
                {
                    txtPassword.setEchoChar((char)0); // แสดงรหัสผ่านเป็นตัวอักษรปกติ
                    chkShowPassword.setText("Hide");
                }
                else
                {
                    txtPassword.setEchoChar('.'); // ซ่อนรหัสผ่านเป็นจุด (.)
                    chkShowPassword.setText("Show");
                }
            }
        );

        // ตั้งค่าปุ่มกดและ Listener ดักจับการกดปุ่ม
        btnLogin.addActionListener(evt -> btnLoginActionPerformed());

        btnRegister.setText("Register");
        btnRegister.addActionListener(evt -> btnRegisterActionPerformed());

        // จัด Layout หน้าจอด้วย GridBagLayoutเพื่อความยืดหยุ่นในการจัดกึ่งกลาง
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 0, 8, 0);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // เพิ่ม Title เข้า Layout
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 30, 0);
        add(jLabel1, gbc);

        gbc.insets = new Insets(4, 0, 4, 0);
        gbc.anchor = GridBagConstraints.WEST;

        // เพิ่ม Email Label และ TextField
        gbc.gridy = 1;
        add(jLabel2, gbc);

        gbc.gridy = 2;
        add(txtEmail, gbc);

        // เพิ่ม Password Label
        gbc.gridy = 3;
        add(jLabel3, gbc);

        // รวม Password Field และ CheckBox ซ่อน/แสดงรหัสผ่าน ไว้ใน Panel เดียวกันแบบ FlowLayout
        JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        passPanel.setOpaque(false);
        passPanel.add(txtPassword);
        passPanel.add(chkShowPassword);

        gbc.gridy = 4;
        add(passPanel, gbc);

        // รวมปุ่ม Login & Register ไว้ใน Panel เดียวกัน
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegister);

        gbc.gridy = 5;
        gbc.insets = new Insets(30, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);
    }

    // Method จัดการเมื่อ User กดปุ่ม Login
    private void btnLoginActionPerformed() 
    {
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword());

        // แจ้งเตือนเมื่อกรอกข้อมูลไม่ครบ
        if (email.isEmpty() || password.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Please fill email and password", "Alert", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // เช็กสิทธิ์ว่าเป็น Admin หรือไม่ผ่าน ไปเช็คที่ -> Backend.Admin
        if (Backend.Admin.checkAdminLogin(email, password)) 
        {
            GUI.Admin adminFrame = new GUI.Admin(email); // เปิดหน้า Admin Dashboard
            adminFrame.setVisible(true);
        
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) 
            {
                window.dispose(); // ปิดหน้า Login เดิม
            }
        }
        // ถ้าไม่ใช่ Admin ให้เช็กสิทธิ์ว่าเป็น User ไปเช็คที่ -> Backend.UserManager
        else if (Backend.UserManager.checkLogin(email, password)) 
        {
            JFrame roomFrame = new JFrame("KU Hotel @Room " + email);
            roomFrame.setSize(1044, 600);
            roomFrame.setResizable(false);
            roomFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            roomFrame.setLocationRelativeTo(null);
            roomFrame.add(new Room(email)); // เปิดหน้าจอเลือกห้องพัก
            roomFrame.setVisible(true);
        
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) 
            {
                window.dispose();
            }
        }
        // ถ้าล็อกอินไม่ผ่านทั้งคู่ แสดงข้อความแจ้งเตือนความผิดพลาด
        else
        {
            JOptionPane.showMessageDialog(this, "Email or Password is wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method จัดการเมื่อผู้ใช้กดปุ่ม Register เพื่อเปลี่ยนไปหน้าสมัครสมาชิก
    private void btnRegisterActionPerformed() 
    {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof JFrame) 
        {
            JFrame mainFrame = (JFrame) window;
            mainFrame.setVisible(false); // ซ่อนหน้า Login ไว้ก่อน

            Register registerFrame = new Register(mainFrame); // เปิดหน้า Register พร้อมส่ง Reference ของหน้า Login ไปด้วย
            registerFrame.setVisible(true);
        }
    }
}