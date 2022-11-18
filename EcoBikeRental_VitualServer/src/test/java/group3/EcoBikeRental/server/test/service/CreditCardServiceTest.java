package group3.EcoBikeRental.server.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.classmate.GenericType;

import group3.EcoBikeRental.server.model.CreditCard;
import group3.EcoBikeRental.server.repository.ICreditCardRepo;
import group3.EcoBikeRental.server.service.ICreditCardService;
import group3.EcoBikeRental.server.service.TransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardServiceTest {
	@Autowired
	private ICreditCardService creditCardService;
	@MockBean
	private TransactionService transactionService;
	@MockBean
	private ICreditCardRepo creditCardRepo;

	/***
	 * Decrease balance successfully when credit card is valid and enough balance
	 */
	@Test
	public void testDecreaseBalanceSuccessfully() {
		CreditCard mockCreditCard = new CreditCard();
		mockCreditCard.setCardNumber("1001192");
		mockCreditCard.setCardId(33);
		mockCreditCard.setSecurityCode("111");
		mockCreditCard.setBalance(500.0);
		Mockito.when(creditCardRepo.findCreditCardByCardNumber(mockCreditCard.getCardNumber()))
				.thenReturn(mockCreditCard);
		Mockito.when(transactionService.isCardBeingUsed(mockCreditCard.getCardNumber())).thenReturn(false);
		creditCardService.decreaseBalance(mockCreditCard, 100.0);
		assertEquals(mockCreditCard.getBalance(), 400);
	}
	
	/***
	 * Decrease balance fail when credit card is not enough balance
	 */
	@Test
	public void testDecreaseNotEnoughBalance() {
		CreditCard mockCreditCard = new CreditCard();
		mockCreditCard.setCardNumber("1001192");
		mockCreditCard.setCardId(33);
		mockCreditCard.setSecurityCode("111");
		mockCreditCard.setBalance(100.0);
		Mockito.when(creditCardRepo.findCreditCardByCardNumber(mockCreditCard.getCardNumber()))
				.thenReturn(mockCreditCard);
		Mockito.when(transactionService.isCardBeingUsed(mockCreditCard.getCardNumber())).thenReturn(false);
		creditCardService.decreaseBalance(mockCreditCard, 200.0);
		
		assertEquals(mockCreditCard.getBalance(), 100);
		
	}

}