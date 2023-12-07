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
import java.util.function.Consumer;
import java.util.function.Supplier;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class World {

	private final Canvas canvas;
	private final List<Enemy> enemies;
	private Player player;

	public World(Canvas canvas) {
		this.canvas = canvas;
		this.enemies = new ArrayList<>();

		enemies.add(new RedFace(new Point2D(140, 180)));
		enemies.add(new Sorcerer(new Point2D(160, 140)));

		this.player = new Player(new Point2D(200, 140));
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

	public void checkCollisions(Runnable exitAction) {
		if(enemies.stream().anyMatch(e -> player.hitBy(e))){
			exitAction.run();
		}
	}


	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	protected GraphicsContext gc() {
		return canvas.getGraphicsContext2D();
	}

}
