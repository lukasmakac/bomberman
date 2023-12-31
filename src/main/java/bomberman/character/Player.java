package bomberman.character;

import static bomberman.common.Utils.getImage;

import javafx.geometry.Point2D;

public class Player extends Character {

  public static final String IMAGE = "bomberman.png";
  private final Integer step = 10;

  private Point2D position;

  public Player(Point2D position) {
    super(getImage(IMAGE), position);
    this.position = position;
  }

  public void moveUp() {
    this.position = this.position.add(step, 0);
  }

  public void moveDown() {
    this.position = this.position.subtract(step, 0);
  }

  public void moveRight() {
    this.position = this.position.subtract(0, step);
  }

  public void moveLeft() {
    this.position = this.position.add(0, step);
  }

}
