package bomberman.character;

import bomberman.constant.MovementType;
import javafx.geometry.Point2D;

public class Sorcerer extends Enemy {
  private static final String IMAGE = "sorcerer.gif";

  private static final int SPEED = 50;

  public Sorcerer(Point2D position) {
    super(position, SPEED, IMAGE, MovementType.HORIZONTAL);
  }

  @Override
  public Integer getPoints() {
    return 50;
  }
}
