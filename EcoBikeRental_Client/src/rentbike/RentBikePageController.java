package rentbike;

import java.io.IOException;

import java.util.Objects;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import makedeposite.UserDepositePageController;
import model.Bike;
import searchparkinglot.SearchParkingLotPageController;
import model.ParkingLot;
import serverapi.BikeApi;
import serverapi.ParkingLotApi;
import serverapi.TransactionApi;
import utils.validator.IFormValidator;

public class RentBikePageController implements IFormValidator{
	BikeApi bikeApi = new BikeApi();
	TransactionApi transactionApi = new TransactionApi();
	Stage primaryStage;

    @FXML
    private TextField bikeId;
    
    @FXML
    private Label lbAlert;

    @FXML
    private Button rentBtn;
    
    @FXML
    private Button backBtn;
    
    @FXML
    void onPressBackBtn(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/searchparkinglot/SearchParkingLotPage.fxml"));
        SearchParkingLotPageController controller = new SearchParkingLotPageController(primaryStage);
        // Set it in the FXMLLoader
        loader.setController(controller);
        Parent root =  loader.load();

        Scene scene = new Scene(root);
      
          primaryStage.setTitle("FXML Welcome");
          primaryStage.setScene(scene);
          primaryStage.show();
    }
    
    /** onPressRentBtn() function
     * Handle event when press Rent Bike Button
     * 
     * @param event
     */
    @FXML
    void onPressRentBtn(ActionEvent event) throws IOException {
        
    	lbAlert.setText("");
    	lbAlert.setVisible(true);
    	
    	if (isValid()) {
    		Long bikeId = Long.valueOf(this.bikeId.getText());
    		Bike bike = bikeApi.getBikeByID(bikeId);
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/makedeposite/UserDepositePage.fxml"));
            UserDepositePageController controller = new UserDepositePageController(bike, primaryStage);
            // Set it in the FXMLLoader
            loader.setController(controller);
            Parent root =  loader.load();

            Scene scene = new Scene(root);
          
              primaryStage.setTitle("FXML Welcome");
              primaryStage.setScene(scene);
              primaryStage.show();
    	}	
    }

	public boolean isValid() {
		// TODO Auto-generated method stub
		if (this.bikeId.getText().isEmpty()) {
			lbAlert.setText("Field is required!");
			return false;
		}
		for (char c : this.bikeId.getText().toCharArray()) {
			if(!Character.isDigit(c)) {
				lbAlert.setText("Invalid input. BikeId is only contain digit!");
				return false;
			}
		}
    	Long bikeId = Long.valueOf(this.bikeId.getText());
    	if (Objects.isNull(bikeApi.getBikeByID(bikeId))) {
    		lbAlert.setText("Invalid input. BikeId is not exist!");
    		return false;
    	}
    	if (!Objects.isNull(transactionApi.getOpenTransactionRecordByBike(bikeId))) {
			lbAlert.setText("Invalid input. Bike is already rent!");
    		return false;
    	}
    	return true;
	}
	
	public RentBikePageController(Stage primaryStage) {
		super();
		this.primaryStage = primaryStage;
	}
}
