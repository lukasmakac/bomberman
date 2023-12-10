package bomberman;

import static bomberman.constant.StaticLayout.BRICKS;
import static bomberman.constant.StaticLayout.WALLS;

import bomberman.character.Enemy;
import bomberman.character.Player;
import bomberman.character.RedFace;
import bomberman.character.Sorcerer;
import bomberman.common.Utils;
import bomberman.constant.Direction;
import bomberman.solid.Bomb;
import bomberman.solid.Fire;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class World {

	private final Canvas canvas;
	private final List<Enemy> enemies;

	private final Player player;
	private final List<Bomb> bombs;

	private final Set<Fire> explosions;

	public World(Canvas canvas) {
		this.canvas = canvas;
		this.enemies = new ArrayList<>();

		this.enemies.add(new RedFace(new Point2D(70, 20)));
		this.enemies.add(new Sorcerer(new Point2D(175, 270)));
		this.enemies.add(new Sorcerer(new Point2D(160, 220)));

		this.player = new Player(new Point2D(470, 470));
		this.bombs = new ArrayList<>();
		this.explosions = new HashSet<>();
	}

	public void draw() {
		gc().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc().setFill(Color.GREEN);
		gc().fillRect(0,0, canvas.getWidth(), canvas.getHeight());

		WALLS.forEach(w -> w.draw(gc()));
		BRICKS.forEach(b -> b.draw(gc()));

		enemies.forEach(e -> e.draw(gc()));
		bombs.forEach(b -> b.draw(gc()));
		explosions.forEach(e -> e.draw(gc()));

		player.draw(gc());
	}

	public Canvas getCanvas() {
		return canvas;
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

		Utils.generateXPoints(center, Bomb.EXPLOSION_SIZE, Direction.ADD).forEach(e -> explosions.add(new Fire(e)));
		Utils.generateXPoints(center, Bomb.EXPLOSION_SIZE, Direction.SUBTRACT).forEach(e -> explosions.add(new Fire(e)));
		Utils.generateYPoints(center, Bomb.EXPLOSION_SIZE, Direction.ADD).forEach(e -> explosions.add(new Fire(e)));
		Utils.generateYPoints(center, Bomb.EXPLOSION_SIZE, Direction.SUBTRACT).forEach(e -> explosions.add(new Fire(e)));

		bombs.remove(bomb);
	}

	protected GraphicsContext gc() {
		return canvas.getGraphicsContext2D();
	}

}
