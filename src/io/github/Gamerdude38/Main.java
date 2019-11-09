package io.github.Gamerdude38;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A program integrating skills learned in COP 3003 (JavaFX and H2). Serves as a driver class.
 *
 * @author John Maurer
 */
public class Main extends Application {

  /**
   * Initialization method of the JavaFX portion of the program.
   *
   * @param primaryStage a <code>Stage</code> object required to render the GUI.
   * @throws Exception a general <code>Exception</code> thrown by all JavaFX <code>start</code>
   *     methods.
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("formFunctionality.fxml"));
    Scene scene = new Scene(root);
    scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
    primaryStage.setTitle("Production Line");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Initialization of the Java program.
   *
   * @param args a <code>String</code> array accepting input from the console.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
