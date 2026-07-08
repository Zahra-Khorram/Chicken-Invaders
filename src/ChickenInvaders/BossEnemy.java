package ChickenInvaders;

import java.awt.*;

public class BossEnemy extends Enemy {

    private boolean finalBoss;

    private int direction = 1;
    private int verticalDirection = 1;
    private long lastShotTime = System.currentTimeMillis();
    private long shootDelay;
    private int maxHealth;

    public BossEnemy(int x, int y, boolean finalBoss) {

        super(
                x,
                y,
                finalBoss ? 100 : 50,
                EnemyType.BOSS
        );

        this.finalBoss = finalBoss;

        this.maxHealth = health;

        if (finalBoss) {
            shootDelay = 1000;
        } else {
            shootDelay = 1500;
        }

        if(finalBoss){

            width = 180;
            height = 180;

        }else{

            width = 140;
            height = 140;

        }

    }

    @Override
    public void update() {

        x += direction * 2;

        if(x <= 0 || x + width >= 800){

            direction *= -1;

        }

        y += verticalDirection;

        if(y <= 40 || y >= 140){

            verticalDirection *= -1;

        }

    }

    @Override
    public void draw(Graphics2D g) {

        if(finalBoss){

            if(ImageLoader.boss2 != null){

                g.drawImage(ImageLoader.boss2,
                        x,y,width,height,null);

            }

        }else{

            if(ImageLoader.boss1 != null){

                g.drawImage(ImageLoader.boss1,
                        x,y,width,height,null);

            }

        }

        drawHealthBar(g);

    }

    private void drawHealthBar(Graphics2D g){

        int barWidth = 250;

        int current =
                (int)((health/(double)maxHealth)*barWidth);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(270,80,barWidth,18);

        g.setColor(Color.RED);
        g.fillRect(270,80,current,18);

        g.setColor(Color.WHITE);
        g.drawRect(270,80,barWidth,18);

    }
    public java.util.ArrayList<Egg> shoot() {

        java.util.ArrayList<Egg> eggs = new java.util.ArrayList<>();

        long now = System.currentTimeMillis();

        if (now - lastShotTime < shootDelay) {
            return eggs;
        }

        lastShotTime = now;

        int centerX = x + width / 2;
        int centerY = y + height / 2;

        if (finalBoss) {

            double speed = 5;

            eggs.add(new Egg(centerX, centerY, 0, -speed));
            eggs.add(new Egg(centerX, centerY, 0, speed));
            eggs.add(new Egg(centerX, centerY, speed, 0));
            eggs.add(new Egg(centerX, centerY, -speed, 0));

            eggs.add(new Egg(centerX, centerY, speed, speed));
            eggs.add(new Egg(centerX, centerY, -speed, speed));
            eggs.add(new Egg(centerX, centerY, speed, -speed));
            eggs.add(new Egg(centerX, centerY, -speed, -speed));

        } else {

            double speed = 4;

            eggs.add(new Egg(centerX, centerY, 0, -speed));
            eggs.add(new Egg(centerX, centerY, 0, speed));
            eggs.add(new Egg(centerX, centerY, speed, 0));
            eggs.add(new Egg(centerX, centerY, -speed, 0));

        }

        return eggs;
    }
}