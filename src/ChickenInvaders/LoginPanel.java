package ChickenInvaders;

import javax.swing.*;
        import java.awt.*;

public class LoginPanel extends JPanel {
    private GameMain main;
    private JTextField userField = new JTextField(15);
    private JPasswordField passField = new JPasswordField(15);

    public LoginPanel(GameMain main) {
        this.main = main;
        setName("LoginPanel");
        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Username:"), gbc);
        gbc.gridx = 1; add(userField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; add(passField, gbc);

        JButton loginBtn = new JButton("Login");
        JButton regBtn = new JButton("Go to Register");
        gbc.gridx = 0; gbc.gridy = 2; add(loginBtn, gbc);
        gbc.gridx = 1; add(regBtn, gbc);

        loginBtn.addActionListener(e -> {
            User user = DatabaseManager.loginUser(userField.getText(), new String(passField.getPassword()));
            if (user != null) {
                main.setCurrentUser(user);
                main.startGame();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
            }
        });

        regBtn.addActionListener(e -> main.showPanel("RegisterPanel"));
    }
}