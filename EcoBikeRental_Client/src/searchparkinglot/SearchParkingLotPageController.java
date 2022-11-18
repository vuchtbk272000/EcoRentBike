package searchparkinglot;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ParkingLot;
import rentbike.RentBikePageController;
import serverapi.ParkingLotApi;

import java.io.IOException;
import java.util.ArrayList;

import home.HomePageController;

public class SearchParkingLotPageController {
	ParkingLotApi parkingLotApi = new ParkingLotApi();
	Stage primaryStage;
	ArrayList<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

	@FXML
    private Button backBtn;

    @FXML
    private Button rentBikeBtn;
    
    @FXML
    private Button goToParkingLotBtn;
    
    @FXML
    private TextField tfFilter;

    @FXML
    private RadioButton filterByNameRadioBtn;

    @FXML
    private TableView<ParkingLot> tblParkingLot;

    @FXML
    private TableColumn<ParkingLot, String> colStationName;

    @FXML
    private TableColumn<ParkingLot, Integer> colNumOfEmptySlot;

    @FXML
    private TableColumn<ParkingLot, String> colStationAddress;

    @FXML
    void onPressBackBtn(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/home/HomePage.fxml"));
        HomePageController controller = new HomePageController(primaryStage);
        // Set it in the FXMLLoader
        loader.setController(controller);
        Parent root =  loader.load();

        Scene scene = new Scene(root);

        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void onPressRentBikeBtn(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/rentbike/RentBikePage.fxml"));
        RentBikePageController controller = new RentBikePageController(primaryStage);
        // Set it in the FXMLLoader
        loader.setController(controller);
        Parent root =  loader.load();

        Scene scene = new Scene(root);

        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    @FXML
    public void initialize() {
    	colStationName.setCellValueFactory(new PropertyValueFactory<ParkingLot, String>("stationName"));
    	colNumOfEmptySlot.setCellValueFactory(new PropertyValueFactory<ParkingLot, Integer>("numberOfEmptySlot"));
    	colStationAddress.setCellValueFactory(new PropertyValueFactory<ParkingLot, String>("stationAddress"));
    	
    	parkingLots = new ArrayList<ParkingLot>();
    	parkingLots = parkingLotApi.getParkingLot();
    	ObservableList<ParkingLot> parkingLotList = FXCollections.observableArrayList(parkingLots);
    	tblParkingLot.setItems(parkingLotList);
    	
    	goToParkingLotBtn.setVisible(false);
    	
    	tblParkingLot.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ParkingLot>() {

			@Override
			public void changed(ObservableValue<? extends ParkingLot> arg0, ParkingLot oldParkingLot, ParkingLot newParkingLot) {
				// TODO Auto-generated method stub
				if (newParkingLot != null) {
					goToParkingLotBtn.setVisible(true);
				}
			}
    		
    	});
    	
    	tfFilter.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				showFilteredParkingLot(newValue);
			}
    		
    	});
    	
    }
    /**
     * Function show the filtered table of list parking lot when user filter by station name/station address 
     * @param value
     */
    void showFilteredParkingLot(String value) {
    	ObservableList<ParkingLot> list = FXCollections.observableArrayList();
		
		if (filterByNameRadioBtn.isSelected()){
			for (ParkingLot item : parkingLots) {
				if (item.getStationName().toLowerCase().contains(value.toLowerCase())) {
					list.add(item);
				}
			}
			tblParkingLot.setItems(list);
		}
		else {
			for (ParkingLot item : parkingLots) {
				if (item.getStationAddress().toLowerCase().contains(value.toLowerCase())) {
					list.add(item);
				}
			}
			tblParkingLot.setItems(list);
		}
    }
    
    @FXML
    void onPressGoToParkingLotBtn(ActionEvent event) {

    }

    public SearchParkingLotPageController(Stage primaryStage) {
        super();
        this.primaryStage = primaryStage;
    }
}
