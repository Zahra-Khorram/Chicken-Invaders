package ChickenInvaders;

import java.awt.*;

public class Egg {

    private double x;
    private double y;

    private double dx;
    private double dy;

    private final int width = 12;
    private final int height = 16;

    public Egg(int x, int y) {
        this(x, y, 0, 4);
    }

    public Egg(int x, int y, double dx, double dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void draw(Graphics2D g) {

        if (ImageLoader.egg != null) {

            g.drawImage(
                    ImageLoader.egg,
                    (int)x,
                    (int)y,
                    width,
                    height,
                    null
            );

        } else {

            g.setColor(Color.YELLOW);
            g.fillOval((int)x, (int)y, width, height);

        }

    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }

    public boolean isOut() {

        return x < -20 ||
                x > 820 ||
                y < -20 ||
                y > 620;

    }
}