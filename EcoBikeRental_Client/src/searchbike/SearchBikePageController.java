package searchbike;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchBikePageController {
	Stage primaryStage;

    @FXML
    private Button backBtn;

    @FXML
    private TextField bikeId;

    @FXML
    private Button rentBtn;

    @FXML
    void onPressBackBtn(ActionEvent event) {

    }

    @FXML
    void onPressRentBtn(ActionEvent event) {

    }
    
    public SearchBikePageController(Stage stage) {
    	super();
    	this.primaryStage = stage;
    }

}
