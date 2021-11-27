package view;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.*;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.AlphaComposite;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

import model.*;
import utils.*;

public class Canvas extends JPanel {   // class for rendering (drawing)

    private BufferedImage backgroundImage;
    private boolean withImage;

    private SnakeModel snake;
    private Food food;
    private Poison poison;

    private ConfigReader cr;

    private Font font;
    private Font bigFont;
    private Font midFont;

    private final Color RED = new Color(225,98,86);
    private final Color ORANGE = new Color(254,145,42);
    private final Color GREEN = new Color(91,127,0);

    private boolean gameIsStarted = false;

    public Canvas(ConfigReader cr, String fileName) throws IOException {
        this.cr = cr;
        this.backgroundImage = ImageIO.read(new File(fileName));
        this.withImage = true;

        font = new Font("TimesRoman", Font.PLAIN, (int)((int)cr.get("CELL_SIZE", int.class) / 2));
        bigFont = new Font("TimesRoman", Font.BOLD, (int)((int)cr.get("CELL_SIZE", int.class) * (int)cr.get("CANVAS_HEIGHT", int.class)/10));
        midFont = new Font("TimesRoman", Font.PLAIN, (int)((int)cr.get("CELL_SIZE", int.class) * (int)cr.get("CANVAS_HEIGHT", int.class)/20));
    }

    public Canvas(ConfigReader cr){
        this.cr = cr;
        this.withImage = false;

        font = new Font("TimesRoman", Font.PLAIN, (int)((int)cr.get("CELL_SIZE", int.class) / 2));
    }

    public void setGameIsStarted(boolean status) { this.gameIsStarted = status; }
    public boolean getGameIsStarted() { return this.gameIsStarted; }

    public void toggleGame(int code) {
        if (code == 10 && !this.gameIsStarted){ // 10 - ENTER
            this.gameIsStarted = true;
        }
    }

    public void setSnake(SnakeModel snake) { this.snake = snake; }
    public void setFood(Food food) { this.food = food; }
    public void setPoison(Poison poison) { this.poison = poison; }

    public void adaptateImage() {

        int targetWidth = (int)cr.get("CELL_SIZE", int.class) * (int)cr.get("CANVAS_WIDTH", int.class);
        int targetHeight = (int)cr.get("CELL_SIZE", int.class) * (int)cr.get("CANVAS_HEIGHT", int.class);
        BufferedImage resizedImage = new BufferedImage(targetWidth,
                                                       targetHeight,
                                                       BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(this.backgroundImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        this.backgroundImage = resizedImage;
    }

    private void drawLines(Graphics2D g2D) {
        for (int x = 0; x <= (int)cr.get("CANVAS_WIDTH", int.class); x++) {
            g2D.setColor(GREEN);
            g2D.draw(new Line2D.Double(cr.prepareCoord(x),
                                       ((int)((int)cr.get("CELL_SIZE", int.class)) / 2),
                                       cr.prepareCoord(x),
                                       (int)cr.get("CELL_SIZE", int.class) * (int)cr.get("CANVAS_HEIGHT", int.class) + (int)((int)cr.get("CELL_SIZE", int.class) / 2)));
        }

        for (int y = 0; y <= (int)cr.get("CANVAS_HEIGHT", int.class); y++) {
            g2D.draw(new Line2D.Double((int)((int)cr.get("CELL_SIZE", int.class) / 2),
                                       cr.prepareCoord(y),
                                       (int)cr.get("CELL_SIZE", int.class) * (int)cr.get("CANVAS_WIDTH", int.class) + (int)((int)cr.get("CELL_SIZE", int.class) / 2),
                                       cr.prepareCoord(y)));
        }
    }

    private int posTextOnCenterX(String text, Graphics2D g2d, FontMetrics metrics) {
        int targetWidth = (int)cr.get("CELL_SIZE", int.class) * (int)cr.get("CANVAS_WIDTH", int.class) + (int)cr.get("CELL_SIZE", int.class);
        return  (targetWidth - metrics.stringWidth(text)) / 2;
    }

    private int posTextOnCenterY(String text, Graphics2D g2d, FontMetrics metrics) {
        int targetHeight = (int)cr.get("CELL_SIZE", int.class) * (int)cr.get("CANVAS_HEIGHT", int.class) + (int)cr.get("CELL_SIZE", int.class);
        return (((targetHeight - metrics.getHeight()) / 2) + metrics.getAscent()) - (int)((targetHeight / 8));

    }

    private void drawPlateOnCentre(Graphics2D g2D) {
      int targetWidth = (int)cr.get("CELL_SIZE", int.class) * (int)cr.get("CANVAS_WIDTH", int.class) + (int)cr.get("CELL_SIZE", int.class);
      int targetHeight = (int)cr.get("CELL_SIZE", int.class) * (int)cr.get("CANVAS_HEIGHT", int.class) + (int)cr.get("CELL_SIZE", int.class);

      g2D.setComposite(AlphaComposite.SrcOver.derive(0.8f));
      g2D.setColor(Color.white);
      g2D.fillRect(0, (int)(targetHeight / 4), targetWidth, (int)(targetHeight / 2));

      g2D.setComposite(AlphaComposite.SrcOver);
    }

    private void drawTextOnCenter(String text, Graphics2D g2D, float deviation, Font font, Color color) {

      FontMetrics metrics = g2D.getFontMetrics(font);

      int x = posTextOnCenterX(text,g2D, metrics);
      int y = posTextOnCenterY(text,g2D, metrics);

      g2D.setColor(color);
      g2D.setFont(font);
      g2D.drawString(text, x,y * deviation);
    }

    private void drawGameOver(Graphics2D g2D) {
        drawPlateOnCentre(g2D);
        drawTextOnCenter(this.snake.GAME_OVER_MSG, g2D, 1, this.bigFont, ORANGE);
    }

    private void drawGameStart(Graphics2D g2D) {
        drawPlateOnCentre(g2D);
        drawTextOnCenter("READY?", g2D, 1f, this.bigFont, ORANGE);
        drawTextOnCenter("Press ENTER to start!", g2D, 1.75f, this.midFont, GREEN);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (withImage) {
            g.drawImage(this.backgroundImage, (int)((int)cr.get("CELL_SIZE", int.class) / 2),
                        (int)((int)cr.get("CELL_SIZE", int.class) / 2), this);
        }

        Graphics2D g2D = (Graphics2D) g;

        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (!this.gameIsStarted) {
            drawGameStart(g2D);
            return;
        }

        if (this.snake.isGameOver()) {
            drawGameOver(g2D);
            return;
        }

        drawLines(g2D);

        if (this.snake.isSnakeRead()){
            this.snake.paint(g2D);
            g2D.setColor(RED);
            g2D.setFont(this.font);
            g2D.drawString("Score: " + snake.size() ,
                           (int)((int)cr.get("CELL_SIZE", int.class) / 2),
                           (int)((int)cr.get("CELL_SIZE", int.class) / 2) - 1);
        }
        else {
            g2D.setFont(this.font);
            g2D.drawString("Score: ", (int)((int)cr.get("CELL_SIZE", int.class) / 2),
                          (int)((int)cr.get("CELL_SIZE", int.class) / 2) - 1); }

        if (!this.food.isEatenStatus()){
            food.paint(g2D);
        }
        if (!this.poison.isEatenStatus()){
            poison.paint(g2D);
        }
    }
}
