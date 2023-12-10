package bomberman.solid;

import static bomberman.common.Utils.getImage;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Bomb extends SolidEntity {

  public static final Image IMAGE = getImage("bomb.png");
  public static final Integer EXPLOSION_SIZE = 5;


  public Bomb(Point2D position) {
    super(IMAGE, position);
  }

}
