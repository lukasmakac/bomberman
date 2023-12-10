package bomberman.solid;

import static bomberman.common.Utils.getImage;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Wall extends SolidEntity {

  public static final Image IMAGE = getImage("wall.png");

  public Wall(Point2D position) {
    super(IMAGE, position);
  }
}
