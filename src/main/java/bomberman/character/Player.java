package bomberman.character;

import bomberman.World;
import javafx.geometry.Point2D;

public class Player extends Character {

  public static final String IMAGE = "bomberman.png";

  protected Player(World world, Point2D position) {
    super(world, position, IMAGE);
  }

}
