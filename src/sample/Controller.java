package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    // Vars
    private int current_ball_amount;
    private int current_ball_speed;
    private Color ball_color;
    private Direction direction;

    private ArrayList<Ball> existing_balls;
    private BallFactory factory = new BallFactory();

    // Utility vars
    private Random random;

    // GUI Vars
    Stage stage;
    GraphicsContext gContext;
    Image backgroundImage;

    Image redBallImage;
    Image blueBallImage;
    Image choosenImage;

    {
        try {
            backgroundImage = new Image(new FileInputStream("src/Assets/background.png"));
            redBallImage = new Image(new FileInputStream("src/Assets/image_circle_red.png"));
            blueBallImage = new Image(new FileInputStream("src/Assets/image_circle_blue.png"));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML public ComboBox cmb_color;
    @FXML public ComboBox cmb_direction;

    @FXML public TextField txf_ball_amount;
    @FXML public TextField txf_balls_speed;

    @FXML public Button btn_factory;
    @FXML public Button btn_no_pattern;

    @FXML public Canvas balls_canvas;


    // Setting values for the combo boxes
    ObservableList<String> color_list = FXCollections.observableArrayList("Red" , "Blue");
    ObservableList<String> direction_list = FXCollections.observableArrayList("Up" , "Down" , "Right" , "Left");

    public void init(Stage stage){
        System.out.println("Setting up the stage");
        this.stage = stage;
        existing_balls = new ArrayList<>();
    }

    // Used to set the combo boxes
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmb_color.setItems(color_list);
        cmb_direction.setItems(direction_list);

    }

    //TODO: Recordar que el start() es mágico y evita que la GUI se pegue :)

    public void createBallsWithFactory(){
        String choosenColor = cmb_color.getValue().toString();
        String choosenDirection = cmb_direction.getValue().toString();

        current_ball_amount = Integer.parseInt(txf_ball_amount.getText());
        current_ball_speed = Integer.parseInt(txf_balls_speed.getText());

        switch (choosenDirection){
            case "Up":
                this.direction = Direction.UP;
                break;

            case "Down":
                this.direction = Direction.DOWN;
                break;

            case "Right":
                this.direction = Direction.RIGHT;
                break;

            case "Left":
                this.direction = Direction.LEFT;
                break;
        }


        switch (choosenColor){
            case "Red":
                this.ball_color = Color.RED;
                break;

            case "Blue":
                this.ball_color = Color.BLUE;
                break;
        }

        for (int i = 0 ; i < current_ball_amount ; i++){
            Ball newBall = factory.createBall(current_ball_speed , ball_color ,direction);
            newBall.start();
        }


        //txf_ball_amount.setText(" ");
        //txf_balls_speed.setText(" ");
    }

    public void createBallWithNoPattern(){
        String choosenColor = cmb_color.getValue().toString();
        String choosenDirection = cmb_direction.getValue().toString();

        System.out.println("I AM BEING USED");

        current_ball_amount = Integer.parseInt(txf_ball_amount.getText());
        current_ball_speed = Integer.parseInt(txf_balls_speed.getText());

        switch (choosenDirection){
            case "Up":
                this.direction = Direction.UP;
                break;

            case "Down":
                this.direction = Direction.DOWN;
                break;

            case "Right":
                this.direction = Direction.RIGHT;
                break;

            case "Left":
                this.direction = Direction.LEFT;
                break;
        }


        switch (choosenColor){
            case "Red":
                this.ball_color = Color.RED;
                this.choosenImage = redBallImage;
                break;

            case "Blue":
                this.ball_color = Color.BLUE;
                this.choosenImage = blueBallImage;
                break;
        }


        for (int i = 0 ; i < current_ball_amount ; i++){
            Ball newBall = new Ball(current_ball_speed , ball_color , direction , choosenImage);

            this.factory.balls.add(newBall);
            newBall.start();
        }
    }

    public void createBallsWithPrototype(){
        String choosenColor = cmb_color.getValue().toString();
        String choosenDirection = cmb_direction.getValue().toString();

        current_ball_amount = Integer.parseInt(txf_ball_amount.getText());
        current_ball_speed = Integer.parseInt(txf_balls_speed.getText());

        switch (choosenDirection){
            case "Up":
                this.direction = Direction.UP;
                break;

            case "Down":
                this.direction = Direction.DOWN;
                break;

            case "Right":
                this.direction = Direction.RIGHT;
                break;

            case "Left":
                this.direction = Direction.LEFT;
                break;
        }


        switch (choosenColor){
            case "Red":
                this.ball_color = Color.RED;
                this.choosenImage = redBallImage;
                break;

            case "Blue":
                this.ball_color = Color.BLUE;
                this.choosenImage = blueBallImage;
                break;
        }

        int current_total_balls = factory.balls.size();
        int missingBalls = current_ball_amount - current_total_balls;
        if(missingBalls > 0){
            for (int i = 0 ; i < missingBalls ; i++){
                Ball newBall = factory.createBall(current_ball_speed , ball_color ,direction);
                newBall.start();
            }
            if(current_total_balls > 0){
                ArrayList<Ball> newBalls = factory.getPrototype(current_ball_speed,
                        ball_color,direction,current_total_balls);
                for(Ball ball : newBalls){
                    ball.start();
                }
            }
        }else{
            ArrayList<Ball> newBalls = factory.getPrototype(current_ball_speed,
                    ball_color,direction,current_ball_amount);
            for(Ball ball : newBalls){
                ball.start();
            }
        }


    }

    public void draw(){
        balls_canvas.setWidth(504);
        balls_canvas.setHeight(326);
        gContext = this.balls_canvas.getGraphicsContext2D();
        gContext.clearRect(0 , 0 , 504 , 326);
        gContext.drawImage(backgroundImage , 0 , 0);

        for (int i = 0; i < this.factory.balls.size(); i++){
            Ball currentBall = (Ball)this.factory.balls.get(i);
            gContext.drawImage(currentBall.getImage() , currentBall.getX_position() , currentBall.getY_position());
        }
    }


}
