/*
 * Made by Andrea Mate.
 * For practice.
 * This is the way!
 */
package teendok;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Máté Andrea
 */
public class Teendok extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        FXMLDocumentController controller=loader.getController();

        stage.setOnCloseRequest((e)->{
            e.consume();
            controller.kilep();
        });
        
        Scene scene = new Scene(root);
        scene.setOnKeyPressed((KeyEvent event) -> {
            controller.bill(event);
        });
        stage.setTitle("Teendők");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
