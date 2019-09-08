package nl.rockstars.mp.mpdemo.service;

import nl.rockstars.mp.mpdemo.TechnicalException;
import nl.rockstars.mp.mpdemo.model.Vehicle;
import org.eclipse.microprofile.faulttolerance.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@RequestScoped
@Path("/vehicle")
@Produces(MediaType.APPLICATION_JSON)
public class VehicleResource {

    @Inject
    private VehicleService vehicleService;

    @Inject
    private VehicleMapper mapper;




    @GET
    @Path("moreTrouble")
    @Timeout
    @Retry(delay=500,maxRetries = 5)
    @Fallback(TroubleFallbackHandler.class)
    public Response evenMoreTrouble() {
        throw new TechnicalException();
    }



    @GET
    @CircuitBreaker(failOn = TechnicalException.class)
    @Fallback(fallbackMethod = "fallbackWhenInTrouble")
    @Path("/trouble")
    public Response inTrouble() {
        throw new TechnicalException();
    }


    public Response fallbackWhenInTrouble() {
        return Response.ok("Gered door de fallback").build();
    }


    @GET
    public Response getVehicles() {
        var vehicleList = vehicleService.findAll();
        var dtos = mapper.toDTOList(vehicleList);
        var list = new GenericEntity<>(dtos) { };

        return Response.ok(list).build();
    }

    @GET
    @Path("/{uuid}")
    public Response getVehicle(@PathParam("uuid") String uuid) {
        var vehicle = vehicleService.findVehicleById(uuid);

        var json = JsonbBuilder.create().toJson(mapper.toDTO(vehicle));
        return Response.ok(json).build();
    }



    @POST
    public Response addVehicle(String jsonBody) {
        var createdVehicle = upsertVehicle(jsonBody);
        URI createdUri = UriBuilder.fromResource(VehicleResource.class).path(createdVehicle.getUuid()).build();
        return Response.created(createdUri).build();
    }


    @PUT
    @Path("/{uuid}")
    public Response updateVehicle(@PathParam("uuid") String uuid, String jsonBody) {
        var updatedVehicle = upsertVehicle(uuid, jsonBody);
        var updatedJson = JsonbBuilder.create().toJson(updatedVehicle);
        return Response.ok(updatedJson).build();
    }


    @DELETE
    @Path("/{uuid}")
    public Response deleteVehicle(@PathParam("uuid") String uuid) {
        var deleteCount = vehicleService.delete(uuid);
        return Response.status(deleteCount > 0 ? Response.Status.NO_CONTENT : Response.Status.NOT_FOUND).build();

    }



    private Vehicle upsertVehicle(String jsonBody) {
        return upsertVehicle(null, jsonBody);
    }


    private Vehicle upsertVehicle(String uuid, String jsonBody) {
        var vehicle = JsonbBuilder.create().fromJson(jsonBody, Vehicle.class);
        vehicle.setUuid(uuid);
        return vehicleService.save(vehicle);
    }


    class TroubleFallbackHandler implements FallbackHandler<Response> {
        @Override
        public Response handle(ExecutionContext executionContext) {
            return Response.ok("Gered door de fallback").build();
        }
    }
}
