package bomberman.handler;

import static bomberman.common.Utils.getBoundingBox;
import static bomberman.constant.StaticLayout.BRICKS;
import static bomberman.constant.StaticLayout.WALLS;

import bomberman.World;
import bomberman.character.Player;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyEvent;

public class PlayerMovementHandler implements EventHandler<KeyEvent> {

  private final World world;

  public PlayerMovementHandler(World world) {
    this.world = world;
  }

  @Override
  public void handle(KeyEvent keyEvent) {
    Player player = world.getPlayer();

    switch (keyEvent.getCode()) {
      case UP -> updatePosition(player, player.moveUp());
      case DOWN -> updatePosition(player, player.moveDown());
      case LEFT -> updatePosition(player, player.moveLeft());
      case RIGHT -> updatePosition(player, player.moveRight());

      case SPACE -> world.dropBomb(player.getPosition());
    }
  }

  private void updatePosition(Player player, Point2D newPosition) {
    if (canMove(newPosition)) {
      player.setPosition(newPosition);
    }
  }

  private boolean canMove(Point2D position) {
    Rectangle2D boundingBox = getBoundingBox(position);

    return !(
        BRICKS.stream().anyMatch(b -> b.getBoundingBox().intersects(boundingBox)) ||
            WALLS.stream().anyMatch(w -> w.getBoundingBox().intersects(boundingBox)) ||
//        world.getBombs().stream().anyMatch(b -> b.getBoundingBox().intersects(boundingBox) && !b.getBoundingBox().equals(boundingBox)) ||
            position.getX() < 0 ||
            position.getY() < 0 ||
            boundingBox.getMaxX() > world.getCanvas().getWidth() ||
            boundingBox.getMaxY() > world.getCanvas().getHeight());
  }
}
