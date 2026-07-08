package ChickenInvaders;

import java.awt.*;

public abstract class Enemy {

    protected int x;
    protected int y;

    protected int width = 50;
    protected int height = 50;

    protected int health;
    protected EnemyType type;

    protected int targetX;
    protected int targetY;
    protected boolean isFlyingToTarget = false;
    protected double flySpeed = 3.0;

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

    public void setTarget(int targetX, int targetY) {
        this.targetX = targetX;
        this.targetY = targetY;
        if (this.x != targetX || this.y != targetY) {
            this.isFlyingToTarget = true;
        }
    }

    protected void flyToTarget() {
        if (!isFlyingToTarget) return;

        double dx = targetX - x;
        double dy = targetY - y;
        double distance = Math.hypot(dx, dy);

        if (distance < flySpeed) {
            x = targetX;
            y = targetY;
            isFlyingToTarget = false;
        } else {
            x += (int) ((dx / distance) * flySpeed);
            y += (int) ((dy / distance) * flySpeed);
        }
    }

    public boolean isFlyingToTarget() {
        return isFlyingToTarget;
    }
}