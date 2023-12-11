package bomberman.character;

import static bomberman.common.Utils.getImage;

import javafx.geometry.Point2D;

public class Player extends Character {

  public static final String IMAGE = "bomberman.png";
  private final Integer step = 10;

  private Integer score = 0;

  public Player(Point2D position) {
    super(getImage(IMAGE), position);
    this.position = position;
  }

  public Point2D moveUp() {
    return this.position.subtract(0, step);
  }

  public Point2D moveDown() {
    return this.position.add(0, step);
  }

  public Point2D moveRight() {
    return this.position.add(step, 0);
  }

  public Point2D moveLeft() {
    return this.position.subtract(step, 0);
  }

  public void addScore(Integer points) {
    this.score += points;
  }

  public Integer getScore() {
    return score;
  }

}
