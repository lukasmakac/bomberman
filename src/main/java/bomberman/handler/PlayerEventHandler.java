package bomberman.handler;

import bomberman.character.Player;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class PlayerEventHandler implements EventHandler<KeyEvent> {

  private final Player player;

  public PlayerEventHandler(Player player) {
    this.player = player;
  }

  @Override
  public void handle(KeyEvent keyEvent) {
    switch (keyEvent.getCode()) {
      case UP -> player.moveUp();
      case DOWN -> player.moveDown();
      case LEFT -> player.moveLeft();
      case RIGHT -> player.moveRight();
      case SPACE -> player.dropBomb();
    }
  }
}
