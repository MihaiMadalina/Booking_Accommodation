package ro.sda.booking.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Host;
import ro.sda.booking.core.service.HostService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/host")
public class HostRestService {
    @Autowired
    private HostService hostService;

    @Path("/find-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Host> findAll() {
        return hostService.getAll();
    }

    @Path("/find/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Host getHost(@PathParam("id") long id) {
        Host host = hostService.getHost(id);
        return host;
    }

    @Path("/find")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Host getHost(@QueryParam("email") String email) {
        return hostService.getHostByEmail(email);
    }


    @Path("/delete")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void deleteClient(@QueryParam("hostId") long id) {
        Host h = hostService.getHost(id);
        hostService.delete(h);
    }

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Host create(Host host) {
        return hostService.create(host);
    }

    @Path("/update")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Host updateClient(Host host){
        return hostService.update(host);
    }

}
