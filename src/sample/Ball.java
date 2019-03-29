package sample;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Ball extends Thread implements IBall{

    private double x_position;
    private double y_position;
    private int speed;
    private Color color;
    private Direction direction;
    private Image image;
    private boolean isMoving;

    int x_range = (5 - 1) + 1;
    int y_range = (3 - 1) + 1;

    public Ball(int speed, Color color, Direction direction) {
        this.x_position = 70 * ((Math.random() * x_range) + 1);
        this.y_position = 60 * ((Math.random() * y_range) + 1);
        this.speed = speed;
        this.color = color;
        this.direction = direction;
        this.isMoving = true;
    }

    public double getX_position() {
        return x_position;
    }

    public void setX_position(double x_position) {
        this.x_position = x_position;
    }

    public double getY_position() {
        return y_position;
    }

    public void setY_position(double y_position) {
        this.y_position = y_position;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    @Override
    public void run() {
        while(isMoving){
            move();

            try {
                Thread.sleep(speed);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void move() {
        switch (direction){
            case UP:
                if (this.y_position > 5){
                    setY_position(getY_position() - 2);
                }
                else {
                    double randomBounces = 50 * ((Math.random() * y_range ) + 1);
                    while (this.y_position < randomBounces){
                        setY_position(getY_position() + 2);
                        try {
                            Thread.sleep(speed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;

            case DOWN:
                if (this.y_position < 260){
                    setY_position(getY_position() + 2);
                }
                else {
                    double randomBounces = 50 * ((Math.random() * y_range ) + 1);
                    while (this.y_position > randomBounces){
                        this.y_position -= 2;
                        try {
                            Thread.sleep(speed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;

            case RIGHT:
                if (this.x_position > 5){
                    this.x_position -= 2;
                }
                else {
                    double randomBounces = 50 * ((Math.random() * x_range ) + 1);
                    while (this.x_position < randomBounces){
                        this.x_position += 2;
                        try {
                            Thread.sleep(speed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;

            case LEFT:
                if (this.x_position < 440){
                    this.x_position += 2;
                }
                else {
                    double randomBounces = 50 * ((Math.random() * x_range ) + 1);
                    while (this.x_position > randomBounces){
                        this.x_position -= 2;
                        try {
                            Thread.sleep(speed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;

        }
    }
}
