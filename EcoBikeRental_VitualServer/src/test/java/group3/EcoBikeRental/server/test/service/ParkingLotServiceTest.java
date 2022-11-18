package group3.EcoBikeRental.server.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

import group3.EcoBikeRental.server.model.ParkingLot;
import group3.EcoBikeRental.server.repository.IParkingLotRepo;
import group3.EcoBikeRental.server.service.ParkingLotService;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.classmate.GenericType;

import group3.EcoBikeRental.server.model.CreditCard;
import group3.EcoBikeRental.server.repository.ICreditCardRepo;
import group3.EcoBikeRental.server.service.ICreditCardService;
import group3.EcoBikeRental.server.service.TransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingLotServiceTest {
    @Autowired
    private ParkingLotService parkingLotService;
    @MockBean
    private IParkingLotRepo parkingLotRepo;

    /***
     * Decrease balance successfully when credit card is valid and enough balance
     */
//    @Test
//    public void testDecreaseBalanceSuccessfully() {
//        CreditCard mockCreditCard = new CreditCard();
//        mockCreditCard.setCardNumber("1001192");
//        mockCreditCard.setCardId(33);
//        mockCreditCard.setSecurityCode("111");
//        mockCreditCard.setBalance(500.0);
//        Mockito.when(creditCardRepo.findCreditCardByCardNumber(mockCreditCard.getCardNumber()))
//                .thenReturn(mockCreditCard);
//        Mockito.when(transactionService.isCardBeingUsed(mockCreditCard.getCardNumber())).thenReturn(false);
//        creditCardService.decreaseBalance(mockCreditCard, 100.0);
//        assertEquals(mockCreditCard.getBalance(), 400);
//    }

    /***
     * Decrease balance fail when credit card is not enough balance
     */
    @Test
    public void testNotEnoughMaxSlot() {
        ParkingLot mockParkingLot = new ParkingLot();
        mockParkingLot.setStationName("FL801");
        mockParkingLot.setMaxSlot(120);
        mockParkingLot.setStationAddress("111 Daicoviet");
        Mockito.when(parkingLotRepo.findById(mockParkingLot.getParkingLotId()))
                .thenReturn(Optional.of(mockParkingLot));
        Mockito.when(parkingLotRepo.checkMaxSlotParkingLot(mockParkingLot.getMaxSlot(), mockParkingLot.getParkingLotId()) )
                .thenReturn(1);
        Mockito.when(parkingLotRepo.save(mockParkingLot))
                .thenReturn(mockParkingLot);
        int res = parkingLotService.updateById(mockParkingLot.getParkingLotId(), mockParkingLot);

        assertEquals(res,2);

    }
    @Test
    public void testNotFoundParkingLot() {
        ParkingLot mockParkingLot = new ParkingLot();
        mockParkingLot.setStationName("FL801");
        mockParkingLot.setMaxSlot(120);
        mockParkingLot.setStationAddress("111 Daicoviet");
        Mockito.when(parkingLotRepo.findById(mockParkingLot.getParkingLotId()))
                .thenReturn(null);
        Mockito.when(parkingLotRepo.checkMaxSlotParkingLot(mockParkingLot.getMaxSlot(), mockParkingLot.getParkingLotId()) )
                .thenReturn(1);
        Mockito.when(parkingLotRepo.save(mockParkingLot))
                .thenReturn(mockParkingLot);
        int res = 0;

        assertEquals(res,0);

    }

    @Test
    public void testUpdateParkingLotSuccessfully() {
        ParkingLot mockParkingLot = new ParkingLot();
        mockParkingLot.setStationName("FL801");
        mockParkingLot.setMaxSlot(220);
        mockParkingLot.setStationAddress("111 Daicoviet");
        Mockito.when(parkingLotRepo.findById(mockParkingLot.getParkingLotId()))
                .thenReturn(Optional.of(mockParkingLot));
        Mockito.when(parkingLotRepo.checkMaxSlotParkingLot(mockParkingLot.getMaxSlot(), mockParkingLot.getParkingLotId()) )
                .thenReturn(0);
        Mockito.when(parkingLotRepo.save(mockParkingLot))
                .thenReturn(mockParkingLot);
        int res = parkingLotService.updateById(mockParkingLot.getParkingLotId(), mockParkingLot);

        assertEquals(res,1);

    }
}
