package bomberman;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DrawingThread extends AnimationTimer {

	private final World world;
	private long lastTime = -1;

	public DrawingThread(World world) {
		this.world = world;
	}

	@Override
	public void handle(long now) {
		world.draw();
		if (lastTime > 0) {
			//time are in nanoseconds and method simulate expects seconds
			world.simulate((now - lastTime) / (1.5 * 1e11));
		}
		lastTime = now;
	}

}
