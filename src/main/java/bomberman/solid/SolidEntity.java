package bomberman.solid;

import bomberman.common.Collisional;
import bomberman.common.Drawable;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class SolidEntity implements Drawable, Collisional {

  public static final Integer SIZE = 20;

  protected final Point2D position;
  protected final Image image;

  protected SolidEntity(Image image, Point2D position) {
    this.position = position;
    this.image = image;
  }

  @Override
  public void draw(GraphicsContext gc) {
    gc.drawImage(image, position.getX(), position.getY(), SIZE, SIZE);
  }

  @Override
  public Rectangle2D getBoundingBox() {
    return new Rectangle2D(position.getX(), position.getY(), SIZE, SIZE);
  }

  public Point2D getPosition() {
    return position;
  }
}
