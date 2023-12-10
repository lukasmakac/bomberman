package bomberman.solid;

import static bomberman.common.Utils.getImage;

import bomberman.common.Collisional;
import bomberman.common.Drawable;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bomb implements Drawable, Collisional {

  public static final Image IMAGE = getImage("bomb.png");
  public static final Integer SIZE = 20;

  public static final Integer EXPLOSION_SIZE = 5;

  private final Point2D position;

  public Bomb(Point2D position) {
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

  public Point2D getPosition() {
    return position;
  }
}
