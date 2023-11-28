package bomberman.character;

import bomberman.World;
import javafx.geometry.Point2D;

public class RedFace extends Enemy {
  public static final String IMAGE = "red_face.gif";

  public RedFace(World world, Point2D position, Point2D speed) {
    super(world, position, speed, IMAGE);
  }

  public void hit(){
    speed = speed.multiply(-1);
  }
}
