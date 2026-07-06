package ChickenInvaders;

import java.awt.*;

public class PowerUp {

    private int x;
    private int y;

    private int width = 30;
    private int height = 30;

    private int speed = 3;

    private PowerUpType type;

    public PowerUp(int x, int y, PowerUpType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void update() {
        y += speed;
    }

    public void draw(Graphics2D g) {

        switch (type) {

            case ADD_SHOT ->
                    g.drawImage(ImageLoader.p1_add_shot, x, y, width, height, null);

            case FAST_SHOT ->
                    g.drawImage(ImageLoader.p1_fast_shot, x, y, width, height, null);

            case HEAL ->
                    g.drawImage(ImageLoader.p1_heal, x, y, width, height, null);

            case SHIELD ->
                    g.drawImage(ImageLoader.p1_shield, x, y, width, height, null);

            case FREEZE ->
                    g.drawImage(ImageLoader.p1_freeze, x, y, width, height, null);

            case BOMB ->
                    g.drawImage(ImageLoader.p1_bomb_shot, x, y, width, height, null);

        }

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isOut() {
        return y > 600;
    }

    public PowerUpType getType() {
        return type;
    }

}