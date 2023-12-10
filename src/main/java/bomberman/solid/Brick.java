package bomberman.solid;

import static bomberman.common.Utils.getImage;

import bomberman.GameController;
import bomberman.common.Collisional;
import bomberman.common.Drawable;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Brick implements Drawable, Collisional {
  public static final Image IMAGE = getImage("brick.png");
  public static final Integer SIZE = 25;

  private final Point2D position;

  private GameController controller;

  public Brick(Point2D position){
    this.position = position;
  }

  @Override
  public void draw(GraphicsContext gc) {
    gc.drawImage(IMAGE, position.getX(), position.getY(), SIZE, SIZE);

  }
  @Override
  public Rectangle2D getBoundingBox() {
    return new Rectangle2D(position.getX(), position.getY(), SIZE, SIZE);
  }

  public void removeBrick() {
    // remove brick when bomb collisions the BBX of brick

    // increase score
    controller.addScore(25);
  }

}
