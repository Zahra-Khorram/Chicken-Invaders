package ChickenInvaders;

import java.awt.*;

public class Egg {

    private int x;
    private int y;

    private final int speed = 4;

    private final int width = 12;
    private final int height = 16;

    public Egg(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        y += speed;
    }

    public void draw(Graphics2D g) {

        if (ImageLoader.egg != null) {

            g.drawImage(ImageLoader.egg, x, y, width, height, null);

        } else {

            g.setColor(Color.YELLOW);
            g.fillOval(x, y, width, height);

        }

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isOut() {
        return y > 600;
    }

}