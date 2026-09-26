package GUI;

import java.awt.*;
import java.rmi.registry.Registry;
import Backend.*;
import javax.imageio.spi.RegisterableService;
import javax.swing.*;
 
public class Login extends JPanel {
 
    private JButton btnLogin;
    private JButton btnRegister;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
 
    public Login() {
        initComponents();
    }
 
    private void initComponents() {
 
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        txtEmail = new JTextField();
        jLabel3 = new JLabel();
        txtPassword = new JPasswordField();
        btnLogin = new JButton();
        btnRegister = new JButton();
 
        setBackground(new Color(0, 51, 102));
        setPreferredSize(new Dimension(500, 500));
        
 
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setText("KU.Hotel.com");
 
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setText("E-mail");
 
        jLabel3.setForeground(Color.WHITE);
        jLabel3.setText("Password");
 
        txtEmail.setPreferredSize(new Dimension(250, 30));
        txtPassword.setPreferredSize(new Dimension(250, 30));
 
        btnLogin.setText("Login");
        btnLogin.addActionListener(evt -> btnLoginActionPerformed());
 
        btnRegister.setText("Register");
        btnRegister.addActionListener(evt -> btnRegisterActionPerformed());
 
        //เปลี่ยน Layout ใหม่
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 0, 8, 0);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 30, 0);
        add(jLabel1, gbc);
 
        gbc.insets = new Insets(4, 0, 4, 0);
        gbc.anchor = GridBagConstraints.WEST;
 
        gbc.gridy = 1;
        add(jLabel2, gbc);
 
        gbc.gridy = 2;
        add(txtEmail, gbc);
 
        gbc.gridy = 3;
        add(jLabel3, gbc);
 
        gbc.gridy = 4;
        add(txtPassword, gbc);
 
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegister);
 
        gbc.gridy = 5;
        gbc.insets = new Insets(30, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);
    }
    //หน้าlogin
    private void btnLoginActionPerformed() {
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword());
 
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill email and password","Alert",JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        //เชื่อมกับข้อมูล/ระบบตรวจสอบ
        if (Backend.UserManager.checkLogin(email, password)) {
            JOptionPane.showMessageDialog(this, "Login success","success", JOptionPane.INFORMATION_MESSAGE);
        } 
        else 
        {
            JOptionPane.showMessageDialog(this, "Email or Password is wrong","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
 
    private void btnRegisterActionPerformed() {
        //เปิดหน้าRegister
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