package ChickenInvaders;

import java.awt.*;

public class ZigzagEnemy extends Enemy {

    private double angle = 0;

    public ZigzagEnemy(int x, int y, int health) {
        super(x, y, health, EnemyType.ZIGZAG);
    }

    @Override
    public void update() {

        angle += 0.15;
        y += (int)(Math.sin(angle) * 2);

    }

    @Override
    public void draw(Graphics2D g) {

        if (ImageLoader.zigzag_chicken != null) {

            g.drawImage(
                    ImageLoader.zigzag_chicken,
                    x,
                    y,
                    width,
                    height,
                    null
            );

        } else {

            g.setColor(Color.GREEN);
            g.fillOval(x, y, width, height);

        }

    }
}