package ChickenInvaders;

import java.awt.*;
        import java.util.ArrayList;
import java.util.List;

public class Plane {
    private int x, y;
    private int width = 60, height = 60;
    private int speed = 5;

    private int lives = 3;
    private int maxLives = 5;
    private int firePower = 1;

    private long lastShotTime = 0;
    private final long shotDelay = 300;

    public Plane(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(boolean left, boolean right, boolean up, boolean down) {
        if (left && x > 0) x -= speed;
        if (right && x < 800 - width) x += speed;
        if (up && y > 40) y -= speed;
        if (down && y < 600 - height) y += speed;
    }

    public List<Bullet> shoot() {
        List<Bullet> newBullets = new ArrayList<>();
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastShotTime >= shotDelay) {
            lastShotTime = currentTime;

            if (firePower == 1) {

                newBullets.add(new Bullet(x + width / 2 - 2, y));
            } else {

                int spread = 15;
                int startX = (x + width / 2) - ((firePower - 1) * spread) / 2;
                for (int i = 0; i < firePower; i++) {
                    newBullets.add(new Bullet(startX + (i * spread) - 2, y));
                }
            }
        }
        return newBullets;
    }

    public void draw(Graphics2D g2d) {
        if (ImageLoader.plane1 != null) {
            g2d.drawImage(ImageLoader.plane1, x, y, width, height, null);
        } else {
            g2d.setColor(Color.CYAN);
            g2d.fillRect(x, y, width, height);
        }
    }

    public void addLife() { if (lives < maxLives) lives++; }
    public void damage() {
        if (lives > 0) {
            lives--;
        }
    }
    public int getLives() { return lives; }
    public void incrementFirePower() { firePower++; }
    public int getFirePower() { return firePower; }
    public Rectangle getBounds() { return new Rectangle(x, y, width, height); }
}