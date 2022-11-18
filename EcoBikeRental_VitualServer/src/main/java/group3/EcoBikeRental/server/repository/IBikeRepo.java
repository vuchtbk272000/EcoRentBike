package group3.EcoBikeRental.server.repository;

import group3.EcoBikeRental.server.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBikeRepo extends JpaRepository<Bike, Long> {
    @Query(value =  "select * from bike where  parking_lot_id = ?1",nativeQuery = true)
    List<Bike> viewBikeInParkingLot(long parkingLotId);
}
