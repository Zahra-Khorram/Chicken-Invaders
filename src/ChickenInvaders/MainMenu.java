package ChickenInvaders;

import javax.swing.*;
        import java.awt.*;

public class MainMenu extends JPanel {
    private GameMain main;

    public MainMenu(GameMain main) {
        this.main = main;
        setName("MainMenu");
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("CHICKEN INVADERS", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 38));
        title.setForeground(Color.ORANGE);
        gbc.gridx = 0; gbc.gridy = 0;
        add(title, gbc);

        String[] buttons = {"New Game", "High Scores", "Settings", "How to Play", "Store", "Exit"};
        for (int i = 0; i < buttons.length; i++) {
            JButton btn = new JButton(buttons[i]);
            btn.setFont(new Font("Arial", Font.PLAIN, 18));
            gbc.gridy = i + 1;
            add(btn, gbc);

            final String action = buttons[i];
            btn.addActionListener(e -> {
                if (action.equals("New Game")) main.startGame();
                else if (action.equals("High Scores")) main.showPanel("HighScorePanel");
                else if (action.equals("Settings")) main.showPanel("SettingsPanel");
                else if (action.equals("How to Play")) main.showPanel("HowToPlayPanel");
                else if (action.equals("Store")) main.showPanel("StorePanel");
                else if (action.equals("Exit")) System.exit(0);
            });
        }
    }
}