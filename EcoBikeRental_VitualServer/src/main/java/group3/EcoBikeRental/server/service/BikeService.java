package group3.EcoBikeRental.server.service;

import group3.EcoBikeRental.server.model.Bike;
import group3.EcoBikeRental.server.model.eBike;
import group3.EcoBikeRental.server.repository.IBikeRepo;
import group3.EcoBikeRental.server.repository.ITransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Service
public class BikeService implements IBikeService{
    @Autowired
    private IBikeRepo bikeRepo;

    @Autowired
    private ITransactionRepo transactionRepo;

    @Override
    public List<Bike> getAll() {
        return bikeRepo.findAll();
    }

    @Override
    public Bike getById(long id) {
        Optional<Bike> optional = bikeRepo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            System.out.println("Not found Bike with id = " + id);
            return null;
        }
    }

    @Override
    public Bike addBike(Bike bike) {
        return bikeRepo.save(bike);
    }

    @Override
    public Bike updateById(long id, Bike updatedBike) {
        Optional<Bike> optional = bikeRepo.findById(id);
        if (optional.isPresent()) {
            Bike bike = optional.get();
            bike.setCost(updatedBike.getCost());
            bike.setWeight(updatedBike.getWeight());
            bike.setLicensePlate(updatedBike.getLicensePlate());
            bike.setProducer(updatedBike.getProducer());
            bike.setManufacturingDate(updatedBike.getManufacturingDate());
            bike.setParkingLot(updatedBike.getParkingLot());
            if(bike instanceof eBike){
                ((eBike) bike).setBatteryPercentage(((eBike) updatedBike).getBatteryPercentage());
                ((eBike) bike).setLoadCycle(((eBike) updatedBike).getLoadCycle());
                ((eBike) bike).setUsageTimeRemaining(((eBike) updatedBike).getUsageTimeRemaining());
            }
            bikeRepo.save(bike);
            return bike;
        } else {
            System.out.println("Not found Bike with id = " + id);
            return null;
        }
    }

    @Override
    @Transactional
    // also delete corresponding records in transaction table
    public boolean deleteById(long id) {
        Optional<Bike> optional = bikeRepo.findById(id);
        if (optional.isPresent()) {
            transactionRepo.deleteTransactionsByBikeId(id);
            bikeRepo.deleteById(id);
            return true;
        } else {
            System.out.println("Not found Bike with id = " + id);
            return false;
        }
    }
}
