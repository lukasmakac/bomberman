package bomberman.constant;

import static bomberman.common.Utils.generateXPoints;
import static bomberman.common.Utils.generateXYPoints;
import static bomberman.common.Utils.generateYPoints;
import static bomberman.common.Utils.toWalls;

import bomberman.solid.Brick;
import bomberman.solid.Wall;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

public final class StaticLayout {

  private StaticLayout() {
  }

  public static final List<Wall> WALLS = new ArrayList<>();
  public static final List<Brick> BRICKS = new ArrayList<>();
  
  static {
    WALLS.addAll(toWalls(generateXPoints(new Point2D(0, 0), 20, 510)));    // TOP
    WALLS.addAll(toWalls(generateXPoints(new Point2D(0, 490), 20, 510)));  // BOTTOM
    WALLS.addAll(toWalls(generateYPoints(new Point2D(0, 0), 20, 510)));    // LEFT
    WALLS.addAll(toWalls(generateYPoints(new Point2D(490, 0), 20, 510)));  // RIGHT

    WALLS.addAll(toWalls(generateXYPoints(new Point2D(45, 45), 50, 480)));
    WALLS.addAll(toWalls(generateXYPoints(new Point2D(95, 95), 50, 480)));
    WALLS.addAll(toWalls(generateXYPoints(new Point2D(145, 145), 50, 480)));
    WALLS.addAll(toWalls(generateXYPoints(new Point2D(195, 195), 50, 480)));
    WALLS.addAll(toWalls(generateXYPoints(new Point2D(245, 245), 50, 480)));
    WALLS.addAll(toWalls(generateXYPoints(new Point2D(295, 295), 50, 480)));
    WALLS.addAll(toWalls(generateXYPoints(new Point2D(345, 345), 50, 480)));
    WALLS.addAll(toWalls(generateXYPoints(new Point2D(395, 395), 50, 480)));
    WALLS.addAll(toWalls(generateXYPoints(new Point2D(445, 445), 50, 480)));
    WALLS.addAll(toWalls(generateXYPoints(new Point2D(495, 495), 50, 495)));

    for (int i = 1; i < 6; i++) {
      int j = i + 1;
      BRICKS.add(new Brick(new Point2D(16 + 25 * i, 42 + i * 25)));
      BRICKS.add(new Brick(new Point2D(218, 43 + 25 * i)));
    }

    for (int i = 1; i < 4; i++) {
      BRICKS.add(new Brick(new Point2D(20 + 26 * i, 20)));
      BRICKS.add(new Brick(new Point2D(90 + 26 * i, 118)));

    }

    BRICKS.add(new Brick(new Point2D(145, 268)));

  }

}
