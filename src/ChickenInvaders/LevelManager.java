package ChickenInvaders;

public class LevelManager {

    private int currentLevel = 1;

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void nextLevel() {
        if (currentLevel < 8)
            currentLevel++;
    }

    public boolean isBossLevel() {
        return currentLevel == 4 || currentLevel == 8;
    }

    public void reset() {
        currentLevel = 1;
    }
}