package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class World {
	private double width;
	private double height;
	private BulletAnimated bulletAnimatted;
	private Cannon cannon;
	private Dragon[] dragons;

	public World(double width, double height) {
		super();
		this.width = width;
		this.height = height;
		cannon = new Cannon(this, new Point2D(50, 50), new Point2D(100, 20));
		bulletAnimatted = new BulletAnimated(this, cannon, new Point2D(30, 60), new Point2D(0, 0), 40);
		dragons = new Dragon[] { new Dragon(this, new Point2D(50, 200), new Point2D(100, 5)),
				new Dragon(this, new Point2D(50, 230), new Point2D(60, 5)),
				new Dragon(this, new Point2D(50, 270), new Point2D(-50, 20)) };
	}

	public Point2D getCanvasPoint(Point2D worldPoint) {
		return new Point2D(worldPoint.getX(), height - worldPoint.getY());
	}

	public void draw(Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		cannon.draw(gc);
		bulletAnimatted.draw(gc);
		for(Dragon dragon: dragons) {
			dragon.draw(gc);
		}
	}

	public void simulate(double timeDelta) {
		bulletAnimatted.simulate(timeDelta);
		cannon.simulate(timeDelta);
		for(Dragon dragon: dragons) {
			if (bulletAnimatted.overlaps(dragon)) {
				dragon.hit();
				bulletAnimatted.reload();
			}
			dragon.simulate(timeDelta);
		}
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

}
