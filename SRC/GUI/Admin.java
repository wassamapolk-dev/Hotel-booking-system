package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.List;

public class Admin extends JFrame
{
    private JTable bookingTable;
    private JPanel Bg;
    private DefaultTableModel tableModel;
    private String adminEmail;

    public Admin(String adminEmail)
    {
        this.adminEmail = adminEmail;

        setTitle("KU Hotel - Admin Dashboard (" + adminEmail + ")");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Bg = new JPanel(new BorderLayout());
        Bg.setBackground(new Color(13, 31, 35));
        setContentPane(Bg);

        // Header Label
        JLabel headerLabel = new JLabel("Booking Records Dashboard", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(headerLabel, BorderLayout.NORTH);

        // ตั้งค่า ตาราง (JTable)
        String[] columnNames = {"Email", "Name", "Room", "Night", "Total (THB)", "Date"};
        tableModel = new DefaultTableModel(columnNames, 0) 
        {
            public boolean isCellEditable(int row, int column) 
            {
                return false;
            }
        };

        bookingTable = new JTable(tableModel);
        bookingTable.setRowHeight(25);
        bookingTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        bookingTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(bookingTable);
        add(scrollPane, BorderLayout.CENTER);

        // Panel ปุ่มด้านล่าง
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(13, 31, 35));

        JButton btnRefresh = new JButton("Refresh Data");
        btnRefresh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnRefresh.setBackground(Color.GREEN);
        btnRefresh.addActionListener(e -> loadBookingData());

        JButton btnDelete = new JButton("Delete Selected");
        btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnDelete.setBackground(new Color(204, 0, 0));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.addActionListener(e -> deleteSelectedBooking());

        bottomPanel.add(btnRefresh);
        bottomPanel.add(btnDelete);
        add(bottomPanel, BorderLayout.SOUTH);

        // โหลดข้อมูลเข้าตาราง
        loadBookingData();
    }

    private void loadBookingData()
    {
        tableModel.setRowCount(0);
        try 
        {
            // เรียกใช้ Backend
            List<String[]> dataList = Backend.Admin.loadBookingData();
            for (String[] rowData : dataList) 
            {
                tableModel.addRow(rowData);
            }
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(this, "Error reading booking.csv: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedBooking()
    {
        int selectedRow = bookingTable.getSelectedRow();

        if (selectedRow == -1) 
        {
            JOptionPane.showMessageDialog(this, "Please select a booking record to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this booking?", 
                "Confirm Delete", 
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try 
        {
            // เรียกใช้ Backend เพื่อลบข้อมูล
            boolean success = Backend.Admin.deleteBookingRow(selectedRow);
            if (success) 
            {
                JOptionPane.showMessageDialog(this, "Booking deleted successfully!");
                loadBookingData(); // รีเฟรชตาราง
            }
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(this, "Error deleting booking: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}