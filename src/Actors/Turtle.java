package Actors;

public class Turtle extends Actor{    

    public Turtle (String imageFilePath) {
        super(imageFilePath);
    }

    public Turtle (int x, int y, String imageFilePath) {
        super(x,y, imageFilePath);
    }
}
