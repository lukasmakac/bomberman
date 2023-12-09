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

  @Override
  public void collision() {
    this.position = new Point2D(position.getX(), position.getY()); // no changes in position when hit the bricks
  }

  /* ???
  public void setPosition(double position){
    this.position = new Point2D(this.position.getX(),position);
  }*/

  public void simulate(double timeDelta) {
    // aktualizácia polohy
  }

  public void moveUp() {
    this.position = this.position.add(step, 0);
    System.out.println("moveUp ");
  }
  public void moveDown() {
    this.position = this.position.subtract(step, 0);
    System.out.println("moveDown ");
  }
  public void moveRight() {
    this.position = this.position.subtract(0, step);
    System.out.println("moveRight ");

  }
  public void moveLeft() {
    System.out.println("moveLeft ");
    this.position = this.position.add(0, step);
  }

  public void dropBomb() {

  }

}
