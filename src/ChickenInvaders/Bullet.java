package ChickenInvaders;

import java.awt.*;
import java.awt.image.BufferedImage;
public class Bullet {
    private BufferedImage image;
    private int x, y;
    private int speed = 7;
    private int width = 4, height = 15;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
        image = ImageLoader.shot;
    }

    public void update() {
        y -= speed;
    }

    public void draw(Graphics2D g2d) {

        if (image != null) {

            g2d.drawImage(image, x, y, 20, 35, null);

        } else {

            g2d.setColor(Color.RED);
            g2d.fillRect(x, y, width, height);

        }
    }

    public boolean isOutOfBounds() {
        return y < 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}