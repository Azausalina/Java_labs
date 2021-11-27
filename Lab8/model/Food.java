package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.util.Random;

import view.*;
import utils.*;

public class Food {

    private SnakeModel snake;
    private Poison poison;
    private BeautyTexture foodView;
    private int x;
    private int y;
    private boolean isEaten = true;

    private ConfigReader cr;

    public Food(ConfigReader cr, SnakeModel snake){
        this.cr = cr;
        this.snake = snake;
    }

    public void setFoodView(BeautyTexture foodView) {
         this.foodView = foodView;
    }

    public void setPoison(Poison poison) {
        this.poison = poison;
    }

    public boolean isCellEmpty(int x, int y) {
        return (this.x != x && this.y != y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isEatenStatus(){
        return this.isEaten;
    }
    public void setEatenStatus(boolean status){
        this.isEaten = status;
    }

    public void appear() {

        x = new Random().nextInt((int)cr.get("CANVAS_WIDTH", int.class));
        y = new Random().nextInt((int)cr.get("CANVAS_HEIGHT", int.class));
        while ((!snake.isCellEmpty(x,y) && !poison.isCellEmpty(x,y))) {
            x = new Random().nextInt((int)cr.get("CANVAS_WIDTH", int.class));
            y = new Random().nextInt((int)cr.get("CANVAS_HEIGHT", int.class));
        }

        this.isEaten = false;
    }

    public void paint(Graphics2D g2D) {
        BufferedImage image = this.foodView.paintFood();
        g2D.drawImage(image, cr.prepareCoord(x), cr.prepareCoord(y), null);
    }


}
