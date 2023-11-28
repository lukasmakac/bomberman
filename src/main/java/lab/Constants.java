package lab;

import javafx.scene.image.Image;

public final class Constants {
	private Constants() {}
	
	public static final double GRAVITATIONAL_ACCELERATION = 9.81;
	
	public static final double AIR_DENSITY = 1.2;
	
	public static final Image WALL_IMAGE;
	public static final Image ENEMY_IMAGE;
	
	static{ 
		WALL_IMAGE = new Image(Constants.class.getResourceAsStream("wall.png"));
		ENEMY_IMAGE = new Image(Constants.class.getResourceAsStream("enemy.gif"));
	}
}
