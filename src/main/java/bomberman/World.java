package bomberman;

import static bomberman.constant.StaticLayout.BRICKS;
import static bomberman.constant.StaticLayout.WALLS;

import bomberman.character.Enemy;
import bomberman.character.Player;
import bomberman.character.RedFace;
import bomberman.character.Sorcerer;
import bomberman.solid.Bomb;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class World {

	private final Canvas canvas;
	private final List<Enemy> enemies;

	private final Player player;
	private final List<Bomb> bombs;

	public World(Canvas canvas) {
		this.canvas = canvas;
		this.enemies = new ArrayList<>();

		this.enemies.add(new RedFace(new Point2D(70, 20)));
		this.enemies.add(new Sorcerer(new Point2D(175, 270)));
		this.enemies.add(new Sorcerer(new Point2D(160, 220)));

		this.player = new Player(new Point2D(470, 470));
		this.bombs = new ArrayList<>();
	}

	public void draw() {
		gc().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc().setFill(Color.GREEN);
		gc().fillRect(0,0, canvas.getWidth(), canvas.getHeight());

		WALLS.forEach(w -> w.draw(gc()));
		BRICKS.forEach(b -> b.draw(gc()));

		enemies.forEach(e -> e.draw(gc()));
		bombs.forEach(b -> b.draw(gc()));

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

	public Player getPlayer() {
		return player;
	}

	public void removeEnemy(Enemy enemy) {
		this.enemies.remove(enemy);
	}

	public void dropBomb(Point2D position) {
		this.bombs.add(new Bomb(position));
	}

	public void removeBomb(Bomb e) {
		this.bombs.remove(e);
	}

	protected GraphicsContext gc() {
		return canvas.getGraphicsContext2D();
	}

}
