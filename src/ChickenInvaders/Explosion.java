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

        g.setColor(new Color(255,150,0, life * 12));

        g.fillOval(
                x - radius / 2,
                y - radius / 2,
                radius,
                radius
        );

    }

    public boolean isFinished() {

        return life <= 0;

    }

}