package ChickenInvaders;

import java.awt.*;

public class NormalEnemy extends Enemy {

    public NormalEnemy(int x, int y, int health) {
        super(x, y, health, EnemyType.NORMAL);
    }

    @Override
    public void update() {
        if (isFlyingToTarget) {
            flyToTarget();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        if (ImageLoader.normal_chicken != null) {
            g.drawImage(
                    ImageLoader.normal_chicken,
                    x,
                    y,
                    width,
                    height,
                    null
            );
        } else {
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, width, height);
        }
    }
}