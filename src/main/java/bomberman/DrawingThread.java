package bomberman;

import static bomberman.constant.StaticLayout.BRICKS;
import static bomberman.constant.StaticLayout.WALLS;

import bomberman.handler.PlayerMovementHandler;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class DrawingThread extends AnimationTimer {

  private final GameController controller;

  private final World world;

  private final EventHandler<KeyEvent> keyEventHandler;

  public DrawingThread(GameController controller, World world) {
    this.controller = controller;
    this.world = world;

    this.keyEventHandler = new PlayerMovementHandler(world);
  }

  @Override
  public void handle(long now) {
    printState();
    Platform.runLater(this::draw);
  }

  public void draw() {
    gc().clearRect(0, 0, controller.getCanvas().getWidth(), controller.getCanvas().getHeight());
    gc().setFill(Color.GREEN);
    gc().fillRect(0, 0, controller.getCanvas().getWidth(), controller.getCanvas().getHeight());

    WALLS.forEach(w -> w.draw(gc()));
    BRICKS.forEach(b -> b.draw(gc()));

    world.getEnemies().forEach(e -> e.draw(gc()));
    world.getBombs().forEach(b -> b.draw(gc()));
    world.getExplosions().forEach(e -> e.draw(gc()));

    world.getPlayer().draw(gc());
  }

  public EventHandler<KeyEvent> getKeyEventHandler() {
    return keyEventHandler;
  }

  private void printState() {
    controller.setPositionText(
        "[" + world.getPlayer().getPosition().getX() + "," + world.getPlayer().getPosition().getY() + "]");

    controller.setScoreLabelValue(world.getPlayer().getScore());
  }

  private GraphicsContext gc() {
    return controller.getCanvas().getGraphicsContext2D();
  }
}
