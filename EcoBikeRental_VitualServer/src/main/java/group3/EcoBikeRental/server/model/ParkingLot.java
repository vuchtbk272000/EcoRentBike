package group3.EcoBikeRental.server.model;

import lombok.Data;
import org.springframework.core.SpringVersion;

import javax.persistence.*;

@Entity
@Data
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long parkingLotId;

    private String stationName;
    private String stationAddress;
    private int numberOfBikes;
    private int numberOfEBikes;
    private int numberOfTwinBikes;
    private int maxSlot;
    private int numberOfEmptySlot;
}
