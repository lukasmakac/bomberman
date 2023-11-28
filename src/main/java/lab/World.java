package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class World {
	private double width;
	private double height;
	private Wall wall[];
	private Wall middleCubes[];
	private Enemy enemy;


	/*
	private BulletAnimated bulletAnimatted;
	private Cannon cannon;
	private Dragon[] dragons;*/

	public World(double width, double height) {
 		this.width = width;
		this.height = height;

		this.wall = new Wall[92];
		this.middleCubes = new Wall[530];
		this.enemy = new Enemy(this, new Point2D(20,40),new Point2D(10,10), true);

		// okrajové steny
		for(int i = 0; i < 23; i++){
			wall[i] = new Wall(new Point2D( i*20,20));	// bottom wall
			wall[i+23] = new Wall(new Point2D( i*20,this.height)); // top wall
			wall[i+46] = new Wall(new Point2D(0,i*20));	// left wall
			wall[i+69] = new Wall(new Point2D(this.width - 20, i*20));// right wall
		}

		int n = 0;

		// kocky v poli
		for(int i = 0; i < 23; i++){
			for(int j = 0; j < 23; j++){
				n++;
				if(i % 2 == 1 && i > 2){
					if(j % 2 == 0 && j > 1){
						middleCubes[n] = new Wall(new Point2D(j*20, i*20));
					}
				}
			}

		}


		/*cannon = new Cannon(this, new Point2D(50, 50), new Point2D(100, 20));
		bulletAnimatted = new BulletAnimated(this, cannon, new Point2D(30, 60), new Point2D(0, 0), 40);
		dragons = new Dragon[] { new Dragon(this, new Point2D(50, 200), new Point2D(100, 5)),
				new Dragon(this, new Point2D(50, 230), new Point2D(60, 5)),
				new Dragon(this, new Point2D(50, 270), new Point2D(-50, 20)) };*/
	}

	public Point2D getCanvasPoint(Point2D worldPoint) {
		return new Point2D(worldPoint.getX(), height - worldPoint.getY());
	}

	public void draw(GraphicsContext gc) {
		gc.clearRect(0, 0, width, height);
		gc.setFill(Color.GREEN);
		gc.fillRect(0,0,width,height);

		for(Wall w : wall){
			w.draw(gc,this);
		}

		int n = 0;
		for(int i = 0; i < 23; i++){
			for(int j = 0; j < 23; j++){
				n++;
				if(i % 2 == 1 && i > 2){
					if(j % 2 == 0 && j > 1){
						middleCubes[n].draw(gc,this);
					}
				}
			}
		}

		enemy.draw(gc);


		/*cannon.draw(gc);
		bulletAnimatted.draw(gc);
		for(Dragon dragon: dragons) {
			dragon.draw(gc);
		}*/
	}

	public void simulate(double timeDelta) {
		enemy.simulate(timeDelta);
		/*bulletAnimatted.simulate(timeDelta);
		cannon.simulate(timeDelta);
		for(Dragon dragon: dragons) {
			if (bulletAnimatted.overlaps(dragon)) {
				dragon.hit();
				bulletAnimatted.reload();
			}
			dragon.simulate(timeDelta);
		}*/
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
