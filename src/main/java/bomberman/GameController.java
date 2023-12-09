package bomberman;

import bomberman.handler.PlayerEventHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class GameController implements EventHandler<KeyEvent> {

    @FXML
    private Canvas canvas;
    private DrawingThread drawingThread;
    private PlayerEventHandler playerEventHandler;

    @FXML
    private TextField playerInsert;

    @FXML
    private Text playerTitle;

    @FXML
    private Label score;
    private int currentScore = 0;


    public GameController() {
    }

    @FXML
    public void addScore() {
        currentScore += 25;
        this.score.setText("" + currentScore);
    }

    public void startGame() {
        World world = new World(canvas);
        playerEventHandler = new PlayerEventHandler(world.getPlayer());

        // pridala som, asi to nie je potrebné
        //canvas.setOnKeyPressed(playerEventHandler);

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

    @FXML
    void insertPlayerName(MouseEvent event) {
        playerTitle.setText(playerInsert.getText());
    }

}
