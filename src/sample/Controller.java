package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Controller {

    public ArrayList<Rectangle> snakeCells;

    public Controller() {
        snakeCells = new ArrayList<Rectangle>();
    }


    public void generateSnake() {
        int y = (int) 5 * (Constants.WINDOW_HEIGHT / Constants.CELL_SIZE);
        int x = (int) 3 * (Constants.WINDOW_WIDTH / Constants.CELL_SIZE);

        Rectangle snakeHead = new Rectangle();
        snakeHead.setY(y);
        snakeHead.setX(x);
        snakeHead.setWidth(Constants.CELL_SIZE);
        snakeHead.setHeight(Constants.CELL_SIZE);
        snakeHead.setFill(Color.WHITESMOKE);
        snakeCells.add(snakeHead);
    }

    public ArrayList<Rectangle> generateGrid() {
        ArrayList<Rectangle> grid = new ArrayList<Rectangle>();
        int rows = Constants.WINDOW_HEIGHT / Constants.CELL_SIZE;
        int cols = Constants.WINDOW_WIDTH / Constants.CELL_SIZE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Rectangle rectangle = new Rectangle();
                rectangle.setY((int) i * Constants.CELL_SIZE);
                rectangle.setX((int) j * Constants.CELL_SIZE);
                rectangle.setWidth(Constants.CELL_SIZE);
                rectangle.setHeight(Constants.CELL_SIZE);
                rectangle.setFill(Color.DARKSLATEGREY);
                grid.add(rectangle);
            }
        }
        return grid;
    }

    public void move() {
        int currentX = (int) snakeCells.get(0).getX();
        int currentY = (int) snakeCells.get(0).getY();
        Rectangle oldCell = snakeCells.get(0);
        Rectangle newCell = new Rectangle();
        newCell.setX(oldCell.getX());
        newCell.setY(oldCell.getY());
        newCell.setFill(oldCell.getFill());
        newCell.setWidth(oldCell.getWidth());
        newCell.setHeight(oldCell.getHeight());

        if (Constants.CURRENT_DIRECTION == Direction.UP) {
            if (currentY > 0) {
                newCell.setY(currentY - Constants.CELL_SIZE);
            } else {
                newCell.setY(Constants.WINDOW_HEIGHT);
            }
            snakeCells.add(0, newCell);
        } else if (Constants.CURRENT_DIRECTION == Direction.LEFT) {
            if (currentX > 0) {
                newCell.setX(currentX - Constants.CELL_SIZE);
            } else {
                newCell.setX(Constants.WINDOW_WIDTH);
            }
            snakeCells.add(0, newCell);
        } else if (Constants.CURRENT_DIRECTION == Direction.DOWN) {
            if (currentY < Constants.WINDOW_HEIGHT) {
                newCell.setY(currentY + Constants.CELL_SIZE);
            } else {
                newCell.setY(0);
            }
            snakeCells.add(0, newCell);
        } else if (Constants.CURRENT_DIRECTION == Direction.RIGHT) {
            if (currentX < Constants.WINDOW_WIDTH) {
                newCell.setX(currentX + Constants.CELL_SIZE);
            } else {
                newCell.setX(0);
            }
            snakeCells.add(0, newCell);
        }

    }

    public void updateSnakeCells() {
        while (snakeCells.size() > Constants.CURRENT_SNAKE_SIZE) {
            snakeCells.remove(snakeCells.size() - 1);
        }
    }

    public Rectangle generateFood() {
        Rectangle food = new Rectangle();
        int foodX = (int) (Math.random() * (Constants.WINDOW_WIDTH / Constants.CELL_SIZE));
        int foodY = (int) (Math.random() * (Constants.WINDOW_HEIGHT / Constants.CELL_SIZE));
        foodX *= Constants.CELL_SIZE;
        foodY *= Constants.CELL_SIZE;
        food.setHeight(Constants.CELL_SIZE);
        food.setWidth(Constants.CELL_SIZE);
        food.setX(foodX);
        food.setY(foodY);
        food.setFill(Color.DARKSEAGREEN);
        return food;
    }

    public boolean isTailCollideFood(Rectangle food) {
        for (Rectangle snakeCell : snakeCells) {
            if (food.getX() == snakeCell.getX() && food.getY() == snakeCell.getY()) {
                return true;
            }
        }
        return false;
    }

    public boolean foodEaten(Rectangle food) {
        snakeCells.get(0).getX();
        snakeCells.get(0).getY();
        if (food.getX() == snakeCells.get(0).getX() && food.getY() == snakeCells.get(0).getY()) {
            return true;
        }
        return false;
    }


}
