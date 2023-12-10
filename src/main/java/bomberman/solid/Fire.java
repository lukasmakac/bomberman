package bomberman.solid;

import static bomberman.common.Utils.getImage;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Fire extends SolidEntity {
  public static final Image IMAGE = getImage("fire_test_3.gif");

  public Fire(Point2D position) {
    super(IMAGE, position);
  }

}
