package bomberman;

import static bomberman.constant.StaticLayout.BRICKS;
import static bomberman.constant.StaticLayout.WALLS;

import bomberman.character.Enemy;
import bomberman.handler.PlayerMovementHandler;
import bomberman.solid.Fire;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class DrawingThread extends AnimationTimer {

  private final GameController controller;
  private final World world;

  private final EventHandler<KeyEvent> keyEventHandler;

  public static final int MAX_STEP = 5;

  private long lastTime = -1;

  public DrawingThread(GameController controller) {
    this.controller = controller;
    this.world = controller.getWorld();

    this.keyEventHandler = new PlayerMovementHandler(world);
  }

  @Override
  public void handle(long now) {
    printPosition();
    Platform.runLater(world::draw);

    simulate(Math.min(MAX_STEP, (now - lastTime) / (1.5 * 1e11)));

    lastTime = now;
  }

  public EventHandler<KeyEvent> getKeyEventHandler() {
    return keyEventHandler;
  }

  private void simulate(double timeDelta) {
    if (!world.getEnemies().isEmpty()) {
      world.getEnemies().forEach(e -> e.simulate(world.gc(), timeDelta));
    }
    checkCollisions();
  }

  private void checkCollisions() {
    if (world.getEnemies().isEmpty()) {
      controller.stopGame(); // WIN
    } else {
      for (Enemy e : world.getEnemies()) {
        if (e.hitBy(WALLS) || e.hitBy(BRICKS) || e.hitBy(world.getBombs()) || e.hitBy(enemiesExceptEnemy(e))) {
          e.changeDirection();
        }

        if (e.hitBy(world.getPlayer())) {
          controller.stopGame(); // LOSE
        }

        if (!world.getExplosions().isEmpty()) {
          if (e.hitBy(world.getExplosions())) {
            controller.addScore(e.getPoints());
            world.removeEnemy(e);
          }
          if (world.getPlayer().hitBy(world.getExplosions())) {
            controller.stopGame(); // LOSE
          }
          for (Fire explosion : world.getExplosions()) {
            BRICKS.removeIf(b -> b.getBoundingBox().intersects(explosion.getBoundingBox()));
          }
        }
      }
    }

  }

  private List<Enemy> enemiesExceptEnemy(Enemy e) {
    return world.getEnemies().stream().filter(enemy -> !enemy.equals(e)).toList();
  }

  private void printPosition() {
    controller.setPositionText(
        "[" + world.getPlayer().getPosition().getX() + "," + world.getPlayer().getPosition().getY() + "]");
  }
}
