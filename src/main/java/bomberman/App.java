package bomberman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Class <b>App</b> - extends class Application and it is an entry point of the program
 */
public class App extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  private GameController controller;
  private SimulationService simulationService;

  @Override
  public void start(Stage primaryStage) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
      BorderPane pane = loader.load();

      controller = loader.getController();

      Scene scene = new Scene(pane);
      scene.setOnKeyPressed(controller);

      primaryStage.setScene(scene);
      primaryStage.resizableProperty().set(false);
      primaryStage.setTitle("BOMBERMAN");
      primaryStage.show();

      controller.startGame();

      //Exit program when main window is closed
      primaryStage.setOnCloseRequest(this::exitProgram);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void exitProgram(WindowEvent evt) {
    System.exit(0);
  }
}