package manageparkinglot;
import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ParkingLot;
import serverapi.ParkingLotApi;
import utils.makedeposite.PopUpAlert;

public class ParkingLotInfoController {
	private long id;
	private Stage primaryStage;
	private ParkingLot parkingLot;
	private ParkingLotApi parkingLotApi = new ParkingLotApi();

	public ParkingLotInfoController(ParkingLot parkingLot, Stage stage) {
		super();
		this.parkingLot = parkingLot;
		this.primaryStage = stage;
	}
	public ParkingLotInfoController(Stage stage) {
		super();
		this.primaryStage = stage;
	}

	@FXML
    private Button submitButton;

    @FXML
    private Button btnBack;

    @FXML
    private TextField tfCapa;

    @FXML
    private TextField tfName;

    @FXML
    private Label lbALert;

    @FXML
    private TextField tfAddress;
    

    @FXML
    void btnBackPressed(ActionEvent event) throws IOException {
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
    void onSubmit(ActionEvent event) throws IOException {
    	if((tfName.getText()==null)||tfName.getText().trim().isEmpty()||(tfAddress.getText()==null)||(tfCapa.getText()==null)||tfAddress.getText().trim().isEmpty()||tfCapa.getText().trim().isEmpty()) {
        	PopUpAlert.showAlert("please fill all the fields");
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/manageparkinglot/ParkingLotInfo.fxml"));
    		if(parkingLot== null) {
    			ParkingLotInfoController controller = new ParkingLotInfoController(primaryStage);
    			loader.setController(controller);
                Parent root =  loader.load();

                Scene scene = new Scene(root);
              
    	        primaryStage.setTitle("FXML Welcome");
    	        primaryStage.setScene(scene);
    	        primaryStage.show();
    	    }
    		else {
    			ParkingLotInfoController controller = new ParkingLotInfoController(parkingLot, primaryStage);
    			loader.setController(controller);
                Parent root =  loader.load();

                Scene scene = new Scene(root);
              
    	        primaryStage.setTitle("FXML Welcome");
    	        primaryStage.setScene(scene);
    	        primaryStage.show();
    		}   			
            // Set it in the FXMLLoader
            
    	}
    	else {
    		String name = tfName.getText();
        	String address = tfAddress.getText();
        	int capa = Integer.parseInt(tfCapa.getText());
        	if(parkingLot != null) {
        		System.out.print("not null");
        		parkingLot.editInfo(name, address, capa);
            	int res = parkingLotApi.editParkingLot(parkingLot);
            	if(res == 1)
            		PopUpAlert.showAlert("Editted Successfully");
            	else if (res==2)
            		PopUpAlert.showAlert("Not enough slots for current bikes number");
        	}
        	else {
        		System.out.print("null");
        		ParkingLot newParkingLot = new ParkingLot();
        		newParkingLot.editInfo(name, address, capa);
        		int res = parkingLotApi.addParkingLot(newParkingLot);
            	if(res == 1)
            		PopUpAlert.showAlert("Added Successfully");
            	else if (res==2)
            		PopUpAlert.showAlert("Not enough slots for current bikes number");
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
    	
    }

    @FXML
	private void initialize() {
    	if(parkingLot != null) {
	    	tfName.setText(parkingLot.getStationName());
	    	tfAddress.setText(parkingLot.getStationAddress());
	    	tfCapa.setText(String.valueOf(parkingLot.getMaxSlot()));
    	}
//		
//		ArrayList<ParkingLot> parkingLot = new ArrayList<ParkingLot>();
//		parkingLot = this.parkingLotApi.getParkingLot();		
//		ObservableList<ParkingLot> parkingLotList = FXCollections.observableArrayList(parkingLot);
//    	
//		tblParkingLot.setItems(parkingLotList);
//		
////		btnPlay.setVisible(false);
////		btnRemove.setVisible(false);
//		
//		tblParkingLot.getSelectionModel().selectedItemProperty().addListener(
//				new ChangeListener<ParkingLot>() {
//					
//					@Override
//					public void changed(ObservableValue<? extends ParkingLot> observable, ParkingLot oldValue, ParkingLot newValue) {
//						if (newValue!= null) {
////							updateButtonBar(newValue);
//						}
//					}
//				});
//		
		
	}

}
