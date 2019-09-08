package nl.rockstars.mp.mpdemo.service;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;

@Health
@ApplicationScoped
public class HealthController implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("VehicleHealth")
                .withData("MongoConnections", 2)
                .withData("SpaceRemaining", 8939839)
                .withData("Clustered", true)
                .up()
                .build();
    }
}

