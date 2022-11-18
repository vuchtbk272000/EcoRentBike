package group3.EcoBikeRental.server.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cardId;
    private String cardName;
    private String cardNumber;
    private String issuingBank;
    private LocalDate expirationDate;
    private String securityCode;
    private double balance;
}
