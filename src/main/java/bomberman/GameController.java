package bomberman;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class GameController {

    @FXML
    private Canvas canvas;

    private AnimationTimer animationTimer;

    public GameController() {	}

    public void startGame() {
        World world = new World(canvas);
        //Draw scene on a separate thread to avoid blocking UI.
        animationTimer = new DrawingThread(world);
        animationTimer.start();
    }

    public void stopGame() {
        animationTimer.stop();
    }

}
