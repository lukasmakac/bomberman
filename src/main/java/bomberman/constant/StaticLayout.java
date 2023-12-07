package bomberman.constant;

import static bomberman.common.Utils.generateXPoints;
import static bomberman.common.Utils.generateXYPoints;
import static bomberman.common.Utils.generateYPoints;
import static bomberman.common.Utils.toBricks;
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
    WALLS.addAll(toWalls(generateXPoints(new Point2D(0, 0), 20, 440)));    // TOP
    WALLS.addAll(toWalls(generateXPoints(new Point2D(0, 440), 20, 440)));  // BOTTOM
    WALLS.addAll(toWalls(generateYPoints(new Point2D(0,0), 20, 460)));    // LEFT
    WALLS.addAll(toWalls(generateYPoints(new Point2D(440,0), 20, 460)));  // RIGHT

    BRICKS.addAll(toBricks(generateXYPoints(new Point2D(40,60), 40, 440))); 
    BRICKS.addAll(toBricks(generateXYPoints(new Point2D(80,100), 40, 440))); 
    BRICKS.addAll(toBricks(generateXYPoints(new Point2D(120,140), 40, 440))); 
    BRICKS.addAll(toBricks(generateXYPoints(new Point2D(160,180), 40, 440))); 
    BRICKS.addAll(toBricks(generateXYPoints(new Point2D(200,220), 40, 440))); 
    BRICKS.addAll(toBricks(generateXYPoints(new Point2D(240,260), 40, 440))); 
    BRICKS.addAll(toBricks(generateXYPoints(new Point2D(280,300), 40, 440))); 
    BRICKS.addAll(toBricks(generateXYPoints(new Point2D(320,340), 40, 440))); 
    BRICKS.addAll(toBricks(generateXYPoints(new Point2D(360,380), 40, 440))); 
    BRICKS.addAll(toBricks(generateXYPoints(new Point2D(400,420), 40, 440))); 
  }

}
