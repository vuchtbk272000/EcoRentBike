package manageparkinglot;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import adminpage.AdminPageController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import makedeposite.UserDepositePageController;
import model.Bike;
import model.ParkingLot;
import serverapi.ParkingLotApi;
import utils.makedeposite.PopUpAlert;

public class  ManageParkingLotController {
	Stage primaryStage;
	@FXML
    private TableColumn<ParkingLot, String> colAddress;

	@FXML
    private TableColumn<ParkingLot, String> colName;

	@FXML
    private TableColumn<ParkingLot, Integer> colCapacity;


    @FXML
    private Button editBtn;


    @FXML
    private TableView<ParkingLot> tblParkingLot;


    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

	    
	ParkingLotApi parkingLotApi = new ParkingLotApi(); 
	
	
	public ManageParkingLotController(Stage stage) {
		
		super();
		this.primaryStage = stage;
//		this.parkingLot = parkingLotApi.getParkingLot();
//		this.parkingLot = parkingLot;
	}
//	
    @FXML
    private MenuItem viewStore;

    
    @FXML
	private void initialize() {

		colName.setCellValueFactory(new PropertyValueFactory<ParkingLot,String>("stationName"));
		colAddress.setCellValueFactory(new PropertyValueFactory<ParkingLot,String>("stationAddress"));
		colCapacity.setCellValueFactory(new PropertyValueFactory<ParkingLot,Integer>("maxSlot"));
		
		ArrayList<ParkingLot> parkingLot = new ArrayList<ParkingLot>();
		parkingLot = this.parkingLotApi.getParkingLot();		
		ObservableList<ParkingLot> parkingLotList = FXCollections.observableArrayList(parkingLot);
    	
		tblParkingLot.setItems(parkingLotList);
		
//		btnPlay.setVisible(false);
//		btnRemove.setVisible(false);
		
		tblParkingLot.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<ParkingLot>() {
					
					@Override
					public void changed(ObservableValue<? extends ParkingLot> observable, ParkingLot oldValue, ParkingLot newValue) {
						if (newValue!= null) {
//							updateButtonBar(newValue);
						}
					}
				});
		
		
	}

    @FXML
    void btnDeletePressed(ActionEvent event) throws IOException {
    	ParkingLot parkingLot= tblParkingLot.getSelectionModel().getSelectedItem();
    	System.out.println(parkingLot.getParkingLotId());
    	int res = parkingLotApi.deleteParkingLot(parkingLot.getParkingLotId());
    	if(res==1) {
    		PopUpAlert.showAlert("deleted successfully");	
    	}
    	if(res==2) {
    		PopUpAlert.showAlert("Cannot delete the parking lot because there are bikes in it");
    	}
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/manageparkinglot/ManageParking.fxml"));
    	ManageParkingLotController controller = new ManageParkingLotController(primaryStage);
        // Set it in the FXMLLoader
        loader.setController(controller);
        Parent root =  loader.load();

        Scene scene = new Scene(root);
      
          primaryStage.setTitle("FXML Welcome");
          primaryStage.setScene(scene);
          primaryStage.show();
    }
    
    @FXML
    void btnEditPressed(ActionEvent event) throws IOException {
    	ParkingLot parkingLot= tblParkingLot.getSelectionModel().getSelectedItem();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/manageparkinglot/ParkingLotInfo.fxml"));
    	ParkingLotInfoController controller = new ParkingLotInfoController(parkingLot, primaryStage);
        // Set it in the FXMLLoader
        loader.setController(controller);
        Parent root =  loader.load();

        Scene scene = new Scene(root);
      
          primaryStage.setTitle("FXML Welcome");
          primaryStage.setScene(scene);
          primaryStage.show();
    }

    @FXML
    void btnAddPressed(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/manageparkinglot/ParkingLotInfo.fxml"));
    	ParkingLotInfoController controller = new ParkingLotInfoController(primaryStage);
        // Set it in the FXMLLoader
        loader.setController(controller);
        Parent root =  loader.load();

        Scene scene = new Scene(root);
      
          primaryStage.setTitle("FXML Welcome");
          primaryStage.setScene(scene);
          primaryStage.show();
    }

    @FXML
    void btnBackPressed(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminpage/AdminPage.fxml"));
    	AdminPageController controller = new AdminPageController(primaryStage);
        // Set it in the FXMLLoader
        loader.setController(controller);
        Parent root =  loader.load();

        Scene scene = new Scene(root);
      
          primaryStage.setTitle("FXML Welcome");
          primaryStage.setScene(scene);
          primaryStage.show();
    }

    
}
