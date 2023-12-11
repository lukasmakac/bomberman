package bomberman;

import static bomberman.constant.StaticLayout.BRICKS;
import static bomberman.constant.StaticLayout.WALLS;

import bomberman.character.Enemy;
import bomberman.constant.PlayerStatus;
import bomberman.solid.Fire;
import java.util.List;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

public class SimulationService extends ScheduledService<Void> {

  private final GameController controller;
  private final World world;

  private final Double interval;

  public SimulationService(GameController controller, World world, double interval) {
    this.controller = controller;
    this.world = world;
    this.interval = interval;
  }

  @Override
  protected Task<Void> createTask() {
    return new Task<>() {
      @Override
      protected Void call() {
        simulate(interval);
        return null;
      }
    };
  }

  private void simulate(double timeDelta) {
    if (!world.getEnemies().isEmpty()) {
      world.getEnemies().forEach(e -> e.simulate(controller.getCanvas().getGraphicsContext2D(), timeDelta));
    }
    checkEnemyCollisions();
    checkExplosionCollisions();
  }

  private void checkEnemyCollisions() {
    if (world.getEnemies().isEmpty()) {
      world.getPlayer().setStatus(PlayerStatus.WINNER);
      controller.stopGame(); // WIN
    } else {
      for (Enemy e : world.getEnemies()) {
        handleObstacleCollisions(e, world);
        handleExplosionCollisions(e, world);
        handlePlayerCollisions(e, world);
      }
    }
  }

  private void checkExplosionCollisions() {
    if (!world.getExplosions().isEmpty()) {
      for (Fire explosion : world.getExplosions()) {
        handleExplosionCollision(explosion, world);
      }
    }
  }

  private void handleObstacleCollisions(Enemy enemy, World world) {
    if (enemy.hitBy(WALLS) || enemy.hitBy(BRICKS) || enemy.hitBy(world.getBombs()) || enemy.hitBy(
        enemiesExceptEnemy(enemy))) {
      enemy.changeDirection();
    }
  }

  private void handleExplosionCollisions(Enemy enemy, World world) {
    if (!world.getExplosions().isEmpty()) {
      if (enemy.hitBy(world.getExplosions())) {
        world.getPlayer().addScore(enemy.getPoints());
        world.removeEnemy(enemy);
      }
    }
  }

  private void handlePlayerCollisions(Enemy enemy, World world) {
    if (enemy.hitBy(world.getPlayer())) {
      world.getPlayer().setStatus(PlayerStatus.DEAD);
      controller.stopGame(); // LOSE
    }
  }

  private void handleExplosionCollision(Fire explosion, World world) {
    BRICKS.removeIf(b -> b.getBoundingBox().intersects(explosion.getBoundingBox()));

    if (world.getPlayer().hitBy(explosion)) {
      world.getPlayer().setStatus(PlayerStatus.DEAD);
      controller.stopGame();
    }
  }

  private List<Enemy> enemiesExceptEnemy(Enemy e) {
    return world.getEnemies().stream().filter(enemy -> !enemy.equals(e)).toList();
  }

}
