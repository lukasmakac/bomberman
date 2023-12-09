package bomberman;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class GameController implements EventHandler<KeyEvent> {

    @FXML
    private Canvas canvas;
    private DrawingThread drawingThread;
    private World world;

    @FXML
    private Text playerName;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label position;

    private int score;

    public GameController() {
    }

    public void startGame() {
        world = new World(canvas);

        // Draw scene on a separate thread to avoid blocking UI.
        drawingThread = new DrawingThread(world, this);
        drawingThread.start();
    }

    public void stopGame() {
        drawingThread.stop();
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP -> world.getPlayer().moveUp();
            case DOWN -> world.getPlayer().moveDown();
            case LEFT -> world.getPlayer().moveLeft();
            case RIGHT -> world.getPlayer().moveRight();
            case SPACE -> world.getPlayer().dropBomb();
        }
    }

    public void addScore(Integer points) {
        score += points;
        scoreLabel.setText(String.valueOf(score));
    }

    public void setPositionText(String text) {
        this.position.setText(text);
    }
}
