package bomberman.character;

import bomberman.constant.MovementType;
import javafx.geometry.Point2D;

public class RedFace extends Enemy {
  private static final String IMAGE = "red_face.gif";
  private static final Integer SPEED = 20;

  public RedFace(Point2D position) {
    super(position, SPEED, IMAGE, MovementType.VERTICAL);
  }

  @Override
  public Integer getPoints() {
    return 25;
  }
}
