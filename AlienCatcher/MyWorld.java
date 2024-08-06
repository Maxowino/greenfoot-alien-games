import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    private int score;
    private static int highScore = 0;
    private GreenfootSound backgroundMusic;
    private int level;
    private int spawnInterval;
    private int alienSpeed;
    private int asteroidSpeed;
    private int spawnCounter;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        score = 0;
        level = 1; 
        spawnInterval = 100;
        alienSpeed = 1; 
        asteroidSpeed = 1; 
        spawnCounter = 0; 

        showScore();
        showHighScore();
        backgroundMusic = new GreenfootSound("spacebackground.mp3");
        startBackgroundMusic();

       
    }
    
    public void act() {
        spawnObjects();
        spawnCounter++;
        if (spawnCounter % spawnInterval == 0) {
            spawnObjects();
            spawnCounter = 0;
        }
        showScore();
        showLevel();
        checkLevelUp();
    }
    private void spawnObjects() {
    if (Greenfoot.getRandomNumber(190) < 2) {
        addObject(new Alien(alienSpeed), Greenfoot.getRandomNumber(getWidth()), 0);
    }
    if (Greenfoot.getRandomNumber(100) < 1) {
        addObject(new Asteroid(asteroidSpeed), Greenfoot.getRandomNumber(getWidth()), 0);
    }
}
public void incrementScore() {
    score++;
    showScore();
    if (score > highScore) {
            highScore = score;
            showHighScore();
        }
}

private void showHighScore() {
        showText("High score: " + highScore, getWidth() - 100, 20);
    }
     private void showLevel() {
        showText("Level: " + level, 80, 40);
    }
 private void checkLevelUp() {
    if (score >= level * 10) { 
        level++;
        spawnInterval = Math.max(50, spawnInterval - 50);
        alienSpeed++;
        asteroidSpeed++;
    }
}
private void showScore() {
    showText("My score: " + score, 70, 20);
}
public void startBackgroundMusic() {
        backgroundMusic.playLoop();
    }

    public void stopBackgroundMusic() {
        backgroundMusic.stop();
    }
}
