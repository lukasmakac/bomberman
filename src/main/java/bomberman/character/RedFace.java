package bomberman.character;

import bomberman.World;
import javafx.geometry.Point2D;

public class RedFace extends Enemy {
  private static final String IMAGE = "red_face.gif";
  private static final Integer SPEED = 20;

  public RedFace(World world, Point2D position) {
    super(world, position, SPEED, IMAGE);
  }

}
