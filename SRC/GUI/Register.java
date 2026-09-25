package GUI;

import Backend.UserManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.StructuredTaskScope.FailedException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Register extends JFrame 
{

    private static final Logger logger = Logger.getLogger(Register.class.getName());

    // ประกาศตัวแปรของแต่ละช่องกรอกข้อมูล (ตั้งชื่อให้สื่อความหมาย)
    private JPanel Background;
    private JLabel titleLabel;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel confirmPasswordLabel;
    private JPasswordField confirmPasswordField;
    private JButton approveButton;

    public Register() 
    {
        // ตั้งค่าพื้นฐานของหน้าต่าง
        setTitle("Register");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); // ให้หน้าต่างขึ้นตรงกลางจอ


        // ใช้ layout แบบ null คือเราจะกำหนดตำแหน่ง (x, y, กว้าง, สูง) ของทุกอย่างเอง
        setLayout(null);

        buildForm();
    }

    /**
     * สร้างช่องกรอกข้อมูลและปุ่มทั้งหมด แล้ววางตำแหน่งลงบนหน้าต่าง
     */
    private void buildForm() 
    {
        
        // หัวข้อ "Register"
        titleLabel = new JLabel("Register");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setBounds(180, 30, 200, 30);
        add(titleLabel);

        // ช่อง E-mail
        emailLabel = new JLabel("E-mail");
        emailLabel.setForeground(Color.white);
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        emailLabel.setBounds(100, 90, 100, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(100, 115, 280, 30);
        add(emailField);

        // ช่อง Name
        nameLabel = new JLabel("Name");
        nameLabel.setForeground(Color.white);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        nameLabel.setBounds(100, 155, 100, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 180, 280, 30);
        add(nameField);

        // ช่อง Password
        passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.white);
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        passwordLabel.setBounds(100, 220, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 245, 280, 30);
        add(passwordField);

        // ช่อง Confirm Password
        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setForeground(Color.white);
        confirmPasswordLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        confirmPasswordLabel.setBounds(100, 285, 150, 25);
        add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(100, 310, 280, 30);
        add(confirmPasswordField);

        // ปุ่ม APPROVE
        approveButton = new JButton("APPROVE");
        approveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        approveButton.setBounds(160, 370, 160, 40);

        approveButton.addActionListener
        (e -> 
            {
                String email = emailField.getText();
                String name = nameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String( confirmPasswordField.getPassword());

                if(email.isEmpty()||name.isEmpty()|| password.isEmpty()|| confirmPassword.isEmpty())
                {
                    JOptionPane.showMessageDialog(this, "Please fill in all the information.!!!","Alert",JOptionPane.WARNING_MESSAGE);
                    return ;
                }
                if(!password.equals(confirmPassword))
                {
                    JOptionPane.showMessageDialog(this,"Passwords do not match.","Alert PAssword",JOptionPane.WARNING_MESSAGE);
                    return ;
                }
                if (UserManager.isEmailExists(email)) 
                {
                    JOptionPane.showConfirmDialog(this, "Email is already registered", "Alert", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (UserManager.isEmailExists(name)) 
                {
                    JOptionPane.showConfirmDialog(this, "Name is already registered", "Alert", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(this,"Success","Success",JOptionPane.INFORMATION_MESSAGE);
            
            
                boolean isSaved = UserManager.saveUser(email, name, password);

                if (isSaved) 
                {
                    //Clear ค่าในช่องเมื่อสมัครเสร็จ

                    emailField.setText("");
                    nameField.setText("");
                    passwordField.setText("");
                    confirmPasswordField.setText("");
                }
                else
                {
                    JOptionPane.showConfirmDialog(this, "Failed to save user data.","Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        );

        add(approveButton);

        Background = new JPanel();
        Background.setBackground(new Color(0, 51, 102));
        Background.setBounds(0, 0, 500, 500);
        Background.setLayout(null);
        add(Background);
       
    }

}