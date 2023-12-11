package bomberman;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameController implements EventHandler<KeyEvent> {

    @FXML
    private Canvas canvas;
    private DrawingThread drawingThread;
    private SimulationService simulationService;

    private World world;

    @FXML
    private Text playerName;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label position;

    public GameController() {
    }

    public void startGame() {
        this.world = new World();

        // Draw scene on a separate thread to avoid blocking UI.
        drawingThread = new DrawingThread(this, world);
        simulationService = new SimulationService(this, world, .025);

        simulationService.setPeriod(Duration.millis(10));

        drawingThread.start();
        simulationService.start();
    }

    public void stopGame() {
        drawingThread.stop();
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        drawingThread.getKeyEventHandler().handle(keyEvent);
    }

    public void setScoreLabelValue(Integer points) {
        this.scoreLabel.setText(points.toString());
    }

    public void setPositionText(String text) {
        this.position.setText(text);
    }

    public Canvas getCanvas() {
        return canvas;
    }

}
