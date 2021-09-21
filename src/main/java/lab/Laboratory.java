package lab;

import javafx.scene.canvas.Canvas;

public class Laboratory {

	private World world;
	private Canvas canvas;
	
	public Laboratory(Canvas canvas) {
		this.canvas = canvas;
		this.world = new World(canvas.getWidth(), canvas.getHeight());	
	}
	
	public void draw(double deltaT) {
		world.draw(canvas);
		world.simulate(deltaT);
		
	}
}
