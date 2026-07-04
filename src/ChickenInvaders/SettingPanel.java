package ChickenInvaders;

import javax.swing.*;
        import java.awt.*;

public class SettingPanel extends JPanel {
    private GameMain main;

    private JCheckBox bgMusicBox = new JCheckBox("Background Music");
    private JCheckBox shotSoundBox = new JCheckBox("Laser/Shot Sound");
    private JCheckBox crashSoundBox = new JCheckBox("Explosion/Crash Sound");
    private JCheckBox gameOverBox = new JCheckBox("Game Over Sound");

    public SettingPanel(GameMain main) {
        this.main = main;
        setName("SettingsPanel");
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;


        JLabel title = new JLabel("SOUND SETTINGS", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.YELLOW);
        gbc.gridy = 0;
        add(title, gbc);


        JCheckBox[] boxes = {bgMusicBox, shotSoundBox, crashSoundBox, gameOverBox};
        for (int i = 0; i < boxes.length; i++) {
            boxes[i].setFont(new Font("Arial", Font.PLAIN, 18));
            boxes[i].setForeground(Color.WHITE);
            boxes[i].setBackground(Color.BLACK);
            gbc.gridy = i + 1;
            add(boxes[i], gbc);
        }

        JButton saveBtn = new JButton("Save & Back");
        saveBtn.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridy = 5;
        add(saveBtn, gbc);

        saveBtn.addActionListener(e -> {
            User user = main.getCurrentUser();
            if (user != null) {

                user.setBgMusic(bgMusicBox.isSelected());
                user.setShotSound(shotSoundBox.isSelected());
                user.setCrashSound(crashSoundBox.isSelected());
                user.setGameOverSound(gameOverBox.isSelected());


                DatabaseManager.updateUser(user);

                JOptionPane.showMessageDialog(this, "Settings saved successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Please log in to save settings.");
            }
            main.showPanel("MainMenu");
        });
    }

    public void refreshData() {
        User user = main.getCurrentUser();
        if (user != null) {
            bgMusicBox.setSelected(user.isBgMusic());
            shotSoundBox.setSelected(user.isShotSound());
            crashSoundBox.setSelected(user.isCrashSound());
            gameOverBox.setSelected(user.isGameOverSound());
        } else {

            bgMusicBox.setSelected(true);
            shotSoundBox.setSelected(true);
            crashSoundBox.setSelected(true);
            gameOverBox.setSelected(true);
        }
    }
}