package ChickenInvaders;

import java.awt.*;

public class FastEnemy extends Enemy {

    public FastEnemy(int x, int y, int health) {
        super(x, y, health, EnemyType.FAST);
    }

    @Override
    public void update() {
        // حرکت توسط شبکه کنترل می‌شود
    }

    @Override
    public void draw(Graphics2D g) {

        if (ImageLoader.fast_chicken != null) {

            g.drawImage(
                    ImageLoader.fast_chicken,
                    x,
                    y,
                    width,
                    height,
                    null
            );

        } else {

            g.setColor(Color.ORANGE);
            g.fillOval(x, y, width, height);

        }

    }
}