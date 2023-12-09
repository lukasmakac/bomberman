package bomberman;

import javafx.animation.AnimationTimer;

public class DrawingThread extends AnimationTimer {

	private final World world;
	private final GameController controller;

	public static final int MAX_STEP = 5;

	private long lastTime = -1;

	public DrawingThread(World world, GameController controller) {
		this.world = world;
		this.controller = controller;
	}

	@Override
	public void handle(long now) {
		world.draw();

		//time are in nanoseconds and method simulate expects seconds
		world.simulate(Math.min(MAX_STEP, (now - lastTime) / (1.5 * 1e11)));
		world.checkEnemyCollisions(controller::stopGame);

		controller.setPositionText(
				"[" + world.getPlayer().getPosition().getX() + "," + world.getPlayer().getPosition().getY() + "]");

		lastTime = now;
	}

}
