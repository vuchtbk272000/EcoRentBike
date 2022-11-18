package group3.EcoBikeRental.server.test.service;

import group3.EcoBikeRental.server.model.Bike;
import group3.EcoBikeRental.server.model.Transaction;
import group3.EcoBikeRental.server.repository.IBikeRepo;
import group3.EcoBikeRental.server.repository.ITransactionRepo;
import group3.EcoBikeRental.server.service.IBikeService;
import group3.EcoBikeRental.server.service.ITransactionService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidateRentBikeTest {
    @Autowired
    private IBikeService bikeService;
    @Autowired
    private ITransactionService transactionService;
    @MockBean
    ITransactionRepo transactionRepo;
    @MockBean
    IBikeRepo bikeRepo;

    /***
     * Validate BikeId successfully and return bike
     */
    @Test
    public void checkBikeExist() {
        long bikeId = 18;

        Bike bike = new Bike();
        bike.setBikeId(18);
        bike.setType(3);
        bike.setCost(1.11);
        bike.setWeight(4.4);
        bike.setLicensePlate("K8-6827");
        bike.setManufacturingDate(LocalDate.ofEpochDay(2021-04-03));
        bike.setProducer("thongnhat");
        Mockito.when(bikeRepo.findById(bikeId)).thenReturn(Optional.of(bike));
        Bike existBike = bikeService.getById(bikeId);
        assertEquals(existBike, bike);
    }

    @Test
    public void checkGetOpenTransactionById() {
        long bikeId = 20;

        Transaction transaction = new Transaction();
        Mockito.when(transactionRepo.getOpenTransactionByBikeId(bikeId)).thenReturn(transaction);
        Transaction openingTransaction = transactionService.getOpenTransactionByBikeId(bikeId);
        assertEquals(openingTransaction, transaction);
    }

    /***
     * Validate BikeId failed and not return bike
     */
    @Test
    public void checkBikeNotExist() {
        long bikeId = 1904;

        Mockito.when(bikeRepo.findById(bikeId)).thenReturn(Optional.empty());
        Bike existBike = bikeService.getById(bikeId);
        assertEquals(existBike, null);
    }
}
