package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Wall {
    private Point2D position;

    public Wall(Point2D position){
        this.position = position;
    };

    public void draw(GraphicsContext gc, World world) {
        Point2D canvasPosition = world.getCanvasPoint(position);
        gc.drawImage(Constants.WALL_IMAGE, canvasPosition.getX(), canvasPosition.getY(), 20, 20);
    }
}
