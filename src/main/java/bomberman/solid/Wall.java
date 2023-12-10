package bomberman.solid;

import static bomberman.common.Utils.getImage;

import bomberman.common.Collisional;
import bomberman.common.Drawable;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Wall implements Drawable, Collisional {
    public static final Image IMAGE = getImage("wall.png");
  public static final Integer SIZE = 20;

  private final Point2D position;

  public Wall(Point2D position) {
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
}
