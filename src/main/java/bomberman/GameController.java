package bomberman;

import bomberman.handler.PlayerEventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class GameController {

    @FXML
    private Canvas canvas;
    private DrawingThread drawingThread;

    public GameController() {
    }

    public void startGame() {
        World world = new World(canvas);
        canvas.addEventHandler(KeyEvent.KEY_TYPED, new PlayerEventHandler(world.getPlayer()));

        // Draw scene on a separate thread to avoid blocking UI.
        drawingThread = new DrawingThread(world, this::stopGame);
        drawingThread.start();

    }

    public void stopGame() {
        drawingThread.stop();
    }

}
