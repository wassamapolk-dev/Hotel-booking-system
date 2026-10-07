package GUI;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class Booking extends JFrame 
{

    // Components
    private String userEmail;

    private JPanel panelMenu;
    private JPanel panelBooking;

    private JLabel title;
    private JLabel nameLabel;
    private JLabel roomLabel;
    private JLabel checkInLabel;
    private JLabel checkOutLabel;
    private JLabel totalLabel;

    private JTextField nameField;
    
    private JTextField checkInField;
    private JTextField checkOutField;
    private LocalDate checkInDate = LocalDate.now();
    private LocalDate checkOutDate = LocalDate.now().plusDays(1);

    private JComboBox<String> roomComboBox;

    private JButton confirmButton;
    private JButton bookingButton;
    private JButton guestButton;
    private JButton roomButton;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Booking(String userEmail) 
    {
        this.userEmail = userEmail;

        // JFrame
        setTitle("KU Hotel @Booking " + userEmail);
        setSize(1044, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());

        // ซ้าย MENU
        panelMenu = new JPanel();
        panelMenu.setBackground(new Color(13, 31, 35));
        panelMenu.setPreferredSize(new Dimension(270, 600));
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));

        title = new JLabel("KU Hotel @Booking");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        bookingButton = new JButton("Booking");
        guestButton = new JButton("GUEST");
        roomButton = new JButton("ROOM");

        bookingButton.setMaximumSize(new Dimension(175, 83));
        guestButton.setMaximumSize(new Dimension(175, 83));
        roomButton.setMaximumSize(new Dimension(175, 83));

        bookingButton.setBackground(new Color(105, 129, 141));
        guestButton.setBackground(new Color(105, 129, 141));
        roomButton.setBackground(new Color(105, 129, 141));

        bookingButton.setFont(new Font("Segoe UI", Font.BOLD, 26));
        guestButton.setFont(new Font("Segoe UI", Font.BOLD, 26));
        roomButton.setFont(new Font("Segoe UI", Font.BOLD, 26));

        bookingButton.setForeground(Color.WHITE);
        guestButton.setForeground(Color.WHITE);
        roomButton.setForeground(Color.WHITE);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        guestButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        roomButton.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        // CHECK-IN
        checkInLabel = new JLabel("Check-in :");
        checkInLabel.setForeground(Color.WHITE);
        checkInLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JPanel checkInPanel = new JPanel(new BorderLayout(5, 0));
        checkInPanel.setOpaque(false);
        checkInPanel.setPreferredSize(new Dimension(279, 25));

        checkInField = new JTextField(checkInDate.format(dateFormatter));
        checkInField.setEditable(false);
        checkInField.setBackground(Color.WHITE);

        JButton btnCalIn = new JButton("📅");
        btnCalIn.setMargin(new Insets(0, 2, 0, 2));
        btnCalIn.addActionListener(e -> openCalendarPicker(true));

        checkInPanel.add(checkInField, BorderLayout.CENTER);
        checkInPanel.add(btnCalIn, BorderLayout.EAST);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panelBooking.add(checkInLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelBooking.add(checkInPanel, gbc);

        // CHECK-OUT
        checkOutLabel = new JLabel("Check-out :");
        checkOutLabel.setForeground(Color.WHITE);
        checkOutLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JPanel checkOutPanel = new JPanel(new BorderLayout(5, 0));
        checkOutPanel.setOpaque(false);
        checkOutPanel.setPreferredSize(new Dimension(279, 25));

        checkOutField = new JTextField(checkOutDate.format(dateFormatter));
        checkOutField.setEditable(false);
        checkOutField.setBackground(Color.WHITE);

        JButton btnCalOut = new JButton("📅");
        btnCalOut.setMargin(new Insets(0, 2, 0, 2));
        btnCalOut.addActionListener(e -> openCalendarPicker(false));

        checkOutPanel.add(checkOutField, BorderLayout.CENTER);
        checkOutPanel.add(btnCalOut, BorderLayout.EAST);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        panelBooking.add(checkOutLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelBooking.add(checkOutPanel, gbc);

        // TOTAL
        totalLabel = new JLabel("Total 0 Baht");
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridheight = 2;
        gbc.insets = new Insets(10, 20, 10, 10);
        panelBooking.add(totalLabel, gbc);

        // CONFIRM BUTTON
        confirmButton = new JButton("Confirm Booking");
        confirmButton.setBackground(new Color(105, 129, 141));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        confirmButton.setPreferredSize(new Dimension(180, 35));

        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(25, 10, 10, 10);
        panelBooking.add(confirmButton, gbc);

        add(panelMenu, BorderLayout.WEST);
        add(panelBooking, BorderLayout.CENTER);

        // LISTENERS
        roomComboBox.addActionListener(e -> updateTotalLabel());
        confirmButton.addActionListener(e -> confirmBooking());

        updateTotalLabel();
    }

    public Booking()
    {
        this("guest@ku.th");
    }

    // --- POPUP ปฏิทินเลือกวันที่แบบรูปที่ 2 ---
    private void openCalendarPicker(boolean isCheckIn) 
    {
        JDialog dialog = new JDialog(this, isCheckIn ? "Select Check-in Date" : "Select Check-out Date", true);
        dialog.setSize(320, 300);
        dialog.setLocationRelativeTo(this);
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
                LocalDate firstOfMonth = ym.atDay(1);
                int dayOfWeek = firstOfMonth.getDayOfWeek().getValue(); // 1 = Mon, 7 = Sun

                for (int i = 1; i < dayOfWeek; i++) {
                    daysPanel.add(new JLabel(""));
                }

                int daysInMonth = ym.lengthOfMonth();
                for (int day = 1; day <= daysInMonth; day++) {
                    final int d = day;
                    JButton dayBtn = new JButton(String.valueOf(day));
                    dayBtn.setMargin(new Insets(2, 2, 2, 2));
                    dayBtn.setFocusPainted(false);
                    dayBtn.setBackground(Color.WHITE);

                    LocalDate thisBtnDate = ym.atDay(day);
                    LocalDate selectedTarget = isCheckIn ? checkInDate : checkOutDate;
                    if (thisBtnDate.equals(selectedTarget)) {
                        dayBtn.setBackground(new Color(230, 160, 60)); // วงไฮไลท์สีส้มแบบในรูป 2
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
                        updateTotalLabel();
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

    private int calculateNights() 
    {
        return (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    private void updateTotalLabel() 
    {
        int night = calculateNights();
        if (night <= 0) 
        {
            totalLabel.setText("Invalid Date");
            return;
        }

        String room = roomComboBox.getSelectedItem().toString();
        int price = 0;

        if (room.startsWith("101")) price = 1000;
        else if (room.startsWith("102")) price = 1500;
        else if (room.startsWith("103")) price = 2000;

        int total = price * night;
        totalLabel.setText("Total " + total + " Baht");
    }

    private void confirmBooking() 
    {
        String name = nameField.getText();
        String room = roomComboBox.getSelectedItem().toString();

        if (name.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Please fill in all information.");
            return;
        }

        int night = calculateNights();

        if (night <= 0) 
        {
            JOptionPane.showMessageDialog(this, "Check-out date must be after Check-in date!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int price = 0;
        if (room.startsWith("101")) price = 1000;
        else if (room.startsWith("102")) price = 1500;
        else if (room.startsWith("103")) price = 2000;

        int total = price * night;

        totalLabel.setText("Total " + total + " Baht");

        Backend.BookingService.saveBookingToCSV(this.userEmail, name, room, night, total);
        Backend.BookingService.sendEmailSimulation(this.userEmail, name, room, night, total);

        JOptionPane.showMessageDialog(this, "Booking Successful!\n"
                + "Name: " + name + "\n"
                + "Room: " + room + "\n"
                + "Night: " + night + "\n"
                + "Total: " + total + " Baht",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}