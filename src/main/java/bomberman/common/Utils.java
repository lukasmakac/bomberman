package bomberman.common;

import bomberman.character.Character;
import bomberman.solid.Brick;
import bomberman.solid.Wall;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public final class Utils {

  private static final String IMAGE_PATH_PREFIX = "/bomberman/pictures/";

  private Utils() {
  }

  public static Image getImage(String path) {
    return new Image(Objects.requireNonNull(Utils.class.getResourceAsStream(IMAGE_PATH_PREFIX + path)));
  }

  public static double translateY(double src, Double height) {
    return height - src;
  }

  public static List<Brick> toBricks(List<Point2D> points) {
    return points.stream().map(Brick::new).toList();
  }

  public static List<Wall> toWalls(List<Point2D> points) {
    return points.stream().map(Wall::new).toList();
  }

  public static List<Point2D> generateXYPoints(Point2D start, Integer step, Integer limit) {
    List<Point2D> points = new ArrayList<>();

    points.addAll(generateXPoints(start,step,limit));
    points.addAll(generateYPoints(start,step,limit));

    return points;
  }

  public static List<Point2D> generateXPoints(Point2D start, Integer step, Integer limit) {
    return generateSequence((int) start.getX(), step, limit).map(x -> new Point2D(x, start.getY())).toList();
  }

  public static List<Point2D> generateYPoints(Point2D start, int step, int limit) {
    return generateSequence((int) start.getY(), step, limit).map(y -> new Point2D(start.getX(), y)).toList();
  }

  private static Stream<Integer> generateSequence(int seed, int step, int limit) {
    return Stream.iterate(seed, i -> i < limit, i -> {
          i+=step;
          return i;
        }
    );
  }

  public static Rectangle2D getBoundingBox(Point2D position) {
    return new Rectangle2D(position.getX(), position.getY(), Character.SIZE, Character.SIZE);
  }
}
