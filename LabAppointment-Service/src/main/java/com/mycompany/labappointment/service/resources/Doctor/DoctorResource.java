/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labappointment.service.resources.Doctor;

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

@Path("doctor")
public class DoctorResource {
    private static final Logger logger = LogManager.getLogger(DoctorResource.class);
    private final Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDoctors() {
        try {
            DoctorRepo doctorRepo = new DoctorRepo();
            return Response.ok(gson.toJson(doctorRepo.getAllDoctors())).build();
        } catch (Exception e) {
            logger.error("Failed to get all doctors", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorById(@PathParam("id") int id) {
        try {
            DoctorRepo doctorRepo = new DoctorRepo();
            Doctor doctor = doctorRepo.getDoctorByID(id);
            if (doctor != null) {
                return Response.ok(gson.toJson(doctor)).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Failed to get doctor by ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDoctor(String json) {
        try {
            Doctor model = gson.fromJson(json, Doctor.class);
            // Perform input validation here if needed
            new DoctorRepo().addDoctor(model);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.error("Failed to add doctor", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDoctor(String json, @PathParam("id") int id) {
        try {
            Doctor model = gson.fromJson(json, Doctor.class);
            // Perform input validation here if needed
            model.setDoctorID(id);
            new DoctorRepo().updateDoctor(model);
            return Response.ok().build();
        } catch (Exception e) {
            logger.error("Failed to update doctor with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteDoctor(@PathParam("id") int id) {
        try {
            new DoctorRepo().deleteDoctor(id);
            return Response.ok().build();
        } catch (Exception e) {
            logger.error("Failed to delete doctor with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

