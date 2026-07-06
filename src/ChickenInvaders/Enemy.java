package ChickenInvaders;

import java.awt.*;

public abstract class Enemy {

    protected int x;
    protected int y;

    protected int width = 50;
    protected int height = 50;

    protected int health;
    protected EnemyType type;

    public Enemy(int x, int y, int health, EnemyType type) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.type = type;
    }

    public abstract void update();

    public abstract void draw(Graphics2D g);

    public void damage() {
        health--;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public EnemyType getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}