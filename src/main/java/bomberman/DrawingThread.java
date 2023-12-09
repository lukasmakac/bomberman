package bomberman;

import javafx.animation.AnimationTimer;

public class DrawingThread extends AnimationTimer {

	private final World world;

	// exitAction mi nie je jasné
	private final Runnable exitAction;

	public static final int MAX_STEP = 5;

	private long lastTime = -1;

	public DrawingThread(World world, Runnable exitAction) {
		this.world = world;
		this.exitAction = exitAction;
	}

	@Override
	public void handle(long now) {
		world.draw();

		//time are in nanoseconds and method simulate expects seconds
		world.simulate(Math.min(MAX_STEP, (now - lastTime) / (1.5 * 1e11)));
		world.checkEnemyCollisions(exitAction);

		lastTime = now;
	}

}
