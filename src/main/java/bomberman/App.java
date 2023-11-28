package bomberman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
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
	
	private GameController controller;

	@Override
	public void start(Stage primaryStage) {
		try {
				FXMLLoader loader = new FXMLLoader(
				getClass().getResource("game.fxml")
			);
			BorderPane pane = loader.load();

			Scene scene = new Scene(pane);	// AJ TOTO SME PRIDALI

			primaryStage.setScene(scene);
			primaryStage.resizableProperty().set(false);
			primaryStage.setTitle("BOMBERMAN");
			primaryStage.show();
			controller = loader.getController(); // NAČÍTA CONTROLLER Z TOHO PROGRAMU KDE SME SI HO VYBRALI
			controller.startGame();

			//Exit program when main window is closed
			primaryStage.setOnCloseRequest(this::exitProgram);

			//Draw scene on a separate thread to avoid blocking UI.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void exitProgram(WindowEvent evt) {
		System.exit(0);
	}
}