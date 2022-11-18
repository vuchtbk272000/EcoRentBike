package group3.EcoBikeRental.server.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    @OneToOne
    @JoinColumn(name = "cardId")
    private CreditCard card;

    @OneToOne
    @JoinColumn(name = "bikeId")
    private Bike bike;

    private double amount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private boolean isClosed;
}
