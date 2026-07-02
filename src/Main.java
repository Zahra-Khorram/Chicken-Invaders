
import ChickenInvaders.GameMain;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        //  بازی از داخل پکیج فراخوانی و اجرا می‌شود
        SwingUtilities.invokeLater(() -> new GameMain().setVisible(true));
    }
}