package ro.sda.booking.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Booking;
import ro.sda.booking.core.service.BookingService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/booking")
public class BookingRestService {

    @Autowired
    private BookingService bookingService;

    @Path("/find-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Booking> findAll(){
        return bookingService.getAll();
    }

    @Path("/find/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Booking getBooking(@PathParam("id") long id){
        return bookingService.getBooking(id);
    }

    @Path("/delete")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void deleteBooking(@QueryParam("bookingId") long id){
        Booking booking = bookingService.getBooking(id);
        bookingService.delete(booking);
    }

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Booking create(Booking booking){
        return bookingService.create(booking);
    }

    @Path("/update")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Booking update(Booking booking){
        return bookingService.update(booking);
    }

}
