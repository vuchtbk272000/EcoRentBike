package group3.EcoBikeRental.server.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@DiscriminatorValue("2")
public class eBike extends Bike{
    private int batteryPercentage;
    private int loadCycle;
    private double usageTimeRemaining;
}
