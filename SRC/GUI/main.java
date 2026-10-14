package GUI;

import javax.swing.JFrame;

public class main 
{
    /**
     * @param args ข้อความอาร์กิวเมนต์จาก Command Line (อาจเป็น Array ว่างได้)
     * @return void
     * @throws Exception หากเกิดข้อผิดพลาดรุนแรงในการวาด Component ของ Swing
    */
    public static void main(String[] args) 
    {
        // สร้างหน้าต่างหลักสำหรับหน้า Login
        JFrame frame = new JFrame("KU.Hotel.com - Login"); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.add(new Login()); 
        frame.pack(); // ปรับขนาดหน้าต่างให้พอดีกับ Component ภายใน
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true); 
    }
}