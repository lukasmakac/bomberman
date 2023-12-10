package bomberman.character;

import bomberman.common.Collisional;
import bomberman.common.Drawable;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Character implements Drawable, Collisional {

  public static final int SIZE = 20;

  protected final Image image;

  protected Point2D position;

  public Character(Image image, Point2D position) {
    this.image = image;
    this.position = position;
  }

  @Override
  public void draw(GraphicsContext gc) {
    gc.drawImage(image, position.getX(), position.getY(), SIZE, SIZE);
  }

  public Rectangle2D getBoundingBox(){
    return new Rectangle2D(position.getX(),position.getY(), SIZE, SIZE);
  }

  public Point2D getPosition() {
    return position;
  }

  public void setPosition(Point2D position) {
    this.position = position;
  }
}
