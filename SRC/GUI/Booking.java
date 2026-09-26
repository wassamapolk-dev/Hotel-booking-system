package GUI;

import javax.swing.*;
import java.awt.*;

public class Booking extends JFrame {

    // Components
    private JPanel panelMenu;
    private JPanel panelBooking;

    private JLabel title;
    private JLabel nameLabel;
    private JLabel roomLabel;
    private JLabel nightLabel;
    private JLabel totalLabel;

    private JTextField nameField;
    private JTextField nightField;

    private JComboBox<String> roomComboBox;

    private JButton confirmButton;
    private JButton bookingButton;
    private JButton guestButton;
    private JButton roomButton;

    public Booking() {

        // JFrame
        setTitle("KU Hotel @Booking");
        setSize(1044, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // ใช้ BorderLayout แบ่งซ้าย-ขวา
        setLayout(new BorderLayout());

        // ซ้ายMENU
        panelMenu = new JPanel();
        panelMenu.setBackground(new Color(13, 31, 35));
        panelMenu.setPreferredSize(new Dimension(270, 600));

        // ใช้ BoxLayout แทน GroupLayout
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));

        title = new JLabel("KU Hotel @Booking");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        bookingButton = new JButton("Booking");
        guestButton = new JButton("GUEST");
        roomButton = new JButton("ROOM");

        // ตั้งขนาดปุ่ม
        bookingButton.setMaximumSize(new Dimension(175, 83));
        guestButton.setMaximumSize(new Dimension(175, 83));
        roomButton.setMaximumSize(new Dimension(175, 83));

        // สีปุ่ม
        bookingButton.setBackground(new Color(105, 129, 141));
        guestButton.setBackground(new Color(105, 129, 141));
        roomButton.setBackground(new Color(105, 129, 141));

        // ขนาดฟ้อนในปุ่ม
        bookingButton.setFont(new Font("Segoe UI", Font.BOLD, 26));
        guestButton.setFont(new Font("Segoe UI", Font.BOLD, 26));
        roomButton.setFont(new Font("Segoe UI", Font.BOLD, 26));

        // สีฟ้อนในปุ่ม
        bookingButton.setForeground(Color.WHITE);
        guestButton.setForeground(Color.WHITE);
        roomButton.setForeground(Color.WHITE);

        // จัดตำแหน่ง
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        guestButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        roomButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // เพิ่มเข้า Panel
        panelMenu.add(Box.createVerticalStrut(45));
        panelMenu.add(title);

        panelMenu.add(Box.createVerticalStrut(80));
        panelMenu.add(bookingButton);

        panelMenu.add(Box.createVerticalStrut(18));
        panelMenu.add(guestButton);

        panelMenu.add(Box.createVerticalStrut(18));
        panelMenu.add(roomButton);

        // ขวา BOOKING
        panelBooking = new JPanel();
        panelBooking.setBackground(new Color(19, 46, 53));

        // ใช้ GridBagLayout
        panelBooking.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // TITLE
        JLabel bookingTitle = new JLabel("Book a Room");
        bookingTitle.setForeground(Color.WHITE);
        bookingTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);

        panelBooking.add(bookingTitle, gbc);

        // NAME
        nameLabel = new JLabel("Name :");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        nameField = new JTextField(25);
        nameField.setPreferredSize(new Dimension(279, 25));

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 10, 10, 10);

        panelBooking.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;

        panelBooking.add(nameField, gbc);

        // ROOM
        roomLabel = new JLabel("Room :");
        roomLabel.setForeground(Color.WHITE);
        roomLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        roomComboBox = new JComboBox<>();

        roomComboBox.addItem("101(Single Room) 1000 Bath");
        roomComboBox.addItem("102(Twin Room) 1500 Bath");
        roomComboBox.addItem("103(Double Room)  2000 Bath");

        roomComboBox.setPreferredSize(new Dimension(279, 25));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;

        panelBooking.add(roomLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;

        panelBooking.add(roomComboBox, gbc);

        // NIGHT
        nightLabel = new JLabel("Night :");
        nightLabel.setForeground(Color.WHITE);
        nightLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        nightField = new JTextField(25);
        nightField.setPreferredSize(new Dimension(279, 25));

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;

        panelBooking.add(nightLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;

        panelBooking.add(nightField, gbc);

        // TOTAL
        totalLabel = new JLabel("Total 0 Baht");
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 20, 10, 10);

        panelBooking.add(totalLabel, gbc);

        // CONFIRM BUTTON
        confirmButton = new JButton("Confirm Booking");

        confirmButton.setBackground(new Color(105, 129, 141));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        confirmButton.setPreferredSize(new Dimension(180, 35));

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(25, 10, 10, 10);

        panelBooking.add(confirmButton, gbc);

        add(panelMenu, BorderLayout.WEST);
        add(panelBooking, BorderLayout.CENTER);

        // BUTTON EVENTS
        confirmButton.addActionListener(e -> confirmBooking());

       
    }

    // CONFIRM BOOKING
    private void confirmBooking() {

        String name = nameField.getText();
        String room = roomComboBox.getSelectedItem().toString();
        String nightText = nightField.getText();

        if (name.isEmpty() || nightText.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please fill in all information.");
            return;
        }

        try {
            int night = Integer.parseInt(nightText);

            int price = 0;

            if (room.startsWith("101")) {
                price = 1000;
            } else if (room.startsWith("102")) {
                price = 1500;
            } else if (room.startsWith("103")) {
                price = 2000;
            }

            int total = price * night;

            totalLabel.setText("Total " + total + " Baht");

            JOptionPane.showMessageDialog(this,"Booking Successful!\n"
                            + "Name: " + name + "\n"
                            + "Room: " + room + "\n"
                            + "Night: " + night + "\n"
                            + "Total: " + total + " Baht");

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,"Night must be a number.");
        }
    }

}