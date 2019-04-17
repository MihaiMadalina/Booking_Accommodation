package ro.sda.booking.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Rating;
import ro.sda.booking.core.service.RatingService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/rating")
public class RatingRestService {

    @Autowired
    private RatingService ratingService;

    @Path("/find-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rating> findAll(){
        return ratingService.getAll();
    }

    @Path("/find/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Rating getRating(@PathParam("id") long id){
        return ratingService.getRating(id);
    }

    @Path("/delete")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void deleteRating(@QueryParam("ratingId") long id){
        Rating rating = ratingService.getRating(id);
        ratingService.delete(rating);
    }

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Rating create(Rating rating){
        return ratingService.create(rating);
    }

    @Path("/update")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Rating update(Rating rating){
        return ratingService.update(rating);
    }
}
