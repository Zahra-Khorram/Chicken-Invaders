package ChickenInvaders;

import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
import java.awt.*;
        import java.util.List;

public class HighScorePanel extends JPanel {
    private GameMain main;
    private JTable table;
    private DefaultTableModel tableModel;

    public HighScorePanel(GameMain main) {
        this.main = main;
        setName("HighScorePanel");
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("TOP 10 HIGH SCORES", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.ORANGE);
        add(title, BorderLayout.NORTH);


        String[] columnNames = {"Rank", "Player Name", "Highest Score", "Spaceship"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setBackground(Color.DARK_GRAY);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.GREEN);
        table.setGridColor(Color.GRAY);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.BLACK);
        add(scrollPane, BorderLayout.CENTER);

        JButton backBtn = new JButton("Back to Menu");
        backBtn.setFont(new Font("Arial", Font.BOLD, 16));
        backBtn.setPreferredSize(new Dimension(150, 40));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(backBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        backBtn.addActionListener(e -> main.showPanel("MainMenu"));
    }

    public void refreshData() {
        tableModel.setRowCount(0);

        List<String[]> scores = DatabaseManager.getHighScores();

        int rank = 1;
        for (String[] scoreRow : scores) {
            tableModel.addRow(new Object[]{
                    rank++,
                    scoreRow[0],
                    scoreRow[1],
                    scoreRow[2]
            });
        }
    }
}