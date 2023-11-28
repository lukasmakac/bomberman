package bomberman.solid;

import static bomberman.common.Utils.getImage;

import bomberman.World;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Wall {
    public static final Image IMAGE = getImage("wall.png");

    private Point2D position;

    public Wall(Point2D position){
        this.position = position;
    };

    public void draw(GraphicsContext gc, World world) {
        Point2D canvasPosition = world.getCanvasPoint(position);
        gc.drawImage(IMAGE, canvasPosition.getX(), canvasPosition.getY(), 20, 20);
    }
}
