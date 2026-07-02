package ChickenInvaders;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    private GameMain main;
    private JTextField userField = new JTextField(15);
    private JPasswordField passField = new JPasswordField(15);

    public RegisterPanel(GameMain main) {
        this.main = main;
        setName("RegisterPanel");
        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("New Username:"), gbc);
        gbc.gridx = 1; add(userField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; add(passField, gbc);

        JButton regBtn = new JButton("Register");
        JButton backBtn = new JButton("Back");
        gbc.gridx = 0; gbc.gridy = 2; add(regBtn, gbc);
        gbc.gridx = 1; add(backBtn, gbc);

        regBtn.addActionListener(e -> {
            if (DatabaseManager.registerUser(userField.getText(), new String(passField.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Success! Please Login.");
                main.showPanel("LoginPanel");
            } else {
                JOptionPane.showMessageDialog(this, "Username exists or invalid!");
            }
        });

        backBtn.addActionListener(e -> main.showPanel("LoginPanel"));
    }
}