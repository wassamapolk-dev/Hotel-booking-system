package GUI;

import javax.swing.*;
import java.awt.*;

public class Room extends JPanel {

    private JPanel panelMenu;
    private JPanel panelRoom;

    private JLabel title;
    private JLabel roomTitle;

    private JButton bookingButton;
    private JButton guestButton;
    private JButton roomButton;

    private JButton room101;
    private JButton room102;
    private JButton room103;
    private JButton room104;
    private JButton room105;
    private JButton room106;

    public Room() {

        // ROOM PANEL
        setLayout(new BorderLayout());

        // ซ้าย MENU
        panelMenu = new JPanel();
        panelMenu.setBackground(new Color(13, 31, 35));

        // ขนาดเหมือน Booking
        panelMenu.setPreferredSize(new Dimension(270, 600));
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));

        // TITLE
        title = new JLabel("KU Hotel @Booking");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        // BUTTON
        bookingButton = new JButton("Booking");
        guestButton = new JButton("GUEST");
        roomButton = new JButton("ROOM");


        // ขนาดปุ่มเหมือน Booking


        bookingButton.setMaximumSize(new Dimension(175, 83));

        guestButton.setMaximumSize(new Dimension(175, 83));

        roomButton.setMaximumSize(new Dimension(175, 83));

        // สีปุ่ม


        bookingButton.setBackground(new Color(105, 129, 141));
        guestButton.setBackground(new Color(105, 129, 141));
        roomButton.setBackground(new Color(105, 129, 141));

 
        // Font ปุ่ม


        bookingButton.setFont(new Font("Segoe UI", Font.BOLD, 26));
        guestButton.setFont(new Font("Segoe UI", Font.BOLD, 26));
        roomButton.setFont(new Font("Segoe UI", Font.BOLD, 26));

        // สีตัวอักษร
        bookingButton.setForeground(Color.WHITE);
        guestButton.setForeground(Color.WHITE);
        roomButton.setForeground(Color.WHITE);

        // จัดตำแหน่ง


        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        guestButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        roomButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        // เพิ่มลง MENU
   

        panelMenu.add(Box.createVerticalStrut(45));
        panelMenu.add(title);
        panelMenu.add(Box.createVerticalStrut(80));
        panelMenu.add(bookingButton);
        panelMenu.add(Box.createVerticalStrut(18));
        panelMenu.add(guestButton);
        panelMenu.add(Box.createVerticalStrut(18));
        panelMenu.add(roomButton);

     
        // ขวา ROOM
        panelRoom = new JPanel();

        panelRoom.setBackground(new Color(19, 46, 53));

        panelRoom.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

  
        // TITLE
        roomTitle = new JLabel("Select Room");

        roomTitle.setForeground(Color.WHITE);

        roomTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 30));

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 2;

        gbc.anchor = GridBagConstraints.CENTER;

        gbc.insets = new Insets(0, 0, 30, 0);

        panelRoom.add(roomTitle, gbc);

        // ROOM BUTTON
        room101 = createRoomButton("101");
        room102 = createRoomButton("102");

        room103 = createRoomButton("103");
        room104 = createRoomButton("104");

        room105 = createRoomButton("105");
        room106 = createRoomButton("106");


        // 101
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;

        gbc.insets = new Insets(10, 20, 10, 20);

        panelRoom.add(room101, gbc);


        // 102
        gbc.gridx = 1;
        panelRoom.add(room102, gbc);


        // 103
        gbc.gridx = 0;
        gbc.gridy = 2;

        panelRoom.add(room103, gbc);


        // 104
        gbc.gridx = 1;
        panelRoom.add(room104, gbc);

  
        // 105
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelRoom.add(room105, gbc);

        // 106
        gbc.gridx = 1;
        panelRoom.add(room106, gbc);


        // เพิ่ม Panel
        add(panelMenu, BorderLayout.WEST);

        add(panelRoom, BorderLayout.CENTER);


        // BUTTON EVENTS
        bookingButton.addActionListener(e -> {

            Booking booking = new Booking();
            booking.setVisible(true);
            Window window = SwingUtilities.getWindowAncestor(this);

            if (window != null) {
                window.dispose();
            }

        });

        room101.addActionListener(e -> onRoomClicked("101"));
        room102.addActionListener(e -> onRoomClicked("102"));
        room103.addActionListener(e -> onRoomClicked("103"));
        room104.addActionListener(e -> onRoomClicked("104"));
        room105.addActionListener(e -> onRoomClicked("105"));
        room106.addActionListener(e -> onRoomClicked("106"));
    }


    // สร้างปุ่มห้อง
    private JButton createRoomButton(
            String roomNumber) {

        JButton button = new JButton(roomNumber);
        button.setBackground(new Color(105, 129, 141));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 26));
        button.setPreferredSize(new Dimension(175, 83));

        return button;
    }


    // เมื่อกดห้อง
    private void onRoomClicked(
            String roomNumber) {
        System.out.println("เลือกห้อง " + roomNumber);
    }
}