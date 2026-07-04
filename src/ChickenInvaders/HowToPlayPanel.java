package ChickenInvaders;

import javax.swing.*;
import java.awt.*;

public class HowToPlayPanel extends JPanel {
    private GameMain main;

    public HowToPlayPanel(GameMain main) {
        this.main = main;
        setName("HowToPlayPanel");
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10); // فاصله بین خطوط
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("HOW TO PLAY", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.CYAN);
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 25, 10); // فاصله بیشتر زیر عنوان
        add(title, gbc);

        gbc.insets = new Insets(6, 10, 6, 10);

        JLabel labelControls = new JLabel("🗣 Controls:", JLabel.CENTER);
        labelControls.setFont(new Font("Arial", Font.BOLD, 18));
        labelControls.setForeground(Color.YELLOW);
        gbc.gridy = 1; add(labelControls, gbc);

        JLabel labelMove = new JLabel("⬅️ / ➡️ / ⬆️ / ⬇️  :  Move your Spaceship", JLabel.CENTER);
        labelMove.setFont(new Font("Arial", Font.PLAIN, 16));
        labelMove.setForeground(Color.WHITE);
        gbc.gridy = 2; add(labelMove, gbc);

        JLabel labelShoot = new JLabel("⌨️ SPACEBAR  :  Shoot Lasers", JLabel.CENTER);
        labelShoot.setFont(new Font("Arial", Font.PLAIN, 16));
        labelShoot.setForeground(Color.WHITE);
        gbc.gridy = 3;
        gbc.insets = new Insets(6, 10, 20, 10); // فاصله بیشتر قبل از بخش بعد
        add(labelShoot, gbc);


        gbc.insets = new Insets(6, 10, 6, 10);
        JLabel labelRules = new JLabel("🎯 Rules:", JLabel.CENTER);
        labelRules.setFont(new Font("Arial", Font.BOLD, 18));
        labelRules.setForeground(Color.YELLOW);
        gbc.gridy = 4; add(labelRules, gbc);

        JLabel rule1 = new JLabel("1. Eliminate all chickens in each level to advance.", JLabel.CENTER);
        rule1.setFont(new Font("Arial", Font.PLAIN, 15));
        rule1.setForeground(Color.WHITE);
        gbc.gridy = 5; add(rule1, gbc);

        JLabel rule2 = new JLabel("2. Avoid falling eggs and direct crashes with enemies.", JLabel.CENTER);
        rule2.setFont(new Font("Arial", Font.PLAIN, 15));
        rule2.setForeground(Color.WHITE);
        gbc.gridy = 6; add(rule2, gbc);

        JLabel rule3 = new JLabel("3. Collect Power-Ups to upgrade weapons or gain shields.", JLabel.CENTER);
        rule3.setFont(new Font("Arial", Font.PLAIN, 15));
        rule3.setForeground(Color.WHITE);
        gbc.gridy = 7; add(rule3, gbc);

        JLabel rule4 = new JLabel("4. Defeat the massive Bosses at Level 4 and Level 8.", JLabel.CENTER);
        rule4.setFont(new Font("Arial", Font.PLAIN, 15));
        rule4.setForeground(Color.WHITE);
        gbc.gridy = 8; add(rule4, gbc);


        JButton backBtn = new JButton("Back to Menu");
        backBtn.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridy = 9;
        gbc.insets = new Insets(30, 10, 10, 10); // ایجاد فاصله خالی بالای دکمه
        gbc.fill = GridBagConstraints.NONE; // دکمه به اندازه متن خودش باشد، نه کل عرض صفحه
        add(backBtn, gbc);


        backBtn.addActionListener(e -> main.showPanel("MainMenu"));
    }
}