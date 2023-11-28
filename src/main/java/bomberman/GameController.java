package bomberman;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public class GameController {

    @FXML
    private Canvas canvas;
    private World world;
    private AnimationTimer animationTimer;

    public GameController() {	}

    public void startGame() {
        this.world = new World(canvas.getWidth(), canvas.getHeight());

        //Draw scene on a separate thread to avoid blocking UI.
        animationTimer = new DrawingThread(canvas, world);
        animationTimer.start();
    }

    public void stopGame() {
        animationTimer.stop();
    }



}
