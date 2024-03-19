/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labappointment.service.resources.Test;

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

@Path("test")
public class TestResource {
    private static final Logger logger = LogManager.getLogger(TestResource.class);
    private final Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTests() {
        try {
            TestRepo testRepo = new TestRepo();
            return Response.ok(gson.toJson(testRepo.getAllTests())).build();
        } catch (Exception e) {
            logger.error("Failed to get all tests", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTestById(@PathParam("id") int id) {
        try {
            TestRepo testRepo = new TestRepo();
            Test test = testRepo.getTestByID(id);
            if (test != null) {
                return Response.ok(gson.toJson(test)).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Failed to get test by ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTest(String json) {
        try {
            Test model = gson.fromJson(json, Test.class);
            // Perform input validation here if needed
            new TestRepo().addTest(model);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.error("Failed to add test", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTest(String json, @PathParam("id") int id) {
        try {
            Test model = gson.fromJson(json, Test.class);
            // Perform input validation here if needed
            model.setTestID(id);
            new TestRepo().updateTest(model);
            return Response.ok().build();
        } catch (Exception e) {
            logger.error("Failed to update test with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteTest(@PathParam("id") int id) {
        try {
            new TestRepo().deleteTest(id);
            return Response.ok().build();
        } catch (Exception e) {
            logger.error("Failed to delete test with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

