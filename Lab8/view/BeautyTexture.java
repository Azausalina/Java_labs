package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.Color;
import java.awt.AlphaComposite;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import utils.*;

public class BeautyTexture {

    private BufferedImage textureImage;
    private boolean withImage;

    private Color poisonColor;
    private Color snakeColor;
    private Color foodColor;

    private boolean isFoodColor = false;
    private boolean isSnakeColor = false;
    private boolean isPoisonColor = false;

    static final int[] FOOD = {0, 3};
    static final int[] POISON = {1, 3};

    static final int[] SNAKE_HEAD_TOP = {3, 0};
    static final int[] SNAKE_HEAD_RIGHT = {4, 0};
    static final int[] SNAKE_HEAD_LEFT = {3, 1};
    static final int[] SNAKE_HEAD_BOTTOM = {4, 1};

    static final int[] SNAKE_TAIL_TOP = {3, 2};
    static final int[] SNAKE_TAIL_RIGHT = {4, 2};
    static final int[] SNAKE_TAIL_LEFT = {3, 3};
    static final int[] SNAKE_TAIL_BOTTOM = {4, 3};

    static final int[] SNAKE_BODY_TURN_I = {0, 0};
    static final int[] SNAKE_BODY_TURN_II = {2, 0};
    static final int[] SNAKE_BODY_TURN_III = {2, 2};
    static final int[] SNAKE_BODY_TURN_IV = {0, 1};

    static final int[] SNAKE_BODY_HORIZONTAL = {1, 0};
    static final int[] SNAKE_BODY_VERTICAL = {2, 1};

    private ConfigReader cr;

    public BeautyTexture (ConfigReader cr, String fileName) throws IOException {
        this.cr = cr;
        this.textureImage = ImageIO.read(new File(fileName));
        this.withImage = true;
    }

    public BeautyTexture (ConfigReader cr){
        this.cr = cr;
        this.withImage = false;
    }

    public void setPoisonColor(Color color) {
        this.poisonColor = color;
        this.isPoisonColor = true;
    }

    public void setSnakeColor(Color color) {
        this.snakeColor = color;
        this.isSnakeColor = true;
    }

    public void setFoodColor(Color color) {
        this.foodColor = color;
        this.isFoodColor = true;
    }

