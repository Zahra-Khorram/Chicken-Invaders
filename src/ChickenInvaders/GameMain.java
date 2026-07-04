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


        mainPanel.add(new SettingPanel(this), "SettingsPanel");
        mainPanel.add(new HowToPlayPanel(this), "HowToPlayPanel");
        mainPanel.add(new HighScorePanel(this), "HighScorePanel");
        mainPanel.add(new JPanel(), "StorePanel");

        add(mainPanel);
        showPanel("MainMenu");
    }

    public void showPanel(String name) {
        for (Component comp : mainPanel.getComponents()) {
            if (comp instanceof SettingPanel && name.equals("SettingsPanel")) {
                ((SettingPanel) comp).refreshData();
            }
            if (comp instanceof HighScorePanel && name.equals("HighScorePanel")) {
                ((HighScorePanel) comp).refreshData();
            }
        }
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