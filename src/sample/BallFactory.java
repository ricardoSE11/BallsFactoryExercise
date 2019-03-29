package sample;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BallFactory {
    public ArrayList<IPrototype> balls;

    public BallFactory(){
        System.out.println(" --- Instantiated a Ball Factory --- ");
        balls = new ArrayList<>();
    }


    public Ball createBall(int speed, Color color, Direction direction){
        Ball ball = null;

        switch (color){
            case RED:
                ball = new Ball(speed,color,direction);
                try {
                    //System.out.println("Succesfully assigned image");
                    ball.setImage(new Image(new FileInputStream("src/Assets/image_circle_red.png")));
                    balls.add(ball);
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
                    balls.add(ball);
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }

        System.out.println("Created a " + color.toString() + " ball that's going " + direction.toString() + " with speed: " + speed);

        return ball;
    }

    public ArrayList<Ball> getPrototype(int speed, Color color, Direction direction,
                                        int total_of_clones){
        ArrayList<Ball> cloneBalls = new ArrayList<>();
        for(int i = 0; i < total_of_clones; i++){
            Ball cloneBall = (Ball) this.balls.get(i).clone();
           cloneBalls.add(cloneBall);
        }
        for(Ball ball : cloneBalls){
            ball.setColor(color);
            ball.setDirection(direction);
            ball.setSpeed(speed);
            switch (color){
                case RED:
                    try {
                        //System.out.println("Succesfully assigned image");
                        ball.setImage(new Image(new FileInputStream("src/Assets/image_circle_red.png")));
                        balls.add(ball);
                    }
                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;

                case BLUE:
                    try {
                        //System.out.println("Succesfully assigned image");
                        ball.setImage(new Image(new FileInputStream("src/Assets/image_circle_blue.png")));
                        balls.add(ball);
                    }
                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        return cloneBalls;
    }

}
