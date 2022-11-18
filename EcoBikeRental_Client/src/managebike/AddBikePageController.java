package managebike;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Bike;
import model.CreditCard;
import model.ParkingLot;
import model.ResponseObj;
import model.Transaction;
import serverapi.BikeApi;
import serverapi.ParkingLotApi;
import utils.addbike.AddBikeForm;
import utils.makedeposite.MakeDepositeForm;
import utils.makedeposite.PopUpAlert;
import utils.validator.AddBikeFormValidator;
import utils.validator.MakeDepositeFormValidator;

public class AddBikePageController implements Initializable{
	Stage primaryStage;
	
    @FXML
    private Pane addBikePane;

    @FXML
    private TextField costTf;

    @FXML
    private TextField licensePlateTf;

    @FXML
    private DatePicker manufacturingDateTf;

    @FXML
    private TextField parkingLotIdTf;

    @FXML
    private ComboBox<String> typeCombox;

    @FXML
    private TextField weightTf;
    
    Bike bike;
    BikeApi bikeApi;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> list = FXCollections.observableArrayList("1","2","3");
		typeCombox.setItems(list);
	}
	
    @FXML
    void onClickCancelBtn(ActionEvent event) throws IOException {
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
    void onClickSaveBtn(ActionEvent event) {
    	try {
			String typeInput = typeCombox.getSelectionModel().getSelectedItem().toString();
			String weightInput = weightTf.getText();
			String costInput = costTf.getText();
			String licensePlateInput = licensePlateTf.getText();
			String parkingLotIdInput = parkingLotIdTf.getText();
			String ManufacturingDate = manufacturingDateTf.getValue().toString();

			AddBikeForm addBikeForm = new AddBikeForm(typeInput, weightInput, costInput, licensePlateInput, parkingLotIdInput, ManufacturingDate);
			AddBikeFormValidator validator = new AddBikeFormValidator(addBikeForm);

			if (!validator.isValid()) {
				System.out.println("Invalid input!");
				PopUpAlert.showAlert("Invalid Input!");
				return;
			}
			ParkingLotApi parkingLotApi = new ParkingLotApi();
			ParkingLot parkingLotFound = parkingLotApi.getParkingLotById(Long.valueOf(parkingLotIdInput));
			Bike bike = new Bike(Integer.valueOf(typeInput), Double.valueOf(weightInput), Double.valueOf(costInput),
					licensePlateInput, ManufacturingDate, parkingLotFound);
			
			bikeApi = new BikeApi();
			bikeApi.addBike(bike);
			PopUpAlert.showAlert("Add successfully!");
		} catch (Exception e) {
			PopUpAlert.showAlert(e.getMessage());
		}
    }

	public AddBikePageController(Stage primaryStage) {
		super();
		this.primaryStage = primaryStage;
	}
}
