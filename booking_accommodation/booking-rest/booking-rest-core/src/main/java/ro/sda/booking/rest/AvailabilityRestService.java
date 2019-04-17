package ro.sda.booking.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Availability;
import ro.sda.booking.core.service.AvailabilityService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@Path("/availability")
public class AvailabilityRestService {
    @Autowired
    private AvailabilityService availabilityService;

    @Path("/find-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Availability> findAll(){
        return availabilityService.getAll();
    }

    @Path("/find/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Availability getAvailability(@PathParam("id") long id){
        return availabilityService.getAvailability(id);
    }

    @Path("/find")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Availability getAvailability (@QueryParam("propertyId") long id, @QueryParam("roomName") String roomName){
        return availabilityService.findAvailabilityByPropertyIdAndRoomName(id, roomName);
    }

    @Path("/find-by")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Availability> getAvailability (@QueryParam("fromDate") String fromDate, @QueryParam("toDate") String toDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fromLocalDate = null;
        try {
            fromLocalDate = format.parse(fromDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date toLocalDate = null;
        try {
            toLocalDate = format.parse(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return availabilityService.findAvailabilityByFromDateGreaterThanEqualAndToDateLessThanEqual(fromLocalDate, toLocalDate);
    }


    @Path("/delete")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void deleteProperty(@QueryParam("availabilityId") long id){
        Availability a = availabilityService.getAvailability(id);
        availabilityService.delete(a);
    }

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Availability create(Availability availability){
        return availabilityService.create(availability);
    }

    @Path("/update")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Availability update(Availability availability){
        return availabilityService.update(availability);
    }
}
