package bomberman.solid;

import static bomberman.common.Utils.getImage;
import static bomberman.common.Utils.translateY;

import bomberman.common.Collisional;
import bomberman.common.Drawable;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Wall implements Drawable, Collisional {
    public static final Image IMAGE = getImage("wall.png");

    private final Point2D position;

    public Wall(Point2D position){
        this.position = position;
    };

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(IMAGE, position.getX(), translateY(position.getY(), gc.getCanvas().getHeight()), 20, 20);
    }

    @Override
    public Rectangle2D getBoundingBox() {
        return null;
    }
}
