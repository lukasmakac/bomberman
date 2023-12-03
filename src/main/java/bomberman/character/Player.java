package bomberman.character;

import bomberman.World;
import javafx.geometry.Point2D;

public class Player extends Character {

  public static final String IMAGE = "bomberman.png";
  private final Integer step = 10;

  private Point2D position;


  public Player(World world, Point2D position) {
    super(world, position, IMAGE);
    this.position = position;
  }

  public Point2D getPosition() {
    return position;
  }

  public void setPosition(Point2D position) {
    this.position = position;
  }

  public void moveUp() {
    this.position.add(step, 0);
  }

  public void moveDown() {
    this.position.subtract(step, 0);
  }

  public void moveRight() {
    this.position.subtract(0, step);
  }

  public void moveLeft() {
    this.position.add(0, step);
  }

}
