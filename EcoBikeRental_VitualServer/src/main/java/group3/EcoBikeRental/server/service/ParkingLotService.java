package group3.EcoBikeRental.server.service;

import group3.EcoBikeRental.server.model.Bike;
import group3.EcoBikeRental.server.model.ParkingLot;
import group3.EcoBikeRental.server.repository.IBikeRepo;
import group3.EcoBikeRental.server.repository.IParkingLotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotService implements IParkingLotService{
    @Autowired
    private IParkingLotRepo parkingLotRepo;
    @Autowired
    private IBikeRepo bikeRepo;

    @Override
    public List<ParkingLot> getAll() {
        return parkingLotRepo.findAll();
    }


    @Override
    public ParkingLot getById(long id) {
        Optional<ParkingLot> optional =parkingLotRepo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            System.out.println("Not found Bike with id = " + id);
            return null;
        }
    }

    @Override
    public int addParkingLot(ParkingLot parkingLot) {
        parkingLotRepo.save(parkingLot); // dung cho ca insert va update
        return 1;
    }

    @Override
    public int updateById(long id, ParkingLot updatedParkingLot) {
        Optional<ParkingLot> optional = parkingLotRepo.findById(id);
        if (optional.isPresent()) {
            if(parkingLotRepo.checkMaxSlotParkingLot(updatedParkingLot.getMaxSlot(), id) ==1 ){
                System.out.println("max slot is not great enough for current number of bikes, please remove bikes from the parking lot.");
                return 2;
            }//max<bike num
            else {
                ParkingLot parkingLot = optional.get();
                parkingLot.setStationName(updatedParkingLot.getStationName());
                parkingLot.setMaxSlot(updatedParkingLot.getMaxSlot());
                parkingLot.setStationAddress(updatedParkingLot.getStationAddress());
                parkingLotRepo.save(parkingLot);
                return 1;

            }
        } else {
            System.out.println("Not found Parking Lot with id = " + id);
            return 0;
        }
    }

    @Override
    public int deleteById(long id) {
        Optional<ParkingLot> optional = parkingLotRepo.findById(id);
        if (optional.isPresent()) {
            if(parkingLotRepo.checkMaxSlotParkingLot(0, id) == 1){
                System.out.println("cannot delete this parking lot because there are bikes in it");
                return 2;
            }//max<bike num
            else{
                parkingLotRepo.deleteById(id);
                return 1;
            }
        } else {
            System.out.println("Not found Parking Lot with id = " + id);
            return 0;
        }
    }


    @Override
    public  List<Bike> viewBikeInParkingLot(long parkingLotId){
        return bikeRepo.viewBikeInParkingLot(parkingLotId);
    }
}
