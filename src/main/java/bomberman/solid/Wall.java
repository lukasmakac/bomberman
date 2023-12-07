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

    public Wall(Point2D position){
        this.position = position;
    }

    @Override
    public void draw(GraphicsContext gc) {
//        gc.drawImage(IMAGE, position.getX(), translateY(position.getY(), gc.getCanvas().getHeight()), SIZE, SIZE);
      gc.drawImage(IMAGE, position.getX(), position.getY(), SIZE, SIZE);
//        gc.setFill(Color.RED);
//        gc.setGlobalAlpha(.75);
//        gc.fillRect(getBoundingBox().getMinX(), translateY(getBoundingBox().getMinY(), gc.getCanvas().getHeight()), SIZE, SIZE);
//        gc.fillRect(getBoundingBox().getMinX(), getBoundingBox().getMinY(), SIZE, SIZE);
//        gc.setGlobalAlpha(1.0);
    }

    @Override
    public Rectangle2D getBoundingBox() {
      return new Rectangle2D(position.getX(), position.getY(), SIZE, SIZE);
    }
}
