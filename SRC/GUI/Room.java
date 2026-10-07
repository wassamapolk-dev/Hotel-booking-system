package GUI;

import java.awt.*;
import javax.swing.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Room extends JPanel {
 
    private static final Color COLOR_BG = new Color(19, 46, 53);
    private static final Color COLOR_MENU = new Color(13, 31, 35);
    private static final Color COLOR_BUTTON = new Color(105, 129, 141);
 
    private JPanel pnlMenu;
    private JPanel pnlMain;
    private JPanel pnlRooms;
    private JLabel lblHotel;
    private JLabel lblTitle;
    private JButton btnBooking;
    private JButton btnGuest;
    private JButton btnRoom;
    private JButton[] roomButtons;
    private String userEmail;
    private JTextField checkInField;
    private JTextField checkOutField;
    private LocalDate checkInDate = LocalDate.now();
    private LocalDate checkOutDate = LocalDate.now().plusDays(1);
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
 
    public Room() {
        this("guest@ku.th");
    }

    public Room(String userEmail) {
        this.userEmail = userEmail;
        initUI();
    }

    public Room(String userEmail, LocalDate checkInDate, LocalDate checkOutDate) {
        this.userEmail = userEmail;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
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
        pnlMenu.setBorder(BorderFactory.createEmptyBorder(45, 0, 0, 0));
        pnlMenu.setPreferredSize(new Dimension(270, 600));
 
        lblHotel = new JLabel("KU Hotel @Booking");
        lblHotel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblHotel.setForeground(Color.WHITE);
        lblHotel.setAlignmentX(CENTER_ALIGNMENT);
 
        btnBooking = createMenuButton("Booking", 83);
        btnGuest = createMenuButton("Guest", 83);
        btnRoom = createMenuButton("Room", 83);
 
        btnBooking.addActionListener(e -> onBookingClicked());
        btnGuest.addActionListener(e -> onGuestClicked());
        btnRoom.addActionListener(e -> onRoomMenuClicked());
 
        pnlMenu.add(lblHotel);
        pnlMenu.add(Box.createRigidArea(new Dimension(0, 80)));
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
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 26));
        btn.setAlignmentX(CENTER_ALIGNMENT);
        btn.setPreferredSize(new Dimension(175, height));
        btn.setMaximumSize(new Dimension(175, height));
        return btn;
    }
 
    //ส่วนเลือกห้อง(ตรงกลาง)
    private JPanel createMainPanel() {
        pnlMain = new JPanel(new BorderLayout());
        pnlMain.setBackground(COLOR_BG);
 
        lblTitle = new JLabel("Select Room", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(40, 0, 18, 0));
        lblTitle.setAlignmentX(CENTER_ALIGNMENT);

        // วันที่ Check-in / Check-out (อยู่ใต้ Select Room)
        JPanel pnlDates = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        pnlDates.setBackground(COLOR_BG);
        pnlDates.setBorder(BorderFactory.createEmptyBorder(0, 0, 18, 0));

        JLabel checkInLabel = new JLabel("Check-in :");
        checkInLabel.setForeground(Color.WHITE);
        checkInLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        checkInField = new JTextField(checkInDate.format(dateFormatter), 9);
        checkInField.setEditable(false);
        checkInField.setBackground(Color.WHITE);
        JButton btnCalIn = new JButton("📅");
        btnCalIn.setMargin(new Insets(0, 2, 0, 2));
        btnCalIn.addActionListener(e -> openCalendarPicker(true));

        JLabel checkOutLabel = new JLabel("Check-out :");
        checkOutLabel.setForeground(Color.WHITE);
        checkOutLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        checkOutField = new JTextField(checkOutDate.format(dateFormatter), 9);
        checkOutField.setEditable(false);
        checkOutField.setBackground(Color.WHITE);
        JButton btnCalOut = new JButton("📅");
        btnCalOut.setMargin(new Insets(0, 2, 0, 2));
        btnCalOut.addActionListener(e -> openCalendarPicker(false));

        pnlDates.add(checkInLabel);
        pnlDates.add(checkInField);
        pnlDates.add(btnCalIn);
        pnlDates.add(checkOutLabel);
        pnlDates.add(checkOutField);
        pnlDates.add(btnCalOut);

        JPanel pnlTop = new JPanel();
        pnlTop.setBackground(COLOR_BG);
        pnlTop.setLayout(new BoxLayout(pnlTop, BoxLayout.Y_AXIS));
        pnlTop.add(lblTitle);
        pnlTop.add(pnlDates);
        pnlMain.add(pnlTop, BorderLayout.NORTH);
 
        pnlRooms = new JPanel(new GridLayout(3, 2, 37, 18));
        pnlRooms.setBackground(COLOR_BG);
        pnlRooms.setBorder(BorderFactory.createEmptyBorder(0, 36, 49, 35));
 
        String[] roomNames = {"101", "102", "103", "104", "105", "106"};
        roomButtons = new JButton[roomNames.length];
 
        for (int i = 0; i < roomNames.length; i++) {
            JButton btn = new JButton(roomNames[i]);
            btn.setBackground(COLOR_BUTTON);
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
            btn.addActionListener(e -> onRoomClicked(btn.getText()));
            roomButtons[i] = btn;
            pnlRooms.add(btn);
        }
 
        pnlMain.add(pnlRooms, BorderLayout.CENTER);
        return pnlMain;
    }
 
    // --- POPUP ปฏิทินเลือกวันที่ ---
    private void openCalendarPicker(boolean isCheckIn) {
        Window owner = SwingUtilities.getWindowAncestor(this);
        JDialog dialog = new JDialog(owner, isCheckIn ? "Select Check-in Date" : "Select Check-out Date", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(320, 300);
        dialog.setLocationRelativeTo(owner);
        dialog.setLayout(new BorderLayout());

        final LocalDate[] currentDisplay = { isCheckIn ? checkInDate : checkOutDate };

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 243, 246));
        JButton prevBtn = new JButton("<");
        JButton nextBtn = new JButton(">");
        JLabel monthLabel = new JLabel("", SwingConstants.CENTER);
        monthLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        headerPanel.add(prevBtn, BorderLayout.WEST);
        headerPanel.add(monthLabel, BorderLayout.CENTER);
        headerPanel.add(nextBtn, BorderLayout.EAST);

        JPanel daysPanel = new JPanel(new GridLayout(0, 7, 3, 3));
        daysPanel.setBackground(Color.WHITE);

        Runnable renderCalendar = new Runnable() {
            public void run() {
                daysPanel.removeAll();
                monthLabel.setText(currentDisplay[0].getMonth().toString() + " " + currentDisplay[0].getYear());

                String[] headers = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
                for (String h : headers) {
                    JLabel lbl = new JLabel(h, SwingConstants.CENTER);
                    lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
                    lbl.setForeground(Color.GRAY);
                    daysPanel.add(lbl);
                }

                YearMonth ym = YearMonth.of(currentDisplay[0].getYear(), currentDisplay[0].getMonth());
                int dayOfWeek = ym.atDay(1).getDayOfWeek().getValue(); // 1 = Mon, 7 = Sun

                for (int i = 1; i < dayOfWeek; i++) {
                    daysPanel.add(new JLabel(""));
                }

                for (int day = 1; day <= ym.lengthOfMonth(); day++) {
                    JButton dayBtn = new JButton(String.valueOf(day));
                    dayBtn.setMargin(new Insets(2, 2, 2, 2));
                    dayBtn.setFocusPainted(false);
                    dayBtn.setBackground(Color.WHITE);

                    LocalDate thisBtnDate = ym.atDay(day);
                    LocalDate selectedTarget = isCheckIn ? checkInDate : checkOutDate;
                    if (thisBtnDate.equals(selectedTarget)) {
                        dayBtn.setBackground(new Color(230, 160, 60));
                        dayBtn.setForeground(Color.WHITE);
                    }

                    dayBtn.addActionListener(e -> {
                        if (isCheckIn) {
                            checkInDate = thisBtnDate;
                            checkInField.setText(checkInDate.format(dateFormatter));
                        } else {
                            checkOutDate = thisBtnDate;
                            checkOutField.setText(checkOutDate.format(dateFormatter));
                        }
                        dialog.dispose();
                    });
                    daysPanel.add(dayBtn);
                }
                daysPanel.revalidate();
                daysPanel.repaint();
            }
        };

        prevBtn.addActionListener(e -> {
            currentDisplay[0] = currentDisplay[0].minusMonths(1);
            renderCalendar.run();
        });

        nextBtn.addActionListener(e -> {
            currentDisplay[0] = currentDisplay[0].plusMonths(1);
            renderCalendar.run();
        });

        renderCalendar.run();

        dialog.add(headerPanel, BorderLayout.NORTH);
        dialog.add(daysPanel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    //จัดการ event
    private void onBookingClicked() {
        //ไปหน้า Booking
        if (!checkOutDate.isAfter(checkInDate)) {
            JOptionPane.showMessageDialog(this, "Check-out date must be after Check-in date!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Booking bookingFrame = new Booking(userEmail, checkInDate, checkOutDate);
        bookingFrame.setVisible(true);
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) window.dispose();
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