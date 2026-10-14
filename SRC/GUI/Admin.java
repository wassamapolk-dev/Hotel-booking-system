package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.List;

public class Admin extends JFrame
{
    private JTable bookingTable; // ตารางแสดงรายการจองห้องพัก
    private JPanel Bg;
    private DefaultTableModel tableModel; // Model ควบคุมข้อมูลของตาราง
    private String adminEmail;

    // Constructor หลักของหน้า Admin Dashboard
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

        // Header ข้อความส่วนบน
        JLabel headerLabel = new JLabel("Booking Records Dashboard", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(headerLabel, BorderLayout.NORTH);

        // ตั้งค่าโครงสร้างตารางข้อมูล (JTable & DefaultTableModel)
        String[] columnNames = {"Email", "Name", "Room", "Night", "Total (THB)", "Date"};
        tableModel = new DefaultTableModel(columnNames, 0) 
        {
            // ป้องกันไม่ให้ผู้ใช้แก้ไขข้อมูลในตารางโดยตรง
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

        // Panel ปุ่มการทำงานด้านล่าง (Refresh & Delete)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(13, 31, 35));

        JButton btnRefresh = new JButton("Refresh Data");
        btnRefresh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnRefresh.setBackground(Color.GREEN);
        btnRefresh.addActionListener(e -> loadBookingData()); // รีเฟรชตาราง

        JButton btnDelete = new JButton("Delete Selected");
        btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnDelete.setBackground(new Color(204, 0, 0));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.addActionListener(e -> deleteSelectedBooking()); // ลบข้อมูลแถวที่เลือก

        bottomPanel.add(btnRefresh);
        bottomPanel.add(btnDelete);
        add(bottomPanel, BorderLayout.SOUTH);

        // โหลดข้อมูลจากไฟล์ CSV เข้ามาแสดงผลในตารางเมื่อเริ่มต้น
        loadBookingData();
    }

    // Method โหลดข้อมูลรายการจองทั้งหมดจาก Backend นำมาใส่ใน DefaultTableModel
    private void loadBookingData()
    {
        tableModel.setRowCount(0); // ล้างข้อมูลเก่าในตารางออกก่อน
        try 
        {
            // ดึงข้อมูลผ่าน Backend.Admin
            List<String[]> dataList = Backend.Admin.loadBookingData();
            for (String[] rowData : dataList) 
            {
                tableModel.addRow(rowData); // เพิ่มแถวข้อมูลลงในตาราง
            }
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(this, "Error reading booking.csv: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method ลบรายการจองที่ถูกเลือกในตาราง
    private void deleteSelectedBooking()
    {
        int selectedRow = bookingTable.getSelectedRow(); // ดึงแถวที่ผู้ใช้คลิกเลือก

        if (selectedRow == -1) 
        {
            JOptionPane.showMessageDialog(this, "Please select a booking record to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ยืนยันการลบข้อมูล
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this booking?", 
                "Confirm Delete", 
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) 
        {
            return;
        }

        try 
        {
            // เรียกใช้ Backend เพื่อลบแถวข้อมูลในไฟล์ CSV
            boolean success = Backend.Admin.deleteBookingRow(selectedRow);
            if (success) 
            {
                JOptionPane.showMessageDialog(this, "Booking deleted successfully!");
                loadBookingData(); // รีเฟรชตารางหลังลบเสร็จ
            }
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(this, "Error deleting booking: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}