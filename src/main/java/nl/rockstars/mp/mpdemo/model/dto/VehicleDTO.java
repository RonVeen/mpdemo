package nl.rockstars.mp.mpdemo.model.dto;

import javax.json.bind.annotation.JsonbProperty;
import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class VehicleDTO {

    @JsonbProperty(value = "id")
    public String id;
    public String registration;
    public String description;
    public boolean active;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

}
