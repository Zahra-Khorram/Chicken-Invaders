package ChickenInvaders;

import javax.sound.sampled.*;

public class SoundManager {


    public static void playSound(String path) {

        try {

            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(
                            SoundManager.class.getResource(path)
                    );

            Clip clip = AudioSystem.getClip();

            clip.open(audio);
            clip.start();

        } catch (Exception e) {

            System.out.println("Sound Error: " + e.getMessage());

        }
    }


    public static void gameOverSound() {
        playSound("/sounds/sound-effects/mixkit-retro-arcade-game-over-470.wav");
    }


    public static void explosionSound() {
        playSound("/sounds/sound-effects/mixkit-epic-impact-afar-explosion-2782.wav");
    }


    public static void shootSound() {
        playSound("/sounds/sound-effects/mixkit-short-laser-gun-shot-1670.wav");
    }

    public static void winSound() {
        playSound("/sounds/sound-effects/Chicken Invaders 2 Remastered OST - Ending Theme.wav");
    }


}