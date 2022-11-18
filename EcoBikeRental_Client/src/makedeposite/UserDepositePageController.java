package makedeposite;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Bike;
import model.CreditCard;
import model.ResponseObj;
import model.Transaction;
import searchparkinglot.SearchParkingLotPageController;
import serverapi.BankApi;

import serverapi.IBillingSystem;
import serverapi.TransactionApi;
import utils.makedeposite.MakeDepositeForm;
import utils.makedeposite.PopUpAlert;
import utils.validator.IFormValidator;
import utils.validator.MakeDepositeFormValidator;

public class UserDepositePageController implements Initializable {
	private Bike bike;
	private IBillingSystem billingSystem;
	private TransactionApi transactionApi;
	private IFormValidator makeDepositeValidator;
	Stage primaryStage;

	public UserDepositePageController(Bike bike, Stage stage) {
		billingSystem = new BankApi();
		transactionApi = new TransactionApi();
		this.bike = bike;
		this.primaryStage = stage;
	}

	@FXML
	private Button payButton;

	@FXML
	private TextField issuingBank;

	@FXML
	private TextField cardName;

	@FXML
	private TextArea transDesc;

	@FXML
	private TextField securityCode;

	@FXML
	private TextField cardNumber;

	@FXML
	private DatePicker expirationDate;

	@FXML
	private Label lbALert;
	
	@FXML
    private Button backBtn;
    
    @FXML
    void onPressBackBtn(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/choosefunction/ChooseFunctionPage.fxml"));
        SearchParkingLotPageController controller = new SearchParkingLotPageController(primaryStage);
        // Set it in the FXMLLoader
        loader.setController(controller);
        Parent root =  loader.load();

        Scene scene = new Scene(root);
      
          primaryStage.setTitle("FXML Welcome");
          primaryStage.setScene(scene);
          primaryStage.show();
    }

	/**
	 * This method for handling submit make payment when user click submit button
	 * name session lead
	 * 
	 * @return void
	 */
	@FXML
	void onSubmit(ActionEvent event) {
		System.out.println(bike.getBikeId());
		try {

			lbALert.setText("");

			String cardNameInput = cardName.getText();
			String cardNumberValue = cardNumber.getText();
			String issuingBankValue = issuingBank.getText();
			String transDescValue = transDesc.getText();
			String expirationDateValue = expirationDate.getValue().toString();
			String securityCodeValue = securityCode.getText();

			MakeDepositeForm makeDepositeForm = new MakeDepositeForm(cardNameInput, cardNumberValue, issuingBankValue,
					transDescValue, expirationDateValue, securityCodeValue);
			makeDepositeValidator = new MakeDepositeFormValidator(makeDepositeForm);

			if (!makeDepositeValidator.isValid()) {
				lbALert.setText("You need to fill all the fields required!");
				PopUpAlert.showAlert("You need to fill all the fields required!");
				return;
			}

			CreditCard card = new CreditCard(cardNameInput, cardNumberValue, issuingBankValue, expirationDateValue,
					securityCodeValue);
			String startRentTimeString = LocalDateTime.now().toString();

			// Call bank api to process transaction
			ResponseObj responseObj = billingSystem.processTransaction(card, bike.getCost());

			lbALert.setText(responseObj.getMessage());
			PopUpAlert.showAlert(responseObj.getMessage());

			if (responseObj.getStatus().equals("ok")) {
				// save the transaction
				Transaction transaction = new Transaction(card, bike, bike.getCost(), startRentTimeString,
						transDescValue);
				transactionApi.saveTransaction(transaction);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			PopUpAlert.showAlert(e.getMessage());
		}

	}

	/**
	 * Set bike infomation to label
	 * 
	 * @return void
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lbALert.setText("Bike " + bike.getBikeId() + " Cost: " + bike.getCost());
		expirationDate.setValue(LocalDate.now());
	}

}
