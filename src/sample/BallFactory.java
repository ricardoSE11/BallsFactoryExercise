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
        BallListImpl blueUBalls = new BallListImpl();
        blueUBalls.setName("BlueUp");
        prototypes.put("BlueUp",blueUBalls);
        BallListImpl redUBalls = new BallListImpl();
        redUBalls.setName("RedUp");
        prototypes.put("RedUp",redUBalls);

        BallListImpl blueDBalls = new BallListImpl();
        blueDBalls.setName("BlueDown");
        prototypes.put("BlueDown",blueDBalls);
        BallListImpl redDBalls = new BallListImpl();
        redDBalls.setName("RedDown");
        prototypes.put("RedDown",redDBalls);

        BallListImpl blueRBalls = new BallListImpl();
        blueRBalls.setName("BlueRight");
        prototypes.put("BlueRight",blueRBalls);
        BallListImpl redRBalls = new BallListImpl();
        redRBalls.setName("RedRight");
        prototypes.put("RedRight",redRBalls);

        BallListImpl blueLBalls = new BallListImpl();
        blueLBalls.setName("BlueLeft");
        prototypes.put("BlueLeft",blueLBalls);
        BallListImpl redLBalls = new BallListImpl();
        redLBalls.setName("RedLeft");
        prototypes.put("RedLeft",redLBalls);
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
                    BallListImpl r;
                    switch (direction){
                        case UP:
                            r = (BallListImpl)prototypes.get("RedUp");
                            r.addBall(ball);
                            prototypes.replace("RedUp",r);//I'm not sure how disaster is this
                            break;

                        case DOWN:
                            r = (BallListImpl)prototypes.get("RedDown");
                            r.addBall(ball);
                            prototypes.replace("RedDown",r);//I'm not sure
                            //how disaster is this
                            break;

                        case RIGHT:
                            r = (BallListImpl)prototypes.get("RedRight");
                            r.addBall(ball);
                            prototypes.replace("RedRight",r);//I'm not sure
                            //how disaster is this
                            break;

                        case LEFT:
                            r = (BallListImpl)prototypes.get("RedLeft");
                            r.addBall(ball);
                            prototypes.replace("RedLeft",r);//I'm not sure
                            //how disaster is this
                            break;
                    }

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
                    BallListImpl b;
                    switch (direction){
                        case UP:
                            b = (BallListImpl)prototypes.get("BlueUp");
                            b.addBall(ball);
                            prototypes.replace("BlueUp",b);//I'm not sure how disaster is this
                            break;

                        case DOWN:
                            b = (BallListImpl)prototypes.get("BlueDown");
                            b.addBall(ball);
                            prototypes.replace("BlueDown",b);//I'm not sure
                            //how disaster is this
                            break;

                        case RIGHT:
                            b = (BallListImpl)prototypes.get("BlueRight");
                            b.addBall(ball);
                            prototypes.replace("BlueRight", b);//I'm not sure
                            //how disaster is this
                            break;

                        case LEFT:
                            b = (BallListImpl)prototypes.get("BlueLeft");
                            b.addBall(ball);
                            prototypes.replace("BlueLeft",b);//I'm not sure
                            //how disaster is this
                            break;
                    }
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }

        System.out.println("Created a " + color.toString() + " ball that's going " + direction.toString() + " with speed: " + speed);

        return ball;
    }

    public IPrototype getPrototype(String prototypeName){
        BallListImpl cloneBalls = (BallListImpl)
                prototypes.get(prototypeName).deepClone();
        BallListImpl currentBalls = (BallListImpl)
                prototypes.get(prototypeName);
        for(Ball ball : cloneBalls.getBalls()){
            currentBalls.addBall(ball);
        }
        prototypes.replace(prototypeName,currentBalls);
        return cloneBalls;
    }

    public void addPrototype(String prototypeName,
                                    IPrototype prototype){
        prototypes.put(prototypeName,prototype);
    }

}
