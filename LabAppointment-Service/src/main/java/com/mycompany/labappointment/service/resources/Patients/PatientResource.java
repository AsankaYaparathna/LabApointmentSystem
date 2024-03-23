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
import com.google.gson.GsonBuilder;
import com.mycompany.labappointment.service.resources.Utility.LocalDateAdapter;
import com.mycompany.labappointment.service.resources.Utility.LoginRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;
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
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
            PatientRepo patientRepo = new PatientRepo();
            List<Patient> list = patientRepo.getAllPatients();
            return Response.ok(gson.toJson(list)).build();
        } catch (Exception e) {
            logger.error("Failed to get all patients", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatientById(@PathParam("id") int id) {
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
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
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
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

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginPatient(String json) {
        Gson gson = new Gson();
        LoginRequest loginRequest = gson.fromJson(json, LoginRequest.class);
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();

        PatientRepo patientRepo = new PatientRepo();
        boolean loginSuccess = patientRepo.login(userName, password);

        if (loginSuccess) {
            return Response.ok().build(); // Successful login
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build(); // Unauthorized
        }
    }

}
