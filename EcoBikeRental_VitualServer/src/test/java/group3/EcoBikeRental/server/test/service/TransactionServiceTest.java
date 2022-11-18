package group3.EcoBikeRental.server.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.booleanThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import group3.EcoBikeRental.server.model.CreditCard;
import group3.EcoBikeRental.server.model.Transaction;
import group3.EcoBikeRental.server.repository.ITransactionRepo;
import group3.EcoBikeRental.server.service.ITransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {
	@Autowired
	private ITransactionService transactionService;
	@MockBean
	ITransactionRepo transactionRepo;

	/***
	 * Decrease balance successfully when credit card is valid and enough balance
	 */
	@Test
	public void checkCardBeingUsed() {
		String cardNumber = "3033944";

		Transaction transaction = new Transaction();
		Mockito.when(transactionRepo.getTransactionWithCardBeingUse(cardNumber)).thenReturn(transaction);
		boolean isBeingUsed = transactionService.isCardBeingUsed(cardNumber);
		assertEquals(isBeingUsed, true);
	}
}
