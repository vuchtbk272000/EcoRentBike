package group3.EcoBikeRental.server.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import group3.EcoBikeRental.server.model.CreditCard;

public interface ICreditCardService {

	double getBalanceByCardNumber(String cardNumber);

	List<CreditCard>  getAll();

	CreditCard getByCardNumber(String cardNumber);

	ResponseEntity<Object> decreaseBalance(CreditCard card, double amount);
}
