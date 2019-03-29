package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application implements Runnable{

    Controller myController;

    private Thread mainThread;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

        myController = loader.getController();
        myController.init(primaryStage);

        this.mainThread = new Thread(this);
        this.mainThread.start();

        primaryStage.setTitle("Creational Patterns Practice");
        primaryStage.setScene(new Scene(root, 540, 467));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    // Method to refresh page (?)
    @Override
    public void run() {
        long start;
        long elapsed;
        long wait;
        int fps = 30;
        long time = 1000/fps;

        while(true){
            try {
                //System.out.println(" --- Hilo consume recursos ---");
                start=System.nanoTime();
                elapsed=System.nanoTime()-start;
                wait = time-elapsed/1000000; //1000000
                Thread.sleep(wait);
                myController.draw();
            }
            catch (InterruptedException ex) {}
        }
    }
}
