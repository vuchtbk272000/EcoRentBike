package group3.EcoBikeRental.server.service;


import group3.EcoBikeRental.server.model.Transaction;

public interface ITransactionService {
	public boolean isCardBeingUsed(String cardNumber);

	public Transaction  saveTransaction(Transaction transaction);

	public Transaction getOpenTransactionByBikeId(long bikeId);
}
