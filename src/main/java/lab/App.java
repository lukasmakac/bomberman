package lab;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *  Class <b>App</b> - extends class Application and it is an entry point of the program
 * @author     Java I
 */
public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	private Canvas canvas;
	private AnimationTimer animationTimer;
	private Laboratory lab;
	@Override
	public void start(Stage primaryStage) {
		try {
			//Construct a main window with a canvas.  
			Group root = new Group();
			canvas = new Canvas(800, 400);
			root.getChildren().add(canvas);
			Scene scene = new Scene(root, 800, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.resizableProperty().set(false);
			primaryStage.setTitle("Java 1 - 3rd laboratory");
			primaryStage.show();
			
			lab = new Laboratory(canvas);
			
			//Draw scene on a separate thread to avoid blocking UI.
			animationTimer = new AnimationTimer() {
				private Long previous;
				
				@Override
				public void handle(long now) {
					if (previous == null) {
						previous = now;
					} else {
						drawScene((now - previous)/1e9);
						previous = now;
					}
				}
			};
			animationTimer.start();
			//Exit program when main window is closed
			primaryStage.setOnCloseRequest(this::exitProgram);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Draws objects into the canvas. Put you code here. 
	 *
	 *@return      nothing
	 */
	private void drawScene(double deltaT) {
		lab.draw(deltaT);
	}
	
	private void exitProgram(WindowEvent evt) {
		animationTimer.stop();
		System.exit(0);
	}
}