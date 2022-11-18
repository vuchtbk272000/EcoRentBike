package group3.EcoBikeRental.server.repository;

import group3.EcoBikeRental.server.model.CreditCard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICreditCardRepo extends JpaRepository<CreditCard, Long> {
    CreditCard findCreditCardByCardNumber(String cardNumber);

}
