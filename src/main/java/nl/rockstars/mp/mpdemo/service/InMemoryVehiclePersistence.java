package nl.rockstars.mp.mpdemo.service;


import nl.rockstars.mp.mpdemo.model.Vehicle;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.*;



@ApplicationScoped
@MemoryPersistence
public class InMemoryVehiclePersistence implements VehiclePersistence {

    private Map<String, Vehicle> vehicles = new HashMap<>();

    @Override
    public Vehicle findById(String uuid) {
        if (vehicles.containsKey(uuid)) {
            return vehicles.get(uuid);
        } else {
            return null;
        }
    }

    @Override
    public List<Vehicle> findAll() {
        var results = new ArrayList<Vehicle>(vehicles.size());
        vehicles.values().forEach( v -> results.add(v));
        return results;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        if (vehicle.getUuid() == null) {
            vehicle.setUuid(UUID.randomUUID().toString());
            vehicle.setCreatedAt(LocalDateTime.now());
        } else {
            this.delete(vehicle.getUuid());
        }
        vehicle.setUpdatedAt(LocalDateTime.now());
        vehicles.put(vehicle.getUuid(), vehicle);
        return vehicle;
    }


    @Override
    public Long delete(String uuid) {
        if (vehicles.containsKey(uuid)) {
            vehicles.remove(uuid);
            return 1L;
        }
        return 0L;
    }
}
