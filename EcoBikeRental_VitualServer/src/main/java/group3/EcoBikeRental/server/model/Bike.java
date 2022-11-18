package group3.EcoBikeRental.server.model;


import com.sun.istack.NotNull;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("0")
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bikeId;

    // we can not change type (eBike can not be TwinBike,...etc
    // Unless delete this bike and insert a new one
    private long type;
    private double cost;
    private double weight;
    private String licensePlate;
    private LocalDate manufacturingDate;
    private String producer;

    @ManyToOne
    @JoinColumn(name = "parkingLotId")
    // in fact, hibernate will generate column name = "parking_lot_id"
    // for e.g: parkingLotLot -> parking_lot_lot
    private ParkingLot parkingLot;
}
