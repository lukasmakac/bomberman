package bomberman;

import static bomberman.constant.StaticLayout.BRICKS;
import static bomberman.constant.StaticLayout.WALLS;

import bomberman.character.Enemy;
import bomberman.handler.PlayerMovementHandler;
import java.util.List;
import javafx.animation.AnimationTimer;
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
    world.draw();

    //time are in nanoseconds and method simulate expects seconds
    simulate(Math.min(MAX_STEP, (now - lastTime) / (1.5 * 1e11)));

    lastTime = now;
  }

  public EventHandler<KeyEvent> getKeyEventHandler() {
    return keyEventHandler;
  }

  private void simulate(double timeDelta) {
    world.getEnemies().forEach(e -> e.simulate(world.gc(), timeDelta));
    checkCollisions();
  }

  private void checkCollisions() {
    world.getEnemies().forEach(e -> {
      if (e.hitBy(world.getPlayer())) {
        // stop program
        controller.stopGame();
      }

      if (e.hitBy(WALLS) || e.hitBy(BRICKS) || e.hitBy(world.getBombs()) || e.hitBy(enemiesExceptEnemy(e))) {
        e.changeDirection();
      }

      if (e.hitBy(world.getExplosions())) {
        world.getEnemies().remove(e);
        controller.addScore(e.getPoints());
      }

      if (world.getPlayer().hitBy(world.getExplosions())) {
        controller.stopGame();
      }
    });
  }

  private List<Enemy> enemiesExceptEnemy(Enemy e) {
    return world.getEnemies().stream().filter(enemy -> !enemy.equals(e)).toList();
  }

  private void printPosition() {
    controller.setPositionText(
        "[" + world.getPlayer().getPosition().getX() + "," + world.getPlayer().getPosition().getY() + "]");
  }
}
