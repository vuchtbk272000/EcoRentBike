package adminpage;

import java.io.IOException;

import home.HomePageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import managebike.ManageBikePageController;
import manageparkinglot.ManageParkingLotController;
import searchparkinglot.SearchParkingLotPageController;

public class AdminPageController {
    @FXML
    private Pane adminPagePane;

    @FXML
    private Button manageBikeBtn;
    
    @FXML
    private Button backBtn;

    @FXML
    private Button manageParkingLotBtn;
    
	Stage primaryStage;
    
    @FXML
    void onClickBackBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home/HomePage.fxml"));
        HomePageController controller = new HomePageController(primaryStage);
        // Set it in the FXMLLoader
        loader.setController(controller);
        Parent root =  loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("Eco Bike Rental System: Logging in with Admin role");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void onClickManageBikeBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/managebike/ManageBikePage.fxml"));
        ManageBikePageController controller = new ManageBikePageController(primaryStage);
        // Set it in the FXMLLoader
        loader.setController(controller);
        Parent root =  loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("Eco Bike Rental System: Logging in with Admin role");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void onClickManageParkingLotBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/manageparkinglot/ManageParking.fxml"));
        ManageParkingLotController controller = new ManageParkingLotController(primaryStage);
        // Set it in the FXMLLoader
        loader.setController(controller);
        Parent root =  loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("Eco Bike Rental System: Logging in with Admin role");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	public AdminPageController(Stage primaryStage) {
		super();
		this.primaryStage = primaryStage;
	}
}

