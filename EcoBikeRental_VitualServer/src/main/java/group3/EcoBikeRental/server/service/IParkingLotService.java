package group3.EcoBikeRental.server.service;

import group3.EcoBikeRental.server.model.Bike;
import group3.EcoBikeRental.server.model.ParkingLot;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IParkingLotService{
    List<ParkingLot> getAll();
    ParkingLot getById(long id);
    int addParkingLot(ParkingLot parkingLot);
    int updateById(long id, ParkingLot updatedParkingLot);
    int deleteById(long id);
    List<Bike> viewBikeInParkingLot(long parkingLotId);
}
