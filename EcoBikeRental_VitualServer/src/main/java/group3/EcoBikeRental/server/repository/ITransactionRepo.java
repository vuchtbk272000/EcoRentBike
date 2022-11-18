package group3.EcoBikeRental.server.repository;

import group3.EcoBikeRental.server.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepo extends JpaRepository<Transaction, Long> {
	@Query(value = "SELECT * FROM transaction join credit_card on transaction.card_id = credit_card.card_id where credit_card.card_number = ?1 and is_closed=0", nativeQuery = true)
	Transaction getTransactionWithCardBeingUse(String cardNumber);
   
	@Modifying
    @Query(value = "delete from transaction where bike_id = ?1", nativeQuery = true)
    void deleteTransactionsByBikeId(long bikeId);

	@Query(value = "Select * from transaction where bike_id = ?1 and is_closed = 0", nativeQuery = true)
	Transaction getOpenTransactionByBikeId(long bikeId);
}
