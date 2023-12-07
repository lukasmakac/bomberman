package bomberman;

import javafx.animation.AnimationTimer;

public class DrawingThread extends AnimationTimer {

	private final World world;
	private long lastTime = -1;

	public DrawingThread(World world) {
		this.world = world;
	}

	@Override
	public void handle(long now) {
		world.draw();

		//time are in nanoseconds and method simulate expects seconds
		world.simulate((now - lastTime) / (1.5 * 1e11));

		lastTime = now;
	}

}
