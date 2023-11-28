package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class Enemy implements DrawableSimulable {

    private Point2D position;
    private Point2D speed;
    private final double size = 20;
    private final World world;
    boolean vertical;

    public Enemy(World world, Point2D position, Point2D speed, boolean vertical){
        this.world = world;
        this.position = position;
        this.speed = speed;
        this.vertical = vertical;
    }

    @Override
    public void draw(GraphicsContext gc) {
        Point2D canvasPosition = world.getCanvasPoint(position);
        gc.drawImage(Constants.ENEMY_IMAGE, canvasPosition.getX(), canvasPosition.getY(),size,size);
    }

    @Override
    public void simulate(double deltaT) {
        double timeDeltaS = deltaT;
        // ako spraviť smer vertikálny a horizontálny
        double newX = (position.getX() + speed.getX() * timeDeltaS + world.getWidth()) % world.getWidth();
        double newY = (position.getY() + speed.getY() * timeDeltaS + world.getHeight()) % world.getHeight();
        position = new Point2D(newX, newY);
    }

    public Rectangle2D getBoundingBox(){
        return new Rectangle2D(position.getX(),position.getY(), size, size);
    }

    /* neviem či je to správne, možno by som zakomponovala
              changeDirection()
              randomDirection(boolean vertical) */
    public void hit(){

        speed = speed.multiply(-1);
    }
}
