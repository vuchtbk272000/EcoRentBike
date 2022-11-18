package utils.makedeposite;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PopUpAlert {

	public PopUpAlert() {
		super();
	}

	// Show popup alert
	public static void showAlert(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Notification");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}
}
