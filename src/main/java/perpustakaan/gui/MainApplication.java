package perpustakaan.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main JavaFX Application class untuk Sistem Manajemen Perpustakaan Digital
 */
public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Load FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/perpustakaan/gui/main.fxml"));
            Parent root = loader.load();
            
            // Create scene
            Scene scene = new Scene(root, 800, 600);
            
            // Setup stage
            primaryStage.setTitle("Sistem Manajemen Perpustakaan Digital");
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(500);
            primaryStage.show();
            
        } catch (Exception e) {
            System.err.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Main method untuk menjalankan aplikasi JavaFX
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}