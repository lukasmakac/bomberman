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
    checkCollisions();
  }

  private void checkCollisions() {
    if (world.getEnemies().isEmpty()) {
      world.getPlayer().setStatus(PlayerStatus.WINNER);
      controller.stopGame(); // WIN
    } else {
      for (Enemy e : world.getEnemies()) {
        if (e.hitBy(WALLS) || e.hitBy(BRICKS) || e.hitBy(world.getBombs()) || e.hitBy(enemiesExceptEnemy(e))) {
          e.changeDirection();
        }

        if (e.hitBy(world.getPlayer())) {
          world.getPlayer().setStatus(PlayerStatus.DEAD);
          controller.stopGame(); // LOSE
        }

        if (!world.getExplosions().isEmpty()) {
          if (e.hitBy(world.getExplosions())) {
            world.getPlayer().addScore(e.getPoints());
            world.removeEnemy(e);
          }
          if (world.getPlayer().hitBy(world.getExplosions())) {
            world.getPlayer().setStatus(PlayerStatus.DEAD);
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

}
