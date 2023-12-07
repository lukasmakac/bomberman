package bomberman;

import bomberman.handler.PlayerEventHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class GameController implements EventHandler<KeyEvent> {

    @FXML
    private Canvas canvas;
    private DrawingThread drawingThread;
    private PlayerEventHandler playerEventHandler;

    public GameController() {
    }

    public void startGame() {
        World world = new World(canvas);
        playerEventHandler = new PlayerEventHandler(world.getPlayer());

        // Draw scene on a separate thread to avoid blocking UI.
        drawingThread = new DrawingThread(world, this::stopGame);
        drawingThread.start();
    }

    public void stopGame() {
        drawingThread.stop();
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        playerEventHandler.handle(keyEvent);
    }
}
