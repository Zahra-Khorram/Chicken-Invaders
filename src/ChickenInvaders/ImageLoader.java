package ChickenInvaders;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageLoader {
    // backgrounds
    public static BufferedImage background;
    public static BufferedImage background2;

    // airplane (تغییر نام اعداد به متن برای رفع ارور جاوا)
    public static BufferedImage plane1;
    public static BufferedImage plane2;
    public static BufferedImage plane3;
    public static BufferedImage plane4;
    public static BufferedImage plane5;
    public static BufferedImage plane6;
    public static BufferedImage plane7;
    public static BufferedImage planeExplosion;
    public static BufferedImage chickenExplosion;
    public static BufferedImage shot;

    // chicken
    public static BufferedImage normal_chicken;
    public static BufferedImage double_normal_chicken; // تغییر نام متغیر عددی
    public static BufferedImage fast_chicken;
    public static BufferedImage zigzag_chicken;
    public static BufferedImage shooter_chicken;
    public static BufferedImage boss1;
    public static BufferedImage boss2;
    public static BufferedImage egg;

    // powerup1 (اضافه کردن پیشوند برای رفع ارور نام تکراری)
    public static BufferedImage p1_add_shot;
    public static BufferedImage p1_bomb_shot;
    public static BufferedImage p1_fast_shot;
    public static BufferedImage p1_freeze;
    public static BufferedImage p1_heal;
    public static BufferedImage p1_shield;

    // powerup2 (اضافه کردن پیشوند برای رفع ارور نام تکراری)
    public static BufferedImage p2_add_shot;
    public static BufferedImage p2_bomb_shot;
    public static BufferedImage p2_fast_shot;
    public static BufferedImage p2_freeze;
    public static BufferedImage p2_heal;
    public static BufferedImage p2_shield;

    public static void loadAllImages() {

        background  = loadImage("/images/background/background.png");
        background2 = loadImage("/images/background/background2.png");


        plane1           = loadImage("/images/airplane/1.png");
        plane2           = loadImage("/images/airplane/2.png");
        plane3           = loadImage("/images/airplane/3.png");
        plane4           = loadImage("/images/airplane/4.png");
        plane5           = loadImage("/images/airplane/5.png");
        plane6           = loadImage("/images/airplane/6.png");
        plane7           = loadImage("/images/airplane/7.png");
        planeExplosion   = loadImage("/images/airplane/Explosion.png");
        chickenExplosion = loadImage("/images/airplane/Explosion2.png");
        shot             = loadImage("/images/airplane/shot.png");

        //chicken
        normal_chicken        = loadImage("/images/chicken/normal_chicken.png");
        double_normal_chicken = loadImage("/images/chicken/2xnormal_chicken.png");
        fast_chicken          = loadImage("/images/chicken/fast_chicken.png");
        zigzag_chicken        = loadImage("/images/chicken/zigzag_chicken.png");
        shooter_chicken       = loadImage("/images/chicken/shooter_chicken.png");
        boss1                 = loadImage("/images/chicken/boss1.png");
        boss2                 = loadImage("/images/chicken/boss2.png");
        egg                   = loadImage("/images/chicken/egg.png");

        // powerup1
        p1_add_shot  = loadImage("/images/powerup1/add_shot.png");
        p1_bomb_shot = loadImage("/images/powerup1/bomb_shot.png");
        p1_fast_shot = loadImage("/images/powerup1/fast_shot.png");
        p1_freeze    = loadImage("/images/powerup1/freeze.png");
        p1_heal      = loadImage("/images/powerup1/heal.png");
        p1_shield    = loadImage("/images/powerup1/shield.png");

        //powerup2
        p2_add_shot  = loadImage("/images/powerup2/add_shot.png");
        p2_bomb_shot = loadImage("/images/powerup2/bomb_shot.png");
        p2_fast_shot = loadImage("/images/powerup2/fast_shot.png");
        p2_freeze    = loadImage("/images/powerup2/freeze.png");
        p2_heal      = loadImage("/images/powerup2/heal.png");
        p2_shield    = loadImage("/images/powerup2/shield.png");
    }

    private static BufferedImage loadImage(String path) {
        System.out.println(ImageLoader.class.getResource("/"));

        try {
            URL url = ImageLoader.class.getResource(path);
            if (url == null) {
                System.err.println("not found in " + path);
                return null;
            }
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}