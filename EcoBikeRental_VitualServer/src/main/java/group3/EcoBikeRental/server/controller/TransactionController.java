package group3.EcoBikeRental.server.controller;

import group3.EcoBikeRental.server.model.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import group3.EcoBikeRental.server.model.CreditCard;
import group3.EcoBikeRental.server.model.Transaction;
import group3.EcoBikeRental.server.service.ICreditCardService;
import group3.EcoBikeRental.server.service.ITransactionService;

@RestController
public class TransactionController {
	@Autowired
	private ITransactionService transactionService;
	@Autowired
	private ICreditCardService creditCardService;

	@PostMapping(value = "/transaction")
	public Transaction createTransaction(@RequestBody Transaction transaction) {		
		CreditCard card = creditCardService.getByCardNumber(transaction.getCard().getCardNumber());
		transaction.getCard().setCardId(card.getCardId());
		return transactionService.saveTransaction(transaction);
	}

	@GetMapping(value = "/transaction/get-open-transaction-by-id")
	// request gui len co dang : /transaction/get-open-transaction-by-id?bikeId=1
	public Transaction getOpenTransactionByBikeId(@RequestParam(name = "bikeId") long id){
		return transactionService.getOpenTransactionByBikeId(id);
	}

}
