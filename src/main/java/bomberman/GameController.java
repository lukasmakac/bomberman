package bomberman;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class GameController {

    @FXML
    private Canvas canvas;
    private World world;
    private AnimationTimer animationTimer;

    public GameController() {	}

    public void startGame() {
        this.world = new World(canvas.getWidth(), canvas.getHeight());

        canvas.addEventHandler(KeyEvent.KEY_PRESSED, new PlayerEventHandler(world.getPlayer()));

        //Draw scene on a separate thread to avoid blocking UI.
        animationTimer = new DrawingThread(canvas, world);
        animationTimer.start();
    }

    public void stopGame() {
        animationTimer.stop();
    }



}
