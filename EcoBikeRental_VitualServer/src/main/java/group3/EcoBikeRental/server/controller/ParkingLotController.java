package group3.EcoBikeRental.server.controller;

import group3.EcoBikeRental.server.model.Bike;
import group3.EcoBikeRental.server.model.ParkingLot;
import group3.EcoBikeRental.server.service.IParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingLotController {
    @Autowired
    private IParkingLotService parkingLotService;

    @GetMapping(value = "/parking/get-all")
    public List<ParkingLot> getAll(){
        return parkingLotService.getAll();
    }

    @GetMapping(value = "/parking/get-by-id")
    // request gui len co dang : /parkingLot/get-by-id?id=1
    public ParkingLot getById(@RequestParam(name = "id") long id){
        return parkingLotService.getById(id);
    }

    @GetMapping(value = "/parking/get-all-bike")
    public List<Bike> viewBikeInParkingLot(@RequestParam(name = "id") long id){
        return parkingLotService.viewBikeInParkingLot(id);
    }

    @PostMapping(value = "/parking/admin/add")
    // request gui len kem theo 1 json object
    public int addParkingLot(@RequestBody ParkingLot parkingLot){
        return parkingLotService.addParkingLot(parkingLot);
    }


    @PostMapping(value = "/parking/admin/update-by-id")
    // request gui len: /parking/admin/update-by-id?id=1, kem theo 1 json object
    public int updateById(@RequestParam long id, @RequestBody ParkingLot updatedParkingLot){
        return parkingLotService.updateById(id, updatedParkingLot);
    }

    @PostMapping(value = "/parking/admin/delete-by-id")
    public int deleteById(@RequestParam long id){
        return parkingLotService.deleteById(id);
    }


}
