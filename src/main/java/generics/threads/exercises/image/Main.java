package generics.threads.exercises.image;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {

    int width;
    int height;
    GraphicsContext g;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        width = 1100;
        height = 500;

        Canvas canvas = new Canvas(width,height);
        g = canvas.getGraphicsContext2D();

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i =0; i< height; i++){
            executorService.execute(new RowDrawer(i,Color.BLUE));
        }

        /*while(!executorService.isTerminated()){}*/



        BorderPane root = new BorderPane(canvas);
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    }

    //can't call the fillRect from two threads simultaneously, gives rise to an exception
    private synchronized void drawOneRow( int rowNumber,Color color) {
        for (int i = 0; i < width; i++) {
            // Color an individual pixel by filling in a 1-by-1 pixel
            // rectangle.  Not the most efficient way to do this, but
            // good enough for this demo.
            g.setFill(color);
            g.fillRect(i,rowNumber,1,1);
        }
    }


    public class RowDrawer implements Runnable{
        int row;
        Color color;

        public RowDrawer(int row, Color color){
            this.row = row;
            this.color = color;
        }

        public void run(){
           drawOneRow(row,color);
        }


    }



}
