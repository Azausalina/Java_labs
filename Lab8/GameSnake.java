import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.IOException;

/**
 * Java. Classic Game Snake
 *
 * @author Sergey Iryupin
 * @version 0.7.7 dated Oct 26, 2021
 *
 * @modify Anna Zausalina
 * @version 0.8.0 dated Nov 26, 2021
 */

import view.*;
import model.*;
import utils.*;

public class GameSnake extends JFrame {

    final String TITLE_OF_PROGRAM = "Classic Game Snake";

    final static String fileName = "assets/background.jpg";
    final static String snakeFileName = "assets/snake.png";
    final static Color SNAKE_COLOR = Color.darkGray;
    final static Color FOOD_COLOR = Color.green;
    final static Color POISON_COLOR = Color.red;

    private int START_SNAKE_X;
    private int START_SNAKE_Y;

    Canvas canvas;                   // canvas object for rendering (drawing)
    SnakeModel snake;                 // declare a snake object
    Food food;                       // declare a food object
    Poison poison;                   // declare a poison object
    BeautyTexture bText;

    private ConfigReader cr;

    public static void main(String[] args) {    // starting method

        ConfigReader cr = new ConfigReader();
        if (cr.readConfig("assets/GameSnake.cfg")) {
            new GameSnake(cr).startGame(); // create an object and call game()
        }

    }

    public GameSnake(ConfigReader cr) {

        this.cr = cr;

        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            canvas = new Canvas(cr, this.fileName);
            canvas.adaptateImage();
        } catch (IOException ex) {
            canvas = new Canvas(cr);
            canvas.setBackground(Color.white);
        }

        canvas.setPreferredSize(new Dimension(
                (int)cr.get("CELL_SIZE", int.class) * (int)cr.get("CANVAS_WIDTH", int.class) + (int)cr.get("CELL_SIZE", int.class),
                (int)cr.get("CELL_SIZE", int.class) * (int)cr.get("CANVAS_HEIGHT", int.class) + (int)cr.get("CELL_SIZE", int.class)));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                canvas.toggleGame(e.getKeyCode());
            }
        });

        add(canvas);                 // add a panel for rendering
        pack();                      // bringing the window to the required size
        setLocationRelativeTo(null); // the window will be in the center
        setResizable(false);         // prohibit window resizing
        setVisible(true);            // make the window visible
    }

    private void startGame() {
        while (true) {
            if (canvas.getGameIsStarted()) { game(); }
            canvas.repaint();
            sleep((int)cr.get("SNAKE_DELAY", int.class));
        }
    }

    private void game() {            // method containing game cycle

        START_SNAKE_X = (int)cr.get("CANVAS_WIDTH", int.class)/2;
        START_SNAKE_Y = (int)cr.get("CANVAS_HEIGHT", int.class)/2;

        snake = new SnakeModel(cr, START_SNAKE_X,
                               START_SNAKE_Y,
                               (int)cr.get("START_SNAKE_SIZE", int.class));
        food = new Food(cr, snake);      // creating food object
        poison = new Poison(cr, snake);  // poison object

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                snake.setDirection(e.getKeyCode());
            }
        });

        canvas.setSnake(snake);
        canvas.setFood(food);
        canvas.setPoison(poison);

        try {
            bText = new BeautyTexture(cr, snakeFileName);
            bText.adaptateImage();
        } catch (IOException ex) {
            bText = new BeautyTexture(cr);
        }

        //bText.setFoodColor(FOOD_COLOR);
        //bText.setSnakeColor(SNAKE_COLOR);
        //bText.setPoisonColor(POISON_COLOR);

        snake.initSnake();
        snake.setSnakeView(bText);
        snake.setFood(food);
        snake.setPoison(poison);
        snake.setSnakeReady(true);

        food.setFoodView(bText);
        food.setPoison(poison);
        food.appear();

        poison.setPoisonView(bText);
        poison.setFood(food);

        int snakeSize = snake.size();

        sleep((int)cr.get("SNAKE_DELAY", int.class));
        while (!snake.isGameOver()) {          // game cycle while NOT gameOver
            snake.move();            // snake move
            snake.checkColide();
            snake.checkPoison();

            if (snake.checkFood()) {    // if the snake ate the food
                food.appear();       //   show food in new place
                poison.appear();        //   add new poison point
            }
            canvas.repaint();        // repaint panel/window
            sleep((int)cr.get("SNAKE_DELAY", int.class));      // to make delay in milliseconds
        }
        sleep((int)cr.get("GAME_OVER_DELAY", int.class));
        canvas.setGameIsStarted(false);
    }

    private void sleep(long ms) {    // method for suspending
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
