package ChickenInvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener {
    private GameMain main;
    private Timer gameTimer;
    private Plane plane;
    private List<Bullet> bullets;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private List<Cell> cells;
    private boolean left, right, up, down, shootPressed;
    private ArrayList<Egg> eggs = new ArrayList<>();
    private ArrayList<PowerUp> powerUps = new ArrayList<>();
    private ArrayList<Explosion> explosions = new ArrayList<>();
    private long lastEggTime = System.currentTimeMillis();
    private long freezeEndTime = 0;
    private int enemyDirection = 1;
    private int enemySpeed = 2;
    private int enemyStepDown = 20;

    private int score = 0;
    private LevelManager levelManager = new LevelManager();
    private boolean isPaused = false;

    public GamePanel(GameMain main) {
        this.main = main;
        setName("GamePanel");
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(800, 600));

        bullets = new ArrayList<>();
        cells=new ArrayList<>();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) left = true;
                if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) right = true;
                if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) up = true;
                if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) down = true;
                if (key == KeyEvent.VK_SPACE) shootPressed = true;

                if (key == KeyEvent.VK_P) isPaused = !isPaused;

                if (key == KeyEvent.VK_ESCAPE) {
                    gameTimer.stop();
                    if (main.getCurrentUser() != null) {
                        DatabaseManager.saveGameRecord(main.getCurrentUser().getUsername(), score, levelManager.getCurrentLevel());
                    }
                    main.showPanel("MainMenu");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) left = false;
                if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) right = false;
                if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) up = false;
                if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) down = false;
                if (key == KeyEvent.VK_SPACE) shootPressed = false;
            }
        });


        gameTimer = new Timer(16, this);
    }

    public void startGameLoop() {
        score = 0;
        levelManager.reset();


        isPaused = false;
        plane = new Plane(370, 500);

        loadLevel();

        bullets.clear();

        gameTimer.start();
        requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isPaused) {
            updateGame();
        }
        repaint();
    }

    private void updateGame() {
        plane.move(left, right, up, down);

        if (shootPressed) {
            bullets.addAll(plane.shoot());
            SoundManager.shootSound();
        }

        boolean frozen = System.currentTimeMillis() < freezeEndTime;

        if (!frozen) {

            for (Enemy enemy : enemies) {
                enemy.update();
            }

        }

        if (!frozen && !enemies.isEmpty() && enemies.get(0) instanceof BossEnemy boss) {

            eggs.addAll(boss.shoot());
        }

        if (!levelManager.isBossLevel() && !frozen) {
            moveEnemyGrid();
        }

        if (!frozen && System.currentTimeMillis() - lastEggTime > 2000) {

            if (!enemies.isEmpty()) {

                Enemy enemy = enemies.get(
                        (int) (Math.random() * enemies.size())
                );

                eggs.add(
                        new Egg(
                                enemy.getX() + 20,
                                enemy.getY() + 40
                        )
                );

            }

            lastEggTime = System.currentTimeMillis();

        }

        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet b = bullets.get(i);
            b.update();
            if (b.isOutOfBounds()) {
                bullets.remove(i);
            }
        }
        for (int i = bullets.size() - 1; i >= 0; i--) {

            Bullet bullet = bullets.get(i);

            for (int j = enemies.size() - 1; j >= 0; j--) {

                Enemy enemy = enemies.get(j);

                if (bullet.getBounds().intersects(enemy.getBounds())) {

                    enemy.damage();

                    bullets.remove(i);

                    if (enemy.isDead()) {

                        switch (enemy.getType()) {

                            case NORMAL -> score += 10;
                            case FAST -> score += 15;
                            case ZIGZAG -> score += 20;
                            case SHOOTER -> score += 25;
                            case BOSS -> score += 500;
                        }
                        explosions.add(
                                new Explosion(
                                        enemy.getX() + 25,
                                        enemy.getY() + 25
                                )
                        );
                        enemies.remove(j);

                        if (Math.random() < 0.25) {

                            PowerUpType[] list = PowerUpType.values();

                            PowerUpType random =
                                    list[(int)(Math.random() * list.length)];

                            powerUps.add(
                                    new PowerUp(
                                            enemy.getX(),
                                            enemy.getY(),
                                            random
                                    )
                            );

                        }

                        if (enemies.isEmpty()) {

                            score += 200;

                            // اگر باس مرحله ۸ را شکست خورد
                            if (levelManager.getCurrentLevel() == 8) {
                                SoundManager.winSound();
                                JOptionPane.showMessageDialog(this, "🎉 You Win!");

                                gameTimer.stop();

                                if (main.getCurrentUser() != null) {
                                    DatabaseManager.saveGameRecord(
                                            main.getCurrentUser().getUsername(),
                                            score,
                                            levelManager.getCurrentLevel()
                                    );
                                }

                                main.showPanel("MainMenu");
                                return;
                            }

                            levelManager.nextLevel();

                            JOptionPane.showMessageDialog(this, "Level " + levelManager.getCurrentLevel());

                            loadLevel();
                            return;
                        }

                    }

                    break;
                }
            }
        }

        for (int i = eggs.size() - 1; i >= 0; i--) {
            Egg egg = eggs.get(i);

            egg.update();

            if (egg.isOut()) {
                eggs.remove(i);
                continue;
            }
            if (egg.getBounds().intersects(plane.getBounds())) {

                eggs.remove(i);

                if (!plane.hasShield()) {

                    explosions.add(new Explosion(
                            plane.getBounds().x + 30,
                            plane.getBounds().y + 30));

                    plane.damage();

                    if (plane.getLives() <= 0) {
                        gameOver();
                    }
                }
            }
        }
        for (int i = powerUps.size() - 1; i >= 0; i--) {

            PowerUp p = powerUps.get(i);

            p.update();

            if (p.isOut()) {
                powerUps.remove(i);
                continue;
            }

            if (p.getBounds().intersects(plane.getBounds())) {

                switch (p.getType()) {

                    case ADD_SHOT:
                        plane.incrementFirePower();
                        break;

                    case HEAL:
                        plane.addLife();
                        break;

                    case FAST_SHOT:
                        plane.activateFastShot();
                        break;

                    case SHIELD:
                        plane.activateShield();
                        break;

                    case FREEZE:
                        freezeEndTime = System.currentTimeMillis() + 5000;
                        break;

                    case BOMB:
                        powerUps.remove(i);
                        eggs.clear();
                        for (int k = enemies.size() - 1; k >= 0; k--) {

                            Enemy enemy = enemies.get(k);

                            explosions.add(
                                    new Explosion(
                                            enemy.getX() + 25,
                                            enemy.getY() + 25
                                    )
                            );
                            SoundManager.explosionSound();
                            switch (enemy.getType()) {

                                case NORMAL -> score += 10;
                                case FAST -> score += 15;
                                case ZIGZAG -> score += 20;
                                case SHOOTER -> score += 25;
                                case BOSS -> score += 500;
                            }

                            enemies.remove(k);
                        }


                        if (levelManager.getCurrentLevel() == 8) {
                            SoundManager.winSound();
                            JOptionPane.showMessageDialog(this, "🎉 You Win!");

                            gameTimer.stop();

                            if (main.getCurrentUser() != null) {

                                DatabaseManager.saveGameRecord(
                                        main.getCurrentUser().getUsername(),
                                        score,
                                        levelManager.getCurrentLevel()
                                );

                            }

                            main.showPanel("MainMenu");
                            return;
                        }

                        levelManager.nextLevel();

                        JOptionPane.showMessageDialog(
                                this,
                                "Level " + levelManager.getCurrentLevel()
                        );

                        loadLevel();

                        return;


                }

                powerUps.remove(i);
            }
        }

        for (int i = explosions.size() - 1; i >= 0; i--) {

            Explosion ex = explosions.get(i);

            ex.update();

            if (ex.isFinished()) {

                explosions.remove(i);

            }
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (levelManager.getCurrentLevel() == 4 ||
                levelManager.getCurrentLevel() == 8) {

            g2d.drawImage(ImageLoader.background2,
                    0, 0,
                    getWidth(), getHeight(),
                    null);

        } else {

            g2d.drawImage(ImageLoader.background, 0, 0, getWidth(), getHeight(), null);
        }

        for (ChickenInvaders.Enemy enemy : enemies) {

            enemy.draw(g2d);

        }



        for (Egg egg : eggs) {

            egg.draw(g2d);

        }
        for (PowerUp p : powerUps) {
            p.draw(g2d);
        }
        for (Explosion ex : explosions) {

            ex.draw(g2d);

        }
        if (plane != null) plane.draw(g2d);
        for (Bullet b : bullets) b.draw(g2d);


        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, 800, 40);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));

        if (main.getCurrentUser() != null) {
            g2d.drawString("👤 " + main.getCurrentUser().getUsername(), 20, 25);
        }
        g2d.drawString("Score: " + score, 180, 25);
        g2d.drawString("Level: " + levelManager.getCurrentLevel(),340,25);
        g2d.drawString("🔥 Fire: x" + (plane != null ? plane.getFirePower() : 1), 500, 25);
        g2d.drawString("❤️ Lives: " + (plane != null ? plane.getLives() : 3), 680, 25);


        if (isPaused) {
            g2d.setColor(new Color(0, 0, 0, 180));
            g2d.fillRect(0, 0, 800, 600);
            g2d.setColor(Color.ORANGE);
            g2d.setFont(new Font("Arial", Font.BOLD, 36));
            g2d.drawString("PAUSED", 330, 280);
        }
    }
    private void moveEnemyGrid() {

        boolean hitWall = false;

        for (Enemy enemy : enemies) {

            if (enemy.getX() <= 0 ||
                    enemy.getX() + 50 >= getWidth()) {

                hitWall = true;
                break;
            }

        }

        if (hitWall) {

            enemyDirection *= -1;

            for (Enemy enemy : enemies) {
                enemy.move(0, enemyStepDown);
            }

        }

        for (Enemy enemy : enemies) {

            enemy.move(enemyDirection * enemySpeed, 0);

        }

    }
    private void loadLevel() {

        enemies.clear();
        bullets.clear();
        eggs.clear();
        powerUps.clear();
        explosions.clear();

        if (levelManager.getCurrentLevel() == 4 ||
                levelManager.getCurrentLevel() == 8) {

            enemies.add(
                    EnemyFactory.createEnemy(
                            levelManager.getCurrentLevel(),
                            310,
                            50
                    )
            );

            return;
        }

        for (int r = 0; r < 5; r++) {

            for (int c = 0; c < 8; c++) {

                int x = 70 + c * 80;
                int y = 60 + r * 60;

                enemies.add(
                        EnemyFactory.createEnemy(
                                levelManager.getCurrentLevel(),
                                x,
                                y
                        )
                );
            }
        }
    }
    private void gameOver() {

        gameTimer.stop();

        SoundManager.gameOverSound();

        JOptionPane.showMessageDialog(this, "Game Over!");

        if (main.getCurrentUser() != null) {

            DatabaseManager.saveGameRecord(
                    main.getCurrentUser().getUsername(),
                    score,
                    levelManager.getCurrentLevel()
            );

        }

        main.showPanel("MainMenu");
    }

}