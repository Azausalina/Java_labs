package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.util.Random;

import view.*;
import utils.*;

public class Poison {

    private ConfigReader cr;

    private SnakeModel snake;
    private Food food;
    private BeautyTexture poisonView;
    private int x;
    private int y;
    private boolean isEaten = true;

    public Poison(ConfigReader cr, SnakeModel snake){
        this.cr = cr;
        this.snake = snake;
    }

    public void setPoisonView(BeautyTexture poisonView) {
         this.poisonView = poisonView;
    }

    public void setFood(Food food) {
        this.food = food;
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

    public boolean isCellEmpty(int x, int y) {
        return (this.x != x && this.y != y);
    }

    public void appear() {

        x = new Random().nextInt((int)cr.get("CANVAS_WIDTH", int.class));
        y = new Random().nextInt((int)cr.get("CANVAS_HEIGHT", int.class));
        while ((!snake.isCellEmpty(x,y) && !food.isCellEmpty(x,y))) {
            x = new Random().nextInt((int)cr.get("CANVAS_WIDTH", int.class));
            y = new Random().nextInt((int)cr.get("CANVAS_HEIGHT", int.class));
        }

        this.isEaten = false;
    }

    public void paint(Graphics2D g2D) {
        BufferedImage image = this.poisonView.paintPoison();
        g2D.drawImage(image, cr.prepareCoord(x), cr.prepareCoord(y), null);
    }


}
