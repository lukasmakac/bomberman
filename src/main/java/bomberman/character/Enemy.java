package bomberman.character;

import bomberman.World;
import bomberman.common.Simulable;
import javafx.geometry.Point2D;

public abstract class Enemy extends Character implements Simulable {

    protected Integer speed;

    public Enemy(World world, Point2D position, Integer speed, String imagePath) {
        super(world, position, imagePath);
        this.speed = speed;
    }

    @Override
    public void simulate(double deltaT) {
        double newX = (position.getX() + speed * deltaT + world.getWidth()) % world.getWidth();
        double newY = (position.getY() + speed * deltaT + world.getHeight()) % world.getHeight();

        position = new Point2D(newX, newY);
    }

    public void hit(){
        speed *= -1;
    }

}
