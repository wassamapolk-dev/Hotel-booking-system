import javax.swing.*;
import java.awt.*;

public class Guest {
    public static JPanel createPanel(JFrame window, String email) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(10, 55, 85));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // หัวข้อ 
        JLabel titLabel = new JLabel("KU.Hotel.com - Guest", SwingConstants.CENTER);
        titLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titLabel.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titLabel, gbc);

        // ข้อความ Welcome
        JLabel welcomeLabel = new JLabel("Welcome, " + email, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        welcomeLabel.setForeground(Color.white);
        gbc.gridy = 1;
        panel.add(welcomeLabel, gbc);

        // รายการห้อง *ตัวอย่าง
        String[] rooms = {"101 - Standard - 500 บาท/คืน", "102 - Standard - 500 บาท/คืน",
                "201 - Deluxe - 900 บาท/คืน", "301 - Suite - 1500 บาท/คืน"};
        JList<String> roomList = new JList<>(rooms);
        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomList.setFont(new Font("Tahoma", Font.PLAIN, 14)); 
        roomList.setVisibleRowCount(4);                       
        gbc.gridy = 2;
        panel.add(roomList, gbc); 

        // เช็คอิน / เช็คเอ้าท์
        JLabel checkInLabel = new JLabel("Check-in ():");
        checkInLabel.setForeground(Color.WHITE);
        gbc.gridy = 3;
        panel.add(checkInLabel, gbc);

        JTextField checkInInput = new JTextField(20);
        gbc.gridy = 4;
        panel.add(checkInInput, gbc);

        JLabel checkOutLabel = new JLabel("Check-out ():");
        checkOutLabel.setForeground(Color.WHITE);
        gbc.gridy = 5;
        panel.add(checkOutLabel, gbc);

        JTextField checkOutInput = new JTextField(20);
        gbc.gridy = 6;
        panel.add(checkOutInput, gbc);

        // ปุ่ม
        JButton bookBtn = new JButton("Make Booking");
        JButton myBookingBtn = new JButton("View My Booking");
        JButton logoutBtn = new JButton("Logout");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(bookBtn);
        buttonPanel.add(myBookingBtn);
        buttonPanel.add(logoutBtn);

        gbc.gridy = 7;
        panel.add(buttonPanel, gbc);

        // Events
        bookBtn.addActionListener(e -> {
            String selectedRoom = roomList.getSelectedValue();
            String checkIn = checkInInput.getText().trim();
            String checkOut = checkOutInput.getText().trim();

            if (selectedRoom == null) {
                JOptionPane.showConfirmDialog(window, "Please select a room", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else if (checkIn.isEmpty() || checkOut.isEmpty()) {
                JOptionPane.showConfirmDialog(window, "Please enter check-in and check-out date", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                //เชื่อมกับ Booking จริง
                JOptionPane.showConfirmDialog(window,
                        "Booking request:\n" + selectedRoom + "\n" + checkIn + " - " + checkOut,
                        "Booking Confirmed", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        myBookingBtn.addActionListener(e -> {
            //เชื่อมกับ Booking ของจริงเพื่อดึงข้อมูล email 
            JOptionPane.showConfirmDialog(window, "No bookings yet for " + email, "My Bookings",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        logoutBtn.addActionListener(e -> {
            main.showLogin(window);
            window.revalidate();
        });

        return panel;
    }
}