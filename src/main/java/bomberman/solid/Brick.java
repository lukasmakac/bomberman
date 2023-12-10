package bomberman.solid;

import static bomberman.common.Utils.getImage;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Brick extends SolidEntity {
  public static final Image IMAGE = getImage("brick.png");

  public Brick(Point2D position){
    super(IMAGE, position);
  }
}
