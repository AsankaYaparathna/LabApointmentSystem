/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labappointment.service.resources.Payment;

/**
 *
 * @author shakyapa
 */
import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("payment")
public class PaymentResource {
    private static final Logger logger = LogManager.getLogger(PaymentResource.class);
    private final Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPayments() {
        try {
            PaymentRepo paymentRepo = new PaymentRepo();
            return Response.ok(gson.toJson(paymentRepo.getAllPayments())).build();
        } catch (Exception e) {
            logger.error("Failed to get all payments", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaymentById(@PathParam("id") int id) {
        try {
            PaymentRepo paymentRepo = new PaymentRepo();
            Payment payment = paymentRepo.getPaymentByID(id);
            if (payment != null) {
                return Response.ok(gson.toJson(payment)).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Failed to get payment by ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPayment(String json) {
        try {
            Payment model = gson.fromJson(json, Payment.class);
            // Perform input validation here if needed
            new PaymentRepo().addPayment(model);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.error("Failed to add payment", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePayment(String json, @PathParam("id") int id) {
        try {
            Payment model = gson.fromJson(json, Payment.class);
            // Perform input validation here if needed
            model.setPaymentID(id);
            new PaymentRepo().updatePayment(model);
            return Response.ok().build();
        } catch (Exception e) {
            logger.error("Failed to update payment with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    public Response deletePayment(@PathParam("id") int id) {
        try {
            new PaymentRepo().deletePayment(id);
            return Response.ok().build();
        } catch (Exception e) {
            logger.error("Failed to delete payment with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

