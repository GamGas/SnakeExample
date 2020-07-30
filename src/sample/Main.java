package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.event.EventHandler;

import static sample.Direction.*;
import static sample.Direction.LEFT;

public class Main extends Application {

    public static Rectangle FOOD;
    public static int DIFFICULT = 200;


    @Override
    public void start(Stage primaryStage) throws Exception {

        Controller controller = new Controller();
        controller.generateSnake();

        Constants.ROOT = new Group();

        Constants.ROOT.getChildren().addAll(controller.snakeCells);
        Constants.SCENE = new Scene(Constants.ROOT,
                Constants.WINDOW_WIDTH,
                Constants.WINDOW_HEIGHT,
                Color.DARKSLATEGREY);

        primaryStage.setTitle("Snake Example");

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {

                        controller.move();
                        Constants.ROOT.getChildren().clear();
                        if (FOOD == null) {
                            FOOD = controller.generateFood();
                            while (controller.isTailCollideFood(FOOD)) {
                                FOOD = controller.generateFood();
                            }
                        }
                        Constants.ROOT.getChildren().add(FOOD);
                        Constants.ROOT.getChildren().addAll(controller.snakeCells);
                        if (controller.foodEaten(FOOD)) {
                            Constants.CURRENT_SNAKE_SIZE += 1;
                            FOOD = null;
                            if (DIFFICULT > 50) {
                                DIFFICULT -= 10;
                            }
                        }
                        controller.updateSnakeCells();
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(DIFFICULT);
                    } catch (InterruptedException ex) {
                    }
                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();
        Constants.SCENE.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        if (Constants.CURRENT_DIRECTION != DOWN) {
                            Constants.CURRENT_DIRECTION = UP;
                        }
                        break;
                    case RIGHT:
                        if (Constants.CURRENT_DIRECTION != LEFT) {
                            Constants.CURRENT_DIRECTION = RIGHT;
                        }
                        break;
                    case DOWN:
                        if (Constants.CURRENT_DIRECTION != UP) {
                            Constants.CURRENT_DIRECTION = DOWN;
                        }
                        break;
                    case LEFT:
                        if (Constants.CURRENT_DIRECTION != RIGHT) {
                            Constants.CURRENT_DIRECTION = LEFT;
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        primaryStage.setScene(Constants.SCENE);
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }


}
