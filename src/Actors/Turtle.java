package Actors;

/**
 * Turtle object that implement the actor.
 * 
 * @author Vincent
 *
 */
public class Turtle extends Actor{    

    /**
     * Constructor using image path
     * 
     * @param imageFilePath
     */
    public Turtle (String imageFilePath) {
        super(imageFilePath);
    }

    /**
     * Constructor using coordinates and image path
     * 
     * @param x
     * @param y
     * @param imageFilePath
     */
    public Turtle (int x, int y, String imageFilePath) {
        super(x,y, imageFilePath);
    }
}
