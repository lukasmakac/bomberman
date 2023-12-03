package bomberman;

import bomberman.character.Player;
import bomberman.character.RedFace;
import bomberman.character.Sorcerer;
import bomberman.solid.Wall;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import bomberman.character.Enemy;

public class World {
	private double width;
	private double height;
	private Wall walls[];
	private Wall middleCubes[];
	private Enemy redFace;
	private Enemy sorcerer;
	private Player player;

	public World(double width, double height) {
 		this.width = width;
		this.height = height;

		this.walls = new Wall[92];
		this.middleCubes = new Wall[530];

		this.redFace = new RedFace(this, new Point2D(20,40));
		this.sorcerer = new Sorcerer(this, new Point2D(20,140));

		this.player = new Player(this, new Point2D(50, 50));

		// okrajové steny
		for(int i = 0; i < 23; i++){
			walls[i] = new Wall(new Point2D( i*20,20));										// bottom wall
			walls[i+23] = new Wall(new Point2D( i*20,this.height)); 						// top wall
			walls[i+46] = new Wall(new Point2D(0,i*20));										// left wall
			walls[i+69] = new Wall(new Point2D(this.width - 20, i*20));		// right wall
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
	}

	public Point2D getCanvasPoint(Point2D worldPoint) {
		return new Point2D(worldPoint.getX(), height - worldPoint.getY());
	}

	public void draw(GraphicsContext gc) {
		gc.clearRect(0, 0, width, height);
		gc.setFill(Color.GREEN);
		gc.fillRect(0,0,width,height);

		for(Wall w : walls){
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

		redFace.draw(gc);
		sorcerer.draw(gc);
		player.draw(gc);

	}

	public void simulate(double timeDelta) {
		redFace.simulate(timeDelta);
		sorcerer.simulate(timeDelta);
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


	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}


}
