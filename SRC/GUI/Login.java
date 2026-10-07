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
        chkShowPassword = new JCheckBox("Show Password"); // ปุ่มไว้show password
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

        chkShowPassword.setPreferredSize(new Dimension(65, 30));
        chkShowPassword.setFocusable(false);
        chkShowPassword.addActionListener(e ->
            {
                if (chkShowPassword.isSelected()) 
                {
                    txtPassword.setEchoChar((char)0); //show password
                    chkShowPassword.setText("Hide");
                }
                else
                {
                    txtPassword.setEchoChar('.'); //Hide Password
                    chkShowPassword.setText("Show");
                }
            }
        );

        btnLogin.setText("Login");
        btnLogin.addActionListener(evt -> btnLoginActionPerformed());

        btnRegister.setText("Register");
        btnRegister.addActionListener(evt -> btnRegisterActionPerformed());

        // จัด Layout หน้าจอ
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 0, 8, 0);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // 1. Title
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 30, 0);
        add(jLabel1, gbc);

        gbc.insets = new Insets(4, 0, 4, 0);
        gbc.anchor = GridBagConstraints.WEST;

        // 2. Email
        gbc.gridy = 1;
        add(jLabel2, gbc);

        gbc.gridy = 2;
        add(txtEmail, gbc);

        // 3. Password Label
        gbc.gridy = 3;
        add(jLabel3, gbc);

        // 4. สร้าง Panel มารวม Password และ CheckBox ให้อยู่บรรทัดเดียวกัน
        JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        passPanel.setOpaque(false);
        passPanel.add(txtPassword);
        passPanel.add(chkShowPassword);

        gbc.gridy = 4;
        add(passPanel, gbc);

        // 5. ปุ่ม Login & Register
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegister);

        gbc.gridy = 5;
        gbc.insets = new Insets(30, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);
    }

    // หน้าlogin
    // หน้าlogin
    private void btnLoginActionPerformed() 
    {
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (email.isEmpty() || password.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Please fill email and password", "Alert", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 1. เช็กว่าเป็น Admin หรือไม่ (จาก admin.csv)
        if (Backend.Admin.checkAdminLogin(email, password)) 
        {
            GUI.Admin adminFrame = new GUI.Admin(email);
            adminFrame.setVisible(true);
        
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) 
            {
                window.dispose();
            }
        }
        // 2. ถ้าไม่ใช่ Admin ค่อยเช็กว่าใช่ User ทั่วไปหรือไม่ (จาก user.csv)
        else if (Backend.UserManager.checkLogin(email, password)) 
        {
            JFrame roomFrame = new JFrame("KU Hotel @Room " + email);
            roomFrame.setSize(1044, 600);
            roomFrame.setResizable(false);
            roomFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            roomFrame.setLocationRelativeTo(null);
            roomFrame.add(new Room(email));
            roomFrame.setVisible(true);
        
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) 
            {
                window.dispose();
            }
        }
        // 3. ถ้าล็อกอินไม่ผ่านทั้งคู่
        else
        {
            JOptionPane.showMessageDialog(this, "Email or Password is wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnRegisterActionPerformed() 
    {
        // เปิดหน้าRegister
        // System.out.println("Register pressed");
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof JFrame) 
        {
            JFrame mainFrame = (JFrame) window;
            mainFrame.setVisible(false);

            Register registerFrame = new Register(mainFrame);
            registerFrame.setVisible(true);

        }

    }
}