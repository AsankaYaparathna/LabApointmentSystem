/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labappointment.service.resources.Technician;

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

@Path("technician")
public class TechnicianResource {
    private static final Logger logger = LogManager.getLogger(TechnicianResource.class);
    private final Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTechnicians() {
        try {
            TechnicianRepo technicianRepo = new TechnicianRepo();
            return Response.ok(gson.toJson(technicianRepo.getAllTechnicians())).build();
        } catch (Exception e) {
            logger.error("Failed to get all technicians", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTechnicianById(@PathParam("id") int id) {
        try {
            TechnicianRepo technicianRepo = new TechnicianRepo();
            Technician technician = technicianRepo.getTechnicianByID(id);
            if (technician != null) {
                return Response.ok(gson.toJson(technician)).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Failed to get technician by ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTechnician(String json) {
        try {
            Technician model = gson.fromJson(json, Technician.class);
            // Perform input validation here if needed
            new TechnicianRepo().addTechnician(model);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.error("Failed to add technician", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTechnician(String json, @PathParam("id") int id) {
        try {
            Technician model = gson.fromJson(json, Technician.class);
            // Perform input validation here if needed
            model.setTechnicianID(id);
            new TechnicianRepo().updateTechnician(model);
            return Response.ok().build();
        } catch (Exception e) {
            logger.error("Failed to update technician with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteTechnician(@PathParam("id") int id) {
        try {
            new TechnicianRepo().deleteTechnician(id);
            return Response.ok().build();
        } catch (Exception e) {
            logger.error("Failed to delete technician with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

