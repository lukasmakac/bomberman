package bomberman;

import static bomberman.constant.StaticLayout.BRICKS;
import static bomberman.constant.StaticLayout.WALLS;

import bomberman.character.Enemy;
import bomberman.character.Player;
import bomberman.character.RedFace;
import bomberman.character.Sorcerer;
import bomberman.solid.Brick;
import bomberman.solid.Wall;
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

	public World(Canvas canvas) {
		this.canvas = canvas;
		this.enemies = new ArrayList<>();

		this.enemies.add(new RedFace(new Point2D(140, 180)));
		this.enemies.add(new Sorcerer(new Point2D(160, 140)));

		this.player = new Player(new Point2D(200, 180));
	}

	public void draw() {
		gc().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc().setFill(Color.GREEN);
		gc().fillRect(0,0, canvas.getWidth(), canvas.getHeight());

		for(Wall w: WALLS){
			w.draw(canvas.getGraphicsContext2D());
		}

		for (Brick b: BRICKS) {
			b.draw(canvas.getGraphicsContext2D());
		}

		enemies.forEach(e -> e.draw(gc()));
		player.draw(gc());
	}

	public void simulate(double timeDelta) {
		enemies.forEach(e -> e.simulate(gc(), timeDelta));
	}

	public void checkEnemyCollisions(Runnable exitAction) {
		enemies.parallelStream().forEach(e -> {
			if (e.hitBy(player)) {
				exitAction.run();
			}

			if (e.hitBy(WALLS) || e.hitBy(BRICKS) || enemies.stream().filter(enemy -> !enemy.equals(e)).anyMatch(e::hitBy)) {
				e.collision();
			}
		});
	}


	public Player getPlayer() {
		return player;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	protected GraphicsContext gc() {
		return canvas.getGraphicsContext2D();
	}

}
