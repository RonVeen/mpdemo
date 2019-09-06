package nl.rockstars.mp.mpdemo.service;

import nl.rockstars.mp.mpdemo.model.Vehicle;

import java.util.List;

public interface VehiclePersistence {
    Vehicle findById(String uuid);
    List<Vehicle> findAll();
    Vehicle save(Vehicle vehicle);
    Long delete(String uuid);
}
