/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labappointment.service.resources.Patients;

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

@Path("patient")
public class PatientResource {
    private static final Logger logger = LogManager.getLogger(PatientResource.class);
    private final Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPatients() {
        try {
            PatientRepo patientRepo = new PatientRepo();
            return Response.ok(gson.toJson(patientRepo.getAllPatients())).build();
        } catch (Exception e) {
            logger.error("Failed to get all patients", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatientById(@PathParam("id") int id) {
        try {
            PatientRepo patientRepo = new PatientRepo();
            Patient patient = patientRepo.getPatientByID(id);
            if (patient != null) {
                return Response.ok(gson.toJson(patient)).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Failed to get patient by ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPatient(String json) {
        try {
            Patient model = gson.fromJson(json, Patient.class);
            // Perform input validation here if needed
            new PatientRepo().addPatient(model);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.error("Failed to add patient", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePatient(String json, @PathParam("id") int id) {
        try {
            Patient model = gson.fromJson(json, Patient.class);
            // Perform input validation here if needed
            model.setPatientID(id);
            new PatientRepo().updatePatient(model);
            return Response.ok().build();
        } catch (Exception e) {
            logger.error("Failed to update patient with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletePatient(@PathParam("id") int id) {
        try {
            new PatientRepo().deletePatient(id);
            return Response.ok().build();
        } catch (Exception e) {
            logger.error("Failed to delete patient with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

