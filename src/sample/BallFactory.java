package sample;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class BallFactory {
    private static BallFactory ballFactory = null;
    private static HashMap<String,IPrototype> prototypes =
            new HashMap<>();

    private BallFactory(){
        System.out.println(" --- Instantiated a Ball Factory --- ");
        BallListImpl blueBalls = new BallListImpl();
        blueBalls.setName("BLUE");
        prototypes.put("BLUE",blueBalls);
        BallListImpl redBalls = new BallListImpl();
        blueBalls.setName("RED");
        prototypes.put("RED",redBalls);
    }

    public static BallFactory getSingletonFactoryInstance(){
        if(ballFactory == null){
            ballFactory = new BallFactory();
        }
        return ballFactory;
    }

    public Ball createBall(int speed, Color color, Direction direction){
        Ball ball = null;


        switch (color){
            case RED:
                ball = new Ball(speed,color,direction);
                try {
                    //System.out.println("Succesfully assigned image");
                    ball.setImage(new Image(new FileInputStream("src/Assets/image_circle_red.png")));
                    BallListImpl r = (BallListImpl)prototypes.get("RED");
                    r.addBall(ball);
                    prototypes.replace("RED",r);//I'm not sure
                    //how disaster this is
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
                    BallListImpl b = (BallListImpl)prototypes.get("BLUE");
                    b.addBall(ball);
                    prototypes.replace("BLUE",b);//I'm not sure
                    //how disaster this is
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
