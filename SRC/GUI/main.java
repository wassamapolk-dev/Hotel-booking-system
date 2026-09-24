import java.util.logging.Level;
import java.util.logging.Logger;

public class main {

    private static final Logger logger = Logger.getLogger(main.class.getName());

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        // ถ้ามี Nimbus (มีมาตั้งแต่ Java SE 6) ให้ใช้ ไม่งั้นใช้ default look and feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        /* Create and display the Register form */
        java.awt.EventQueue.invokeLater(() -> new Register().setVisible(true));
    }
}