package lab;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class GameController {

    private World world;
    @FXML	// INCLUDLI SME ABY SA ZAHRNULA HODNOTA PLATNA Z TOHO FXML, len tam, kde je canvas
    private Canvas canvas;
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
