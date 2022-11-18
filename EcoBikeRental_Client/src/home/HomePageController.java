package home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import searchparkinglot.SearchParkingLotPageController;

import java.io.IOException;

import adminpage.AdminPageController;

public class HomePageController {
	Stage primaryStage;

    @FXML
    private Button isAdminBtn;

    @FXML
    private Button isUserBtn;

    @FXML
    void onPressAdminBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminpage/AdminPage.fxml"));
        AdminPageController controller = new AdminPageController(primaryStage);
        // Set it in the FXMLLoader
        loader.setController(controller);
        Parent root =  loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("Eco Bike Rental System: Logging in with User role");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void onPressUserBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/searchparkinglot/SearchParkingLotPage.fxml"));
        SearchParkingLotPageController controller = new SearchParkingLotPageController(primaryStage);
        // Set it in the FXMLLoader
        loader.setController(controller);
        Parent root =  loader.load();

        Scene scene = new Scene(root);

        primaryStage.setTitle("Eco Bike Rental System: Logging in with User role");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public HomePageController(Stage stage) {
    	super();
    	this.primaryStage = stage;
    }

}
