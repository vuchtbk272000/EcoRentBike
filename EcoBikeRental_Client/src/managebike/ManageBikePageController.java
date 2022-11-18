package managebike;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

import adminpage.AdminPageController;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;
import javafx.util.converter.NumberStringConverter;
import model.Bike;
import model.ParkingLot;
import model.eBike;
import serverapi.BikeApi;
import serverapi.ParkingLotApi;
import utils.makedeposite.PopUpAlert;

public class ManageBikePageController implements Initializable {
	@FXML
	private Pane manageBikePagePane;

	@FXML
	private Button addBtn;

	@FXML
	private Button deleteBtn;

	@FXML
	private Button updateBtn;

    @FXML
    private Button backBtn;

	@FXML
	private TableView<Bike> bikeTable;

	@FXML
	private TableColumn<Bike, Long> idCol; // long

	@FXML
	private TableColumn<Bike, Integer> typeCol;

	@FXML
	private TableColumn<Bike, Double> weightCol;

	@FXML
	private TableColumn<Bike, Double> costCol;

	@FXML
	private TableColumn<Bike, String> licensePlateCol;

	@FXML
	private TableColumn<Bike, String> manufacturingDateCol;

	@FXML
	private TableColumn<Bike, String> producerCol;

	@FXML
	private TableColumn<Bike, Number> parkingLotIdCol; // long

	BikeApi bikeApi = new BikeApi();
	ParkingLotApi parkingLotApi = new ParkingLotApi();
	Stage primaryStage;
	
    public ManageBikePageController(Stage stage) {
    	super();
    	this.primaryStage = stage;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idCol.setCellValueFactory(new PropertyValueFactory<>("bikeId"));
		typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
		weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
		costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
		licensePlateCol.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
		manufacturingDateCol.setCellValueFactory(new PropertyValueFactory<>("manufacturingDate"));
		producerCol.setCellValueFactory(new PropertyValueFactory<>("producer"));
		parkingLotIdCol
				.setCellValueFactory(c -> new SimpleLongProperty(c.getValue().getParkingLot().getParkingLotId()));
		ObservableList<Bike> listBikes = FXCollections.observableArrayList(bikeApi.getAllBikes());
		bikeTable.setItems(listBikes);
	}
	
	@FXML
	void onClickBackBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminpage/AdminPage.fxml"));
        AdminPageController controller = new AdminPageController(primaryStage);
        // Set it in the FXMLLoader
        loader.setController(controller);
        Parent root =  loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("Eco Bike Rental System: Logging in with Admin role");
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	@FXML
	void onClickAddBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/managebike/AddBikePage.fxml"));
        AddBikePageController controller = new AddBikePageController(primaryStage);
        // Set it in the FXMLLoader
        loader.setController(controller);
        Parent root =  loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("Eco Bike Rental System: Logging in with Admin role");
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	@FXML
	void onClickDeleteBtn(ActionEvent event) {
		bikeApi.deleteBike(bikeTable.getSelectionModel().getSelectedItems().get(0));
		bikeTable.getItems().removeAll(bikeTable.getSelectionModel().getSelectedItems());
	}

	@FXML
	void onClickUpdateBtn(ActionEvent event) {
		bikeTable.setEditable(true);
		weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
		weightCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

		weightCol.setOnEditCommit(new EventHandler<CellEditEvent<Bike, Double>>() {
			@Override
			public void handle(CellEditEvent<Bike, Double> event) {
				Bike bike = event.getRowValue();
				bike.setWeight(event.getNewValue());
				event.getTableView().getItems().set(event.getTablePosition().getRow(), bike);
				bikeApi.updateBike(bike);
			}
		});

		costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
		costCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		costCol.setOnEditCommit(new EventHandler<CellEditEvent<Bike, Double>>() {
			@Override
			public void handle(CellEditEvent<Bike, Double> event) {
				Bike bike = event.getRowValue();
				bike.setCost(event.getNewValue());
				event.getTableView().getItems().set(event.getTablePosition().getRow(), bike);
				bikeApi.updateBike(bike);
			}
		});

		licensePlateCol.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
		licensePlateCol.setCellFactory(TextFieldTableCell.forTableColumn());
		licensePlateCol.setOnEditCommit(new EventHandler<CellEditEvent<Bike, String>>() {
			@Override
			public void handle(CellEditEvent<Bike, String> event) {
				Bike bike = event.getRowValue();
				bike.setLicensePlate(event.getNewValue());
				event.getTableView().getItems().set(event.getTablePosition().getRow(), bike);
				bikeApi.updateBike(bike);
			}
		});

		manufacturingDateCol.setCellValueFactory(new PropertyValueFactory<>("manufacturingDate"));
		manufacturingDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
		manufacturingDateCol.setOnEditCommit(new EventHandler<CellEditEvent<Bike, String>>() {
			@Override
			public void handle(CellEditEvent<Bike, String> event) {
				Bike bike = event.getRowValue();
				bike.setManufacturingDate(event.getNewValue());
				event.getTableView().getItems().set(event.getTablePosition().getRow(), bike);
				bikeApi.updateBike(bike);
			}
		});

		producerCol.setCellValueFactory(new PropertyValueFactory<>("producer"));
		producerCol.setCellFactory(TextFieldTableCell.forTableColumn());
		producerCol.setOnEditCommit(new EventHandler<CellEditEvent<Bike, String>>() {
			@Override
			public void handle(CellEditEvent<Bike, String> event) {
				Bike bike = event.getRowValue();
				bike.setProducer(event.getNewValue());
				event.getTableView().getItems().set(event.getTablePosition().getRow(), bike);
				bikeApi.updateBike(bike);
			}
		});

		parkingLotIdCol
				.setCellValueFactory(c -> new SimpleLongProperty(c.getValue().getParkingLot().getParkingLotId()));
		parkingLotIdCol.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		parkingLotIdCol.setOnEditCommit(new EventHandler<CellEditEvent<Bike, Number>>() {
			@Override
			public void handle(CellEditEvent<Bike, Number> event) {
				Bike bike = event.getRowValue();
				ParkingLot newParkingLot = new ParkingLot();
				try {
					long newParkingLotId = (Long) event.getNewValue();
					newParkingLot = parkingLotApi.getParkingLotById(newParkingLotId);
					if (newParkingLot != null) {
						bike.setParkingLot(newParkingLot);
					} else {
						PopUpAlert.showAlert("Parking Lot not found!");
					}
				} catch (InputMismatchException e) {
					PopUpAlert.showAlert("Invalid input data!");
				}
				event.getTableView().getItems().set(event.getTablePosition().getRow(), bike);
				bikeApi.updateBike(bike);
			}
		});
	}
}
