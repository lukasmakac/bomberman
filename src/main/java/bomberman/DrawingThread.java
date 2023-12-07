package bomberman;

import javafx.animation.AnimationTimer;

public class DrawingThread extends AnimationTimer {

	private final World world;
	private Runnable exitAction;

	private long lastTime = -1;

	public DrawingThread(World world, Runnable exitAction) {
		this.world = world;
		this.exitAction = exitAction;
	}

	@Override
	public void handle(long now) {
		world.draw();

		//time are in nanoseconds and method simulate expects seconds
		world.simulate((now - lastTime) / (1.5 * 1e11));
		world.checkCollisions(exitAction);

		lastTime = now;
	}

}
