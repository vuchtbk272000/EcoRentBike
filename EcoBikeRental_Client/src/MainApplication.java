
import java.io.IOException;

import home.HomePageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {

		FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("/home/HomePage.fxml"));
		HomePageController controller = new HomePageController(primaryStage);
		loader.setController(controller);
		Parent root = loader.load();

		Scene scene = new Scene(root);

		primaryStage.setTitle("Eco Bike Rental System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}