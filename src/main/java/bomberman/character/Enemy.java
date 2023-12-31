package bomberman.character;


import static bomberman.common.Utils.getImage;

import bomberman.common.Simulable;
import bomberman.constant.MovementType;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class Enemy extends Character implements Simulable {

    protected Integer speed;
    protected MovementType movementType;

    public Enemy(Point2D position, Integer speed, String imagePath, MovementType movementType) {
        super(getImage(imagePath), position);
        this.speed = speed;
        this.movementType = movementType;
    }

    @Override
    public void simulate(GraphicsContext gc, double deltaT) {
        switch (movementType) {
            case VERTICAL -> position = computeVertical(gc, position, deltaT);
            case HORIZONTAL -> position = computeHorizontal(gc, position, deltaT);
        };
    }

    public void hit(){
        speed *= -1;
    }

    private Point2D computeVertical(GraphicsContext gc, Point2D position, double step) {
        final double y = compute(position.getY(), step, gc.getCanvas().getHeight());
        return new Point2D(position.getX(), y);
    }

    private Point2D computeHorizontal(GraphicsContext gc, Point2D position, double step) {
        final double x = compute(position.getX(), step, gc.getCanvas().getWidth());
        return new Point2D(x, position.getY());
    }

    private Double compute(Double value, Double step, Double max) {
        return (value + (speed * step) * max) % max;
    }

}
