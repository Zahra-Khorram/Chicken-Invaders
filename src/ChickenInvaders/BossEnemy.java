package ChickenInvaders;

import java.awt.*;
        import java.util.ArrayList;
import java.util.List;

public class BossEnemy extends Enemy {
    private long lastShotTime = 0;
    private long shotInterval;
    private int shootDirections;
    private double speed;

    public BossEnemy(int x, int y, int health, double speed, int level) {
        super(x, y, health, EnemyType.BOSS);
        this.speed = speed;
        this.flySpeed = speed * 2;

        if (level == 8) {
            this.shotInterval = 1000;
            this.shootDirections = 8;
        } else {
            this.shotInterval = 1500;
            this.shootDirections = 4;
        }

        this.width = 100;
        this.height = 100;
    }

    @Override
    public void update() {
        if (isFlyingToTarget) {
            flyToTarget();
        } else {

            x += speed;
            if (x <= 50 || x + width >= 750) {
                speed *= -1;
            }
        }
    }

    public List<Egg> shoot() {
        List<Egg> newEggs = new ArrayList<>();
        if (isFlyingToTarget) return newEggs;

        long now = System.currentTimeMillis();
        if (now - lastShotTime >= shotInterval) {
            lastShotTime = now;
            int centerX = x + width / 2;
            int centerY = y + height;

            if (shootDirections == 4) {

                newEggs.add(new Egg(centerX, centerY, 0, 4));
                newEggs.add(new Egg(centerX, centerY, -3, 3));
                newEggs.add(new Egg(centerX, centerY, 3, 3));
                newEggs.add(new Egg(centerX, centerY, 0, 5));
            } else if (shootDirections == 8) {

                newEggs.add(new Egg(centerX, centerY, -4, 0));
                newEggs.add(new Egg(centerX, centerY, 4, 0));
                newEggs.add(new Egg(centerX, centerY, 0, 4));
                newEggs.add(new Egg(centerX, centerY, -3, 3));
                newEggs.add(new Egg(centerX, centerY, 3, 3));
                newEggs.add(new Egg(centerX, centerY, -2, 4));
                newEggs.add(new Egg(centerX, centerY, 2, 4));
                newEggs.add(new Egg(centerX, centerY, 0, 6));
            }
        }
        return newEggs;
    }

    @Override
    public void draw(Graphics2D g) {

        if (ImageLoader.shooter_chicken != null) {
            g.drawImage(ImageLoader.shooter_chicken, x, y, width, height, null);
        } else {
            g.setColor(Color.RED);
            g.fillOval(x, y, width, height);
        }

        g.setColor(Color.RED);
        g.fillRect(x, y - 15, width, 6);
        g.setColor(Color.GREEN);
        int barWidth = (int) (((double) health / (shootDirections == 8 ? 300.0 : 100.0)) * width);
        g.fillRect(x, y - 15, barWidth, 6);
    }

}