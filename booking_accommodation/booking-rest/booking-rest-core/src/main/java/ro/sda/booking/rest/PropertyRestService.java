package ro.sda.booking.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Property;
import ro.sda.booking.core.service.PropertyService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/property")
public class PropertyRestService {
    @Autowired
    private PropertyService propertyService;

    @Path("/find-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Property> findAll(){
        return propertyService.getAll();
    }

    @Path("/find/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Property getProperty(@PathParam("id") long id){
        return propertyService.getProperty(id);
    }

    @Path("/find")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Property getProperty(@QueryParam("name") String name){
        return propertyService.getPropertyByName(name);
    }


    @Path("/delete")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void deleteProperty(@QueryParam("propertyId") long id){
        Property p = propertyService.getProperty(id);
        propertyService.delete(p);
    }

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Property create(Property property){
        return propertyService.create(property);
    }

    @Path("/update")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Property update(Property property){
        return propertyService.update(property);
    }
}
