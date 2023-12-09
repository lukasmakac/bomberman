package bomberman;

import static bomberman.constant.StaticLayout.BRICKS;
import static bomberman.constant.StaticLayout.WALLS;

import bomberman.character.Enemy;
import java.util.List;
import javafx.animation.AnimationTimer;

public class DrawingThread extends AnimationTimer {

  private final GameController controller;
  private final World world;

  public static final int MAX_STEP = 5;

  private long lastTime = -1;

  public DrawingThread(World world, GameController controller) {
    this.world = world;
    this.controller = controller;
  }

  @Override
  public void handle(long now) {
    printPosition();
    world.draw();

    //time are in nanoseconds and method simulate expects seconds
    simulate(Math.min(MAX_STEP, (now - lastTime) / (1.5 * 1e11)));

    lastTime = now;
  }

  private void simulate(double timeDelta) {
    world.getEnemies().forEach(e -> e.simulate(world.gc(), timeDelta));
    checkEnemyCollisions();
  }

  public void checkEnemyCollisions() {
    world.getEnemies().parallelStream().forEach(e -> {
      if (e.hitBy(world.getPlayer())) {
        // stop program
        controller.stopGame();
      }

      if (e.hitBy(WALLS) || e.hitBy(BRICKS) || e.hitBy(world.getBombs()) || e.hitBy(enemiesExceptEnemy(e))) {
        e.changeDirection();
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
