package bomberman;

import bomberman.character.Enemy;
import bomberman.character.Player;
import bomberman.character.RedFace;
import bomberman.character.Sorcerer;
import bomberman.common.Utils;
import bomberman.constant.Direction;
import bomberman.solid.Bomb;
import bomberman.solid.Fire;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.geometry.Point2D;

public class World {

	private final List<Enemy> enemies;

	private final Player player;
	private final List<Bomb> bombs;

	private final Set<Fire> explosions;

	public World() {
		this.enemies = new ArrayList<>();

		this.enemies.add(new RedFace(new Point2D(70, 20)));
		this.enemies.add(new Sorcerer(new Point2D(175, 270)));
		this.enemies.add(new Sorcerer(new Point2D(160, 220)));

		this.player = new Player(new Point2D(470, 470));
		this.bombs = new ArrayList<>();
		this.explosions = new HashSet<>();
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

  public List<Bomb> getBombs() {
    return bombs;
  }

	public Set<Fire> getExplosions() {
		return explosions;
	}

	public Player getPlayer() {
		return player;
	}

	public synchronized void removeEnemy(Enemy enemy) {
		this.enemies.remove(enemy);
	}

	public void dropBomb(Point2D position) {
		this.bombs.add(new Bomb(position));
	}

	public void explode(Bomb bomb) {
		Point2D center = bomb.getPosition();

		Collection<Fire> temporal = new ArrayList<>();

		Utils.generateXPoints(center, Bomb.EXPLOSION_SIZE, Direction.ADD).forEach(e -> temporal.add(new Fire(e)));
		Utils.generateXPoints(center, Bomb.EXPLOSION_SIZE, Direction.SUBTRACT).forEach(e -> temporal.add(new Fire(e)));
		Utils.generateYPoints(center, Bomb.EXPLOSION_SIZE, Direction.ADD).forEach(e -> temporal.add(new Fire(e)));
		Utils.generateYPoints(center, Bomb.EXPLOSION_SIZE, Direction.SUBTRACT).forEach(e -> temporal.add(new Fire(e)));

		explosions.addAll(temporal);

		Executors.newSingleThreadScheduledExecutor()
				.schedule(() -> explosions.removeAll(temporal), Fire.TIME_TO_CEASE, TimeUnit.SECONDS);

		bombs.remove(bomb);
	}

}
