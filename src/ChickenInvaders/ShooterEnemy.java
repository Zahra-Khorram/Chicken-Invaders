package ChickenInvaders;

import java.awt.*;

public class ShooterEnemy extends Enemy {

    private long lastShot = 0;

    public ShooterEnemy(int x, int y, int health) {
        super(x, y, health, EnemyType.SHOOTER);
    }

    @Override
    public void update() {
        if (isFlyingToTarget) {
            flyToTarget();
        }
    }

    public boolean canShoot() {
        if (isFlyingToTarget) {
            return false;
        }
        long now = System.currentTimeMillis();
        if (now - lastShot > 2000) {
            lastShot = now;
            return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics2D g) {
        if (ImageLoader.shooter_chicken != null) {
            g.drawImage(
                    ImageLoader.shooter_chicken,
                    x,
                    y,
                    width,
                    height,
                    null
            );
        } else {
            g.setColor(Color.RED);
            g.fillOval(x, y, width, height);
        }
    }
}