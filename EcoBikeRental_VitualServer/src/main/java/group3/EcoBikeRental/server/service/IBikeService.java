package group3.EcoBikeRental.server.service;

import group3.EcoBikeRental.server.model.Bike;

import java.util.List;

public interface IBikeService {
    List<Bike> getAll();
    Bike getById(long id);
    Bike addBike(Bike bike);
    Bike updateById(long id, Bike updatedBike);
    boolean deleteById(long id);
}
