import greenfoot.*;
import java.util.List;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class spaceship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class spaceship extends Actor
{
    /**
     * Act - do whatever the spaceship wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootSound collisionSound;
      public void act() {
        moveSpaceship();
        checkCollision();
    }
    public spaceship()
    {
        getImage().scale(90,90);
        collisionSound = new GreenfootSound("collusion.wav.mp3");
    
    }
    private void moveSpaceship() {
        if (Greenfoot.isKeyDown("left")) {
            move(-5);
        }
        if (Greenfoot.isKeyDown("right")) {
            move(5);
        }
        
        if (getX() < 0) {
            setLocation(0, getY());
        }
        if (getX() > getWorld().getWidth()) {
            setLocation(getWorld().getWidth(), getY());
        }
    }

     private void checkCollision() {
       
        List<Alien> aliens = getIntersectingObjects(Alien.class);
        if (!aliens.isEmpty()) {
            for (Alien alien : aliens) {
                getWorld().removeObject(alien);
                ((MyWorld)getWorld()).incrementScore();
            }
        }

        
        List<Asteroid> asteroids = getIntersectingObjects(Asteroid.class);
        if (!asteroids.isEmpty()) {
            for (Asteroid asteroid : asteroids) {
                getWorld().removeObject(asteroid);
            }
            collisionSound.play();
            Greenfoot.stop();
            getWorld().showText("Game Over", getWorld().getWidth() / 2, getWorld().getHeight() / 2);
        }
    }
}
