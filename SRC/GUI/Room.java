import java.awt.*;
import javax.swing.*;

public class Room extends JPanel {
 
    private static final Color COLOR_BG = new Color(0, 102, 153);
    private static final Color COLOR_MENU = new Color(0, 0, 153);
    private static final Color COLOR_BUTTON = new Color(204, 204, 204);
 
    private JPanel pnlMenu;
    private JPanel pnlMain;
    private JPanel pnlRooms;
    private JLabel lblHotel;
    private JLabel lblTitle;
    private JButton btnBooking;
    private JButton btnGuest;
    private JButton btnRoom;
    private JButton[] roomButtons;
 
    public Room() {
        initUI();
    }
 
    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(COLOR_BG);
 
        add(createMenuPanel(), BorderLayout.WEST);
        add(createMainPanel(), BorderLayout.CENTER);
    }
 
    //ปุ่มทางซ้าย
    private JPanel createMenuPanel() {
        pnlMenu = new JPanel();
        pnlMenu.setBackground(COLOR_MENU);
        pnlMenu.setLayout(new BoxLayout(pnlMenu, BoxLayout.Y_AXIS));
        pnlMenu.setBorder(BorderFactory.createEmptyBorder(39, 20, 0, 26));
        pnlMenu.setPreferredSize(new Dimension(134, 300));
 
        lblHotel = new JLabel("KU HOTEL");
        lblHotel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblHotel.setForeground(Color.WHITE);
        lblHotel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        lblHotel.setAlignmentX(LEFT_ALIGNMENT);
 
        btnBooking = createMenuButton("Booking", 36);
        btnGuest = createMenuButton("Guest", 34);
        btnRoom = createMenuButton("Room", 34);
 
        btnBooking.addActionListener(e -> onBookingClicked());
        btnGuest.addActionListener(e -> onGuestClicked());
        btnRoom.addActionListener(e -> onRoomMenuClicked());
 
        pnlMenu.add(lblHotel);
        pnlMenu.add(Box.createRigidArea(new Dimension(0, 29)));
        pnlMenu.add(btnBooking);
        pnlMenu.add(Box.createRigidArea(new Dimension(0, 18)));
        pnlMenu.add(btnGuest);
        pnlMenu.add(Box.createRigidArea(new Dimension(0, 18)));
        pnlMenu.add(btnRoom);
        pnlMenu.add(Box.createVerticalGlue());
 
        return pnlMenu;
    }
 
    private JButton createMenuButton(String text, int height) {
        JButton btn = new JButton(text);
        btn.setBackground(COLOR_BUTTON);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setAlignmentX(LEFT_ALIGNMENT);
        btn.setPreferredSize(new Dimension(88, height));
        btn.setMinimumSize(new Dimension(88, height));
        btn.setMaximumSize(new Dimension(Short.MAX_VALUE, height));
        return btn;
    }
 
    //ส่วนเลือกห้อง(ตรงกลาง)
    private JPanel createMainPanel() {
        pnlMain = new JPanel(new BorderLayout());
        pnlMain.setBackground(COLOR_BG);
 
        lblTitle = new JLabel("Select Room", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(40, 0, 18, 0));
        pnlMain.add(lblTitle, BorderLayout.NORTH);
 
        pnlRooms = new JPanel(new GridLayout(3, 2, 37, 18));
        pnlRooms.setBackground(COLOR_BG);
        pnlRooms.setBorder(BorderFactory.createEmptyBorder(0, 36, 49, 35));
 
        String[] roomNames = {"101", "102", "103", "104", "105", "106"};
        roomButtons = new JButton[roomNames.length];
 
        for (int i = 0; i < roomNames.length; i++) {
            JButton btn = new JButton(roomNames[i]);
            btn.setBackground(COLOR_BUTTON);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
            btn.addActionListener(e -> onRoomClicked(btn.getText()));
            roomButtons[i] = btn;
            pnlRooms.add(btn);
        }
 
        pnlMain.add(pnlRooms, BorderLayout.CENTER);
        return pnlMain;
    }
 
    //จัดการ event
    private void onBookingClicked() {
        //ไปหน้า Booking
    }
 
    private void onGuestClicked() {
        //ไปหน้า Guest
    }
 
    private void onRoomMenuClicked() {
        //อยู่หน้า Room อยู่แล้ว
    }
 
    private void onRoomClicked(String roomNumber) {
        //กดจองห้องแล้วไปหน้า Booking (ส่งเลขห้องไปด้วย)
        System.out.println("เลือกห้อง " + roomNumber);
    }
}