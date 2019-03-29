package sample;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class BallFactory {
    private static HashMap<String,IPrototype> prototypes =
            new HashMap<>();

    public BallFactory(){
        System.out.println(" --- Instantiated a Ball Factory --- ");
    }

    public Ball createBall(int speed, Color color, Direction direction){
        Ball ball = null;


        switch (color){
            case RED:
                ball = new Ball(speed,color,direction);
                try {
                    //System.out.println("Succesfully assigned image");
                    ball.setImage(new Image(new FileInputStream("src/Assets/image_circle_red.png")));
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;

            case BLUE:
                ball = new Ball(speed,color,direction);
                try {
                    //System.out.println("Succesfully assigned image");
                    ball.setImage(new Image(new FileInputStream("src/Assets/image_circle_blue.png")));
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }

        System.out.println("Created a " + color.toString() + " ball that's going " + direction.toString() + " with speed: " + speed);

        return ball;
    }

    public static IPrototype getPrototype(String prototypeName){
        return prototypes.get(prototypeName).deepClone();
    }

    public static void addPrototype(String prototypeName,
                                    IPrototype prototype){
        prototypes.put(prototypeName,prototype);
    }

}
