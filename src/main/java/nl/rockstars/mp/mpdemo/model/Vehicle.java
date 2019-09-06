package nl.rockstars.mp.mpdemo.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@Entity("vehicles")
@Schema(name="Vehicle", description = "Vehicle information")
public class Vehicle {

    @Id
    private String uuid;
    @Schema(required = true)
    private String registration;
    @Schema(required = true)
    private String description;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static class Builder {

        private String uuid;
        private String registration;
        private String description;
        private boolean active;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder withUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder withRegistration(String registration) {
            this.registration = registration;
            return this;
        }


        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withActive(boolean active) {
            this.active = active;
            return this;
        }

        public Builder  withCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder withUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Vehicle build() {
            Vehicle vehicle = new Vehicle();
            vehicle.setUuid(this.uuid);
            vehicle.setRegistration(this.registration);
            vehicle.setDescription(this.description);
            vehicle.setActive(this.active);
            vehicle.setCreatedAt(this.createdAt);
            vehicle.setUpdatedAt(this.updatedAt);
            return vehicle;
        }

    }

    public Vehicle() {
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
