/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labappointment.service.resources.Appointment;

import com.google.gson.Gson;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author shakyapa
 */
@Path("appointment")
public class AppointmentResource {
    private static final Logger logger = LogManager.getLogger(AppointmentResource.class);
    private final Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAppointments() {
        try {
            AppointmentRepo appointmentRepo = new AppointmentRepo();
            return Response.ok(gson.toJson(appointmentRepo.getAllAppointments())).build();
        } catch (Exception e) {
            logger.error("Failed to get all appointments", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentById(@PathParam("id") int id) {
        try {
            AppointmentRepo appointmentRepo = new AppointmentRepo();
            Appointment appointment = appointmentRepo.getAppointmentByID(id);
            if (appointment != null) {
                return Response.ok(gson.toJson(appointment)).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Failed to get appointment by ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAppointment(String json) {
        try {
            Appointment model = gson.fromJson(json, Appointment.class);
            // Perform input validation here if needed
            new AppointmentRepo().addAppointment(model);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.error("Failed to add appointment", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAppointment(String json, @PathParam("id") int id) {
        try {
            Appointment model = gson.fromJson(json, Appointment.class);
            // Perform input validation here if needed
            model.setAppointmentID(id);
            new AppointmentRepo().updateAppointment(model);
            return Response.ok().build();
        } catch (Exception e) {
            logger.error("Failed to update appointment with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteAppointment(@PathParam("id") int id) {
        try {
            new AppointmentRepo().deleteAppointment(id);
            return Response.ok().build();
        } catch (Exception e) {
            logger.error("Failed to delete appointment with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
