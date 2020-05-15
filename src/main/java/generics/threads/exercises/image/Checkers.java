package generics.threads.exercises.image;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Checkers extends Application {

    int width;
    int height;
    GraphicsContext g;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        width = 1100;
        height = 500;
        Canvas canvas = new Canvas(width,height);
        g = canvas.getGraphicsContext2D();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Color color = Color.GREEN;
        for(int i =0; i< 8; i++){
            if(color == Color.GREEN){
                color = Color.RED;
            }else{
                color = Color.GREEN;
            }
            for(int j=0; j<8; j++) {
                executorService.execute(new SquarePainter(i*width/8, j*height/8, width/8, height / 8, color));
                if(color == Color.GREEN){
                    color = Color.RED;
                }else{
                    color = Color.GREEN;
                }
            }
        }

        BorderPane root = new BorderPane(canvas);
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    }

    //having to synchronize here might be a problem since effectively only one thread is painting while the
    //others wait, the idea would be to have several threads painting at the same time to speed up the process
    private synchronized void paintSquare(int initialX,int initialY,int width,int height, Color color){
        g.setFill(color);
        g.fillRect(initialX,initialY,width,height);
    }

    private class SquarePainter implements Runnable{
        int initialX;
        int initialY;
        int width;
        int height;
        Color color;

        public  SquarePainter(int initialX,int initialY,int width,int height,Color color){
            this.initialX = initialX;
            this.initialY = initialY;
            this.width = width;
            this.height = height;
            this.color = color;
        }

        public void run(){
            paintSquare(this.initialX,this.initialY,this.width,this.height,this.color);
        }

    }




}