    private static BufferedImage recolorImage(BufferedImage image, Color color)
    {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage recoloredImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = recoloredImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.setColor(color);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.75f));
        g.fillRect(0, 0, width, height);
        g.dispose();
        return recoloredImage;
    }

    public void adaptateImage() {

        int targetWidth = (int)cr.get("CELL_SIZE", int.class) * 5;
        int targetHeight = (int)cr.get("CELL_SIZE", int.class) * 4;

        BufferedImage resizedImage = new BufferedImage(targetWidth,
                                                       targetHeight,
                                                       BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(this.textureImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        this.textureImage = resizedImage;
    }

    private BufferedImage cropImage(int[] coords) {
        BufferedImage dest = this.textureImage.getSubimage(coords[0] * (int)cr.get("CELL_SIZE", int.class),
                                                           coords[1] * (int)cr.get("CELL_SIZE", int.class),
                                                           (int)cr.get("CELL_SIZE", int.class),
                                                           (int)cr.get("CELL_SIZE", int.class));
        return dest;
    }

    public BufferedImage paintPoison() {
        return (isPoisonColor) ? recolorImage(cropImage(POISON), this.poisonColor) : cropImage(POISON);
    }

    public BufferedImage paintFood() {
        return (isFoodColor) ? recolorImage(cropImage(FOOD), this.foodColor) : cropImage(FOOD);
    }

    public BufferedImage paintHead(int rotation) {
        if (this.withImage){
            if (rotation == 0) {
                return (isSnakeColor) ? recolorImage(cropImage(SNAKE_HEAD_TOP), this.snakeColor) : cropImage(SNAKE_HEAD_TOP);
            }
            else if (rotation == 1) {
                return (isSnakeColor) ? recolorImage(cropImage(SNAKE_HEAD_BOTTOM), this.snakeColor) : cropImage(SNAKE_HEAD_BOTTOM);
            }
            else if (rotation == 2) {
                return (isSnakeColor) ? recolorImage(cropImage(SNAKE_HEAD_LEFT), this.snakeColor) : cropImage(SNAKE_HEAD_LEFT);
            }
            else if (rotation == 3) {
                return (isSnakeColor) ? recolorImage(cropImage(SNAKE_HEAD_RIGHT), this.snakeColor) : cropImage(SNAKE_HEAD_RIGHT);
            }
        }

        return (isSnakeColor) ? recolorImage(cropImage(SNAKE_HEAD_TOP), this.snakeColor) : cropImage(SNAKE_HEAD_TOP);
    }


    public BufferedImage paintTail(int rotation) {

      if (this.withImage){
          if (rotation == 0) {
              return (isSnakeColor) ? recolorImage(cropImage(SNAKE_TAIL_TOP), this.snakeColor) : cropImage(SNAKE_TAIL_TOP);
          }
          else if (rotation == 1) {
              return (isSnakeColor) ? recolorImage(cropImage(SNAKE_TAIL_BOTTOM), this.snakeColor) : cropImage(SNAKE_TAIL_BOTTOM);
          }
          else if (rotation == 2) {
              return (isSnakeColor) ? recolorImage(cropImage(SNAKE_TAIL_LEFT), this.snakeColor) : cropImage(SNAKE_TAIL_LEFT);
          }
          else if (rotation == 3) {
              return (isSnakeColor) ? recolorImage(cropImage(SNAKE_TAIL_RIGHT), this.snakeColor) : cropImage(SNAKE_TAIL_RIGHT);
          }
      }

      return (isSnakeColor) ? recolorImage(cropImage(SNAKE_TAIL_TOP), this.snakeColor) : cropImage(SNAKE_TAIL_TOP);

    }

    public BufferedImage paintBody(int rotation) {

      if (this.withImage){
          if (rotation == 0) {
              return (isSnakeColor) ? recolorImage(cropImage(SNAKE_BODY_VERTICAL), this.snakeColor) : cropImage(SNAKE_BODY_VERTICAL);
          }
          else if (rotation == 1) {
              return (isSnakeColor) ? recolorImage(cropImage(SNAKE_BODY_VERTICAL), this.snakeColor) : cropImage(SNAKE_BODY_VERTICAL);
          }
          else if (rotation == 2) {
              return (isSnakeColor) ? recolorImage(cropImage(SNAKE_BODY_HORIZONTAL), this.snakeColor) : cropImage(SNAKE_BODY_HORIZONTAL);
          }
          else if (rotation == 3) {
              return (isSnakeColor) ? recolorImage(cropImage(SNAKE_BODY_HORIZONTAL), this.snakeColor) : cropImage(SNAKE_BODY_HORIZONTAL);
          }
          else if (rotation == 4) {
              return (isSnakeColor) ? recolorImage(cropImage(SNAKE_BODY_TURN_I), this.snakeColor) : cropImage(SNAKE_BODY_TURN_I);
          }
          else if (rotation == 5) {
              return (isSnakeColor) ? recolorImage(cropImage(SNAKE_BODY_TURN_II), this.snakeColor) : cropImage(SNAKE_BODY_TURN_II);
          }
          else if (rotation == 6) {
              return (isSnakeColor) ? recolorImage(cropImage(SNAKE_BODY_TURN_III), this.snakeColor) : cropImage(SNAKE_BODY_TURN_III);
          }
          else if (rotation == 7) {
              return (isSnakeColor) ? recolorImage(cropImage(SNAKE_BODY_TURN_IV), this.snakeColor) : cropImage(SNAKE_BODY_TURN_IV);
          }
      }

      return (isSnakeColor) ? recolorImage(cropImage(SNAKE_BODY_VERTICAL), this.snakeColor) : cropImage(SNAKE_BODY_VERTICAL);

    }
}
