package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import view.*;
import utils.*;

public class SnakeModel {

    public static String GAME_OVER_MSG;

    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;
    static final int DOWN_AND_RIGHT = 4;
    static final int DOWN_AND_LEFT = 5;
    static final int UP_AND_LEFT = 6;
    static final int UP_AND_RIGHT = 7;

    private int snakeSize;
    private int x;
    private int y;

    private ConfigReader cr;

    private int currentRotation = 0;
    private boolean snakeReady = false;

    private boolean isGameOverStatus = false;

    static final int MAX_SNAKE_SIZE = 20;

    private int[][] SNAKE;
    private Food food;
    private Poison poison;

    BeautyTexture snakeView;

    public SnakeModel(ConfigReader cr, int x, int y, int initSnakeSize) {
        this.cr = cr;
        this.x = x;
        this.y = y;
        this.snakeSize = initSnakeSize;
    }

    public int size(){
        return this.snakeSize;
    }

    public void setSnakeView(BeautyTexture snakeView) {
         this.snakeView = snakeView;
    }

    public void setFood(Food food) {
         this.food = food;
    }

    public void setPoison(Poison poison) {
         this.poison = poison;
    }

    public boolean isSnakeRead() {
        return this.snakeReady;
    }
    public void setSnakeReady(boolean status) {
        this.snakeReady = status;
    }

    public void initSnake() {

        this.SNAKE = new int[this.snakeSize][3];


        for (int i = 0; i < this.snakeSize; i++) {
            this.SNAKE[i][0] = x;
            this.SNAKE[i][1] = (y+i);
            this.SNAKE[i][2] = UP;
        }
    }

    public void eat() {

        int[][] temp = this.SNAKE.clone();

        this.snakeSize += 1;
        this.SNAKE = new int[this.snakeSize][3];

        for (int i = 0; i < this.snakeSize - 1; i++) {
            this.SNAKE[i] = temp[i];
        }

        this.SNAKE[this.SNAKE.length - 1] = temp[temp.length - 1];

        this.food.setEatenStatus(true);
    }

    public boolean checkFood() {
        if (this.SNAKE[0][0] == this.food.getX() && this.SNAKE[0][1] == this.food.getY()) {
            eat();
            return true;
        }

        return false;
    }

    public void checkPoison() {
        if (this.SNAKE[0][0] == this.poison.getX() && this.SNAKE[0][1] == this.poison.getY()) {
            this.isGameOverStatus = true;
            this.GAME_OVER_MSG = "You shouldn't eat poison!";
        }
    }

    public void setDirection(int code) {
        if (code == (int)cr.get("KEY_UP", int.class) && this.currentRotation != DOWN) {
            this.currentRotation = UP;
        }
        else if (code == (int)cr.get("KEY_DOWN", int.class) && this.currentRotation != UP) {
            this.currentRotation = DOWN;
        }
        else if (code == (int)cr.get("KEY_LEFT", int.class) && this.currentRotation != RIGHT) {
            this.currentRotation = LEFT;
        }
        else if (code == (int)cr.get("KEY_RIGHT", int.class) && this.currentRotation != LEFT) {
            this.currentRotation = RIGHT;
        }
    }

    public boolean isGameOver(){
        return this.isGameOverStatus;
    }

    public void checkColide() {
      for (int i = 1; i < this.snakeSize; i++) {
          if (this.SNAKE[0][0] == this.SNAKE[i][0] && this.SNAKE[0][1] == this.SNAKE[i][1]) {
              this.isGameOverStatus = true;
              this.GAME_OVER_MSG = "Why did you bite yourself?";
              break;
          }
        }
    }

    public boolean isCellEmpty(int x, int y) {
        for (int i = 0; i < this.SNAKE.length; i++) {
            if (x == this.SNAKE[i][0] && y == this.SNAKE[i][1]) {
                return false;
            }
      }
      return true;
    }

    private int checkFieldColide(int coord, int limit) {
        if ((coord < 0) || (coord > limit)){
            this.isGameOverStatus = true;
            this.GAME_OVER_MSG = "Where are you going?";
        }

        return coord;
    }

    private int checkField(int coord, int limit) {

        if (!(boolean)cr.get("INF_FIELD", boolean.class)) {
            return checkFieldColide(coord,limit);
        }


        if (coord < 0) {
            return limit;
        }
        else if (coord > limit) {
          return 0;
        }

        return coord;
    }

    private int setRotationForBudy(int oldR, int newR) {

        if (((oldR == UP && newR == LEFT) || (oldR == RIGHT && newR == DOWN))) {
            return DOWN_AND_LEFT;
        }
        else if (((oldR == UP && newR == RIGHT) || (oldR == LEFT && newR == DOWN))) {
            return DOWN_AND_RIGHT;
        }
        else if (((oldR == DOWN && newR == LEFT) || (oldR == RIGHT && newR == UP))) {
            return UP_AND_LEFT;
        }
        else if (((oldR == DOWN && newR == RIGHT) || (oldR == LEFT && newR == UP))) {
            return UP_AND_RIGHT;
        }

        return newR;
    }

    public void move() {

      int[] prev = this.SNAKE[0].clone();

      if (this.currentRotation == UP) {
          this.SNAKE[0][1] = checkField(this.SNAKE[0][1] - 1, (int)cr.get("CANVAS_HEIGHT", int.class) - 1);
          this.SNAKE[0][2] = UP;
      }
      else if (this.currentRotation == DOWN) {
          this.SNAKE[0][1] = checkField(this.SNAKE[0][1] + 1, (int)cr.get("CANVAS_HEIGHT", int.class) - 1);
          this.SNAKE[0][2] = DOWN;
      }
      else if (this.currentRotation == LEFT) {
          this.SNAKE[0][0] = checkField(this.SNAKE[0][0] - 1, (int)cr.get("CANVAS_WIDTH", int.class) - 1);
          this.SNAKE[0][2] = LEFT;
      }
      else if (this.currentRotation == RIGHT) {
          this.SNAKE[0][0] = checkField(this.SNAKE[0][0] + 1, (int)cr.get("CANVAS_WIDTH", int.class) - 1);
          this.SNAKE[0][2] = RIGHT;
      }

      for (int i = 1; i < this.snakeSize; i++) {
          int[] temp = this.SNAKE[i].clone();
          this.SNAKE[i] = prev;
          prev = temp;
      }

    };

    public void paint(Graphics2D g2D) {

        BufferedImage image = this.snakeView.paintHead(this.SNAKE[0][2]);
        g2D.drawImage(image, cr.prepareCoord(this.SNAKE[0][0]),
                      cr.prepareCoord(this.SNAKE[0][1]), null);

        for (int i = 1; i < this.SNAKE.length - 1; i++) {
            image = this.snakeView.paintBody(setRotationForBudy(this.SNAKE[i][2], this.SNAKE[i-1][2]));
            g2D.drawImage(image, cr.prepareCoord(this.SNAKE[i][0]),
                          cr.prepareCoord(this.SNAKE[i][1]), null);
        }

        image = this.snakeView.paintTail(this.SNAKE[this.SNAKE.length - 2][2]);
        g2D.drawImage(image, cr.prepareCoord(this.SNAKE[this.SNAKE.length - 1][0]),
                      cr.prepareCoord(this.SNAKE[this.SNAKE.length - 1][1]), null);

    }

}
