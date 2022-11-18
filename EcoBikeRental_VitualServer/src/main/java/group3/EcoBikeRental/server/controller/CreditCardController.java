package group3.EcoBikeRental.server.controller;

import group3.EcoBikeRental.server.model.Bike;
import group3.EcoBikeRental.server.model.CreditCard;
import group3.EcoBikeRental.server.service.ICreditCardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreditCardController {
    @Autowired
    private ICreditCardService creditCardService;

    @GetMapping(value = "/credit-card/get-balance")
    public double getBalanceByCardNumber(@RequestParam(name = "cardNumber") String cardNumber){
    	return creditCardService.getBalanceByCardNumber(cardNumber);
    }
    
    @PostMapping(value = "/credit-card/decrease-balance")
    public ResponseEntity<Object> decreaseBalance(@RequestParam(name = "amount") double amount, @RequestBody CreditCard card){
    	return creditCardService.decreaseBalance(card, amount);
    }
    
    
    @GetMapping(value = "/credit-card", produces=MediaType.APPLICATION_JSON_VALUE)
    public CreditCard  getByCardNumber(@RequestParam(name = "cardNumber") String cardNumber){
    	return creditCardService.getByCardNumber(cardNumber);
    }

}
