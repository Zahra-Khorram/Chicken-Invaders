package ChickenInvaders;

import java.awt.*;

public class Explosion {

    private int x;
    private int y;

    private int radius = 10;
    private int life = 20;

    public Explosion(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public void update() {

        radius += 2;
        life--;

    }

    public void draw(Graphics2D g) {

        int size = radius * 2;

        if (ImageLoader.chickenExplosion != null) {

            g.drawImage(
                    ImageLoader.chickenExplosion,
                    x,
                    y,
                    size,
                    size,
                    null
            );

        } else {

            g.setColor(Color.ORANGE);
            g.fillOval(x, y, size, size);

        }

    }

    public boolean isFinished() {

        return life <= 0;

    }

}