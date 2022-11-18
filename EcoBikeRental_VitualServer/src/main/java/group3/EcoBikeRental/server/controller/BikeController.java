package group3.EcoBikeRental.server.controller;

import group3.EcoBikeRental.server.model.Bike;
import group3.EcoBikeRental.server.service.IBikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/")
public class BikeController {
    @Autowired
    private IBikeService bikeService;

    @GetMapping(value = "/bike/get-all")
    public List<Bike> getAll(){
        return bikeService.getAll();
    }
    
    @PostMapping(value = "/bike/admin/add")
    // request gui len kem theo 1 json object
    public Bike addBike(@RequestBody Bike bike){
        return bikeService.addBike(bike);
    }

    @GetMapping(value = "/bike/get-by-id")
    // request gui len co dang : /bike/get-by-id?id=1
    public Bike getById(@RequestParam(name = "id") long id){
        return bikeService.getById(id);
    }

    @PostMapping(value = "/bike/admin/update-by-id")
    // request gui len: /bike/admin/update-by-id?id=1, kem theo 1 json object
    public Bike updateById(@RequestParam(name = "id") long id, @RequestBody Bike updatedBike){
        return bikeService.updateById(id, updatedBike);
    }

    @PostMapping(value = "/bike/admin/delete-by-id")
    public boolean deleteById(@RequestParam(name = "id") long id){
        return bikeService.deleteById(id);
    }
}
