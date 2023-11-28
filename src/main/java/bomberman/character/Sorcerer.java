package bomberman.character;

import bomberman.World;
import javafx.geometry.Point2D;

public class Sorcerer extends Enemy {
  private static final String IMAGE = "sorcerer.gif";

  private static final int SPEED = 50;

  public Sorcerer(World world, Point2D position) {
    super(world, position, SPEED, IMAGE);
  }


}
