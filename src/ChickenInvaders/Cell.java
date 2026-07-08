package ChickenInvaders;

import java.util.Random;

public class Cell {
    private Enemy enemy;
    private EnemyType type;
    private int remainingLives;
    private final int row;
    private final int col;
    private final int targetX;
    private final int targetY;
    private boolean isFirstSpawn = true;
    private static final Random random = new Random();
    private int currentLevel; // ذخیره مرحله برای تشخیص نوع باس

    public Cell(int row, int col, int targetX, int targetY, EnemyType type, int lives, int currentLevel) {
        this.row = row;
        this.col = col;
        this.targetX = targetX;
        this.targetY = targetY;
        this.type = type;
        this.remainingLives = lives;
        this.currentLevel = currentLevel;

        spawnEnemy();
    }

    public void spawnEnemy() {
        int startX;
        int startY = -50;

        if (isFirstSpawn) {
            startX = targetX;
            startY = targetY;
            isFirstSpawn = false;
        } else {
            startX = random.nextBoolean() ? 0 : 800;
        }

        switch (type) {
            case NORMAL -> enemy = new NormalEnemy(startX, startY, 1);
            case FAST -> enemy = new FastEnemy(startX, startY, 1);
            case ZIGZAG -> enemy = new ZigzagEnemy(startX, startY, 1);
            case SHOOTER -> enemy = new ShooterEnemy(startX, startY, 1);
            case BOSS -> {
                if (currentLevel == 8) {
                    // غول آخر: جان ۳۰۰، سرعت ۲.۰، شلیک ۸ جهته (ارسال لول ۸ به سازنده غول)
                    enemy = new BossEnemy(startX, startY, 300, 2.0, 8);
                } else {
                    // غول مرحله ۴: جان ۱۰۰، سرعت ۱.۵، شلیک ۴ جهته
                    enemy = new BossEnemy(startX, startY, 100, 1.5, 4);
                }
            }
        }

        if (enemy != null) {
            enemy.setTarget(targetX, targetY);
        }
    }

    public void enemyKilled() {
        remainingLives--;
        if (remainingLives > 0) {
            spawnEnemy();
        } else {
            enemy = null;
        }
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public boolean isFinished() {
        return remainingLives <= 0;
    }
}