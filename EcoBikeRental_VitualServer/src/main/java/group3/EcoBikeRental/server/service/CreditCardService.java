package group3.EcoBikeRental.server.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import group3.EcoBikeRental.server.model.CreditCard;
import group3.EcoBikeRental.server.repository.ICreditCardRepo;

@Service
public class CreditCardService implements ICreditCardService {
	@Autowired
	private ICreditCardRepo creditCardRepo;
	@Autowired
	private TransactionService transactionService;

	@Override
	public double getBalanceByCardNumber(String cardNumber) {
		CreditCard card = creditCardRepo.findCreditCardByCardNumber(cardNumber);
		return card.getBalance();
	}

	@Override
	public CreditCard getByCardNumber(String cardNumber) {
		// TODO Auto-generated method stub
		CreditCard card = creditCardRepo.findCreditCardByCardNumber(cardNumber);
		return card;
	}

	@Override
	public List<CreditCard> getAll() {
		// TODO Auto-generated method stub
		List<CreditCard> cards = creditCardRepo.findAll();
		return cards;
	}

	@Override
	public ResponseEntity<Object> decreaseBalance(CreditCard card, double amount) {

		String cardNumber = card.getCardNumber();
//		// validate card
		Optional<CreditCard> optional = Optional.ofNullable(creditCardRepo.findCreditCardByCardNumber(cardNumber));
		System.out.println(optional);

		if (optional.isPresent()) {
			CreditCard cardInDb = optional.get();
			// check valid security code
			boolean isWrongSecurityCode = !cardInDb.getSecurityCode().equals(card.getSecurityCode());
			if (isWrongSecurityCode) {
				return generateResponseObj("fail", "Wrong card security code!");
			}
			// check if credit card is being used
			boolean isCardBeingUsed = transactionService.isCardBeingUsed(card.getCardNumber());
			if (isCardBeingUsed) {
				return generateResponseObj("fail", "Card is being used for rent bike!");
			}
			// check if credit card balance is enough
			double currenBalance = cardInDb.getBalance();
			boolean isNotEnoughBalance = currenBalance < amount;
			if (isNotEnoughBalance) {
				return generateResponseObj("fail", "Your balance is insufficient to pay!");
			}

			cardInDb.setBalance(cardInDb.getBalance() - amount);
			creditCardRepo.save(cardInDb);

			return generateResponseObj("ok", "Make payment successfully!");

		} else {
			return generateResponseObj("fail", "Wrong credit card number!");

		}
	}

	public ResponseEntity<Object> generateResponseObj(String status, String message) {
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("status", status, "message", message));
	}
}
