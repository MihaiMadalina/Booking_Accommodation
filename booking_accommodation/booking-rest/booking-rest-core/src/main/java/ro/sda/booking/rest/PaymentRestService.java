package ro.sda.booking.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Payment;
import ro.sda.booking.core.service.PaymentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/payment")
public class PaymentRestService {

    @Autowired
    private PaymentService paymentService;

    @Path("/find-all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Payment> findAll(){
        return paymentService.getAll();
    }

    @Path("/find/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Payment getPayment(@PathParam("id") long id){
        return paymentService.getPayment(id);
    }

    @Path("/delete")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void deletePayment(@QueryParam("paymentId") long id){
        Payment payment = paymentService.getPayment(id);
        paymentService.delete(payment);
    }

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Payment create(Payment payment){
        return paymentService.create(payment);
    }

    @Path("/update")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Payment update(Payment payment){
        return paymentService.update(payment);
    }
}
