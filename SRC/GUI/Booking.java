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
    private JLabel PhoneLabel;
    private JLabel totalLabel;

    private JTextField nameField;
    
    private LocalDate checkInDate = LocalDate.now();
    private LocalDate checkOutDate = LocalDate.now().plusDays(1);

    private JTextField PhoeField;

    private String selectedRoom = "101(Single Room) 1000 Bath";

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
        PhoneLabel = new JLabel("Phone :");
        PhoneLabel.setForeground(Color.WHITE);
        PhoneLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        PhoeField = new JTextField(25);
        PhoeField.setPreferredSize(new Dimension(279, 25));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panelBooking.add(PhoneLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelBooking.add(PhoeField, gbc);

        // TOTAL
        totalLabel = new JLabel("Total 0 Baht");
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
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
        PhoeField.addActionListener(e -> updateTotalLabel());
        confirmButton.addActionListener(e -> confirmBooking());
        roomButton.addActionListener(e -> openRoomPage());

        updateTotalLabel();
    }

    public Booking()
    {
        this("guest@ku.th");
    }

    public Booking(String userEmail, LocalDate checkInDate, LocalDate checkOutDate)
    {
        this(userEmail);
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        updateTotalLabel();
    }

    // เปิดหน้า Room
    private void openRoomPage()
    {
        JFrame roomFrame = new JFrame("KU Hotel @Room " + userEmail);
        roomFrame.setSize(1044, 600);
        roomFrame.setResizable(false);
        roomFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        roomFrame.setLocationRelativeTo(null);
        roomFrame.add(new Room(userEmail, checkInDate, checkOutDate));
        roomFrame.setVisible(true);
        dispose();
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

        String room = selectedRoom;
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
        String room = selectedRoom;

        if (name.isEmpty() || PhoeField.getText().trim().isEmpty()) 
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
                + "Phone: " + PhoeField.getText().trim() + "\n"
                + "Room: " + room + "\n"
                + "Night: " + night + "\n"
                + "Total: " + total + " Baht",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}