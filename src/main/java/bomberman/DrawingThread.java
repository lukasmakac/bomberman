package bomberman;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;

public class DrawingThread extends AnimationTimer {

	private final World world;
	private long lastTime = -1;

	public DrawingThread(World world) {
		this.world = world;
		this.world.getCanvas().addEventHandler(KeyEvent.KEY_TYPED, new PlayerEventHandler(world.getPlayer()));
	}

	@Override
	public void handle(long now) {
		world.draw();

		//time are in nanoseconds and method simulate expects seconds
		world.simulate((now - lastTime) / (1.5 * 1e11));

		lastTime = now;
	}

}
