package nl.rockstars.mp.mpdemo.service;


import nl.rockstars.mp.mpdemo.model.Vehicle;
import nl.rockstars.mp.mpdemo.model.dto.VehicleDTO;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class VehicleMapper {

    public VehicleDTO toDTO(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.id = vehicle.getUuid();
        dto.active = vehicle.isActive();
        dto.createdAt = vehicle.getCreatedAt();
        dto.description = vehicle.getDescription();
        dto.registration = vehicle.getRegistration();
        dto.updatedAt = vehicle.getUpdatedAt();
        return dto;
    }

    public List<VehicleDTO> toDTOList(List<Vehicle> vehicles) {
        return vehicles.stream().map(this::toDTO).collect(Collectors.toList());
    }


}
