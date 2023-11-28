package bomberman.character;

import static bomberman.common.Utils.getImage;

import bomberman.World;
import bomberman.common.Drawable;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Character implements Drawable {

  public static final int SIZE = 20;

  protected final World world;
  protected final Image image;

  protected Point2D position;

  protected Character(World world, Point2D position, String imagePath) {
    this.world = world;
    this.position = position;
    this.image = getImage(imagePath);
  }

  @Override
  public void draw(GraphicsContext gc) {
    Point2D canvasPosition = world.getCanvasPoint(position);
    gc.drawImage(image, canvasPosition.getX(), canvasPosition.getY(), SIZE, SIZE);
  }

  public Rectangle2D getBoundingBox(){
    return new Rectangle2D(position.getX(),position.getY(), SIZE, SIZE);
  }

}
