package ChickenInvaders;

import javax.swing.*;
        import java.awt.*;

public class GameMain extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private User currentUser = null;

    public GameMain() {
        setTitle("Chicken Invaders");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        DatabaseManager.initDatabase();

        mainPanel.add(new MainMenu(this), "MainMenu");
        mainPanel.add(new LoginPanel(this), "LoginPanel");
        mainPanel.add(new RegisterPanel(this), "RegisterPanel");

        // پنل‌های خالی موقت برای جلوگیری از خطای کامپایل
        mainPanel.add(new JPanel(), "SettingsPanel");
        mainPanel.add(new JPanel(), "HowToPlayPanel");
        mainPanel.add(new JPanel(), "HighScorePanel");
        mainPanel.add(new JPanel(), "StorePanel");

        add(mainPanel);
        showPanel("MainMenu");
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }

    public void startGame() {
        if (currentUser == null) {
            showPanel("LoginPanel");
        } else {
            JOptionPane.showMessageDialog(this, "Game Started for " + currentUser.getUsername());
        }
    }

    public User getCurrentUser() { return currentUser; }
    public void setCurrentUser(User user) { this.currentUser = user; }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameMain().setVisible(true));
    }
}