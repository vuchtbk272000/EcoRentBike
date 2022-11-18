package group3.EcoBikeRental.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group3.EcoBikeRental.server.model.CreditCard;
import group3.EcoBikeRental.server.model.Transaction;
import group3.EcoBikeRental.server.repository.ITransactionRepo;

@Service
public class TransactionService implements ITransactionService{
	@Autowired
	ITransactionRepo transactionRepo;
	/**
	 * Check if card is being use for rent in another transaction
	 * 
	 * @param cardNumber
	 * 
	 * @return <Transaction>
	 */
	@Override
	public boolean isCardBeingUsed(String cardNumber) {
		// check if there exist an transaction that card is being use
		Transaction  transaction = transactionRepo.getTransactionWithCardBeingUse(cardNumber);
		if (transaction == null) return false;
		return true;
	}

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepo.save(transaction);
	}

	@Override
	public Transaction getOpenTransactionByBikeId(long bikeId) {
		return transactionRepo.getOpenTransactionByBikeId(bikeId);
	}


}
