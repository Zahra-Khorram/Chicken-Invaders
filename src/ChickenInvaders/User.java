package ChickenInvaders;

public class User {
    private String username;
    private String password;
    private int highScore;
    private boolean bgMusic = true;
    private boolean shotSound = true;
    private boolean crashSound = true;
    private boolean gameOverSound = true;
    private String currentPlane = "Default";

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getHighScore() { return highScore; }
    public void setHighScore(int highScore) { this.highScore = highScore; }
    public boolean isBgMusic() { return bgMusic; }
    public void setBgMusic(boolean bgMusic) { this.bgMusic = bgMusic; }
    public boolean isShotSound() { return shotSound; }
    public void setShotSound(boolean shotSound) { this.shotSound = shotSound; }
    public boolean isCrashSound() { return crashSound; }
    public void setCrashSound(boolean crashSound) { this.crashSound = crashSound; }
    public boolean isGameOverSound() { return gameOverSound; }
    public void setGameOverSound(boolean gameOverSound) { this.gameOverSound = gameOverSound; }
    public String getCurrentPlane() { return currentPlane; }
    public void setCurrentPlane(String currentPlane) { this.currentPlane = currentPlane; }
}