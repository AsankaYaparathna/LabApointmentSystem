/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labappointment.service.resources.Reports;

/**
 *
 * @author shakyapa
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.labappointment.service.resources.Utility.LocalDateTimeAdapter;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("report")
public class ReportResource {
    private static final Logger logger = LogManager.getLogger(ReportResource.class);
    private final Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllReports() {
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
            ReportRepo reportRepo = new ReportRepo();
            return Response.ok(gson.toJson(reportRepo.getAllReports())).build();
        } catch (Exception e) {
            logger.error("Failed to get all reports", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReportById(@PathParam("id") int id) {
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
            ReportRepo reportRepo = new ReportRepo();
            Report report = reportRepo.getReportByID(id);
            if (report != null) {
                return Response.ok(gson.toJson(report)).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            logger.error("Failed to get report by ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addReport(String json) {
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
            Report model = gson.fromJson(json, Report.class);
            // Perform input validation here if needed
            new ReportRepo().addReport(model);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.error("Failed to add report", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateReport(String json, @PathParam("id") int id) {
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
            Report model = gson.fromJson(json, Report.class);
            // Perform input validation here if needed
            model.setReportID(id);
            new ReportRepo().updateReport(model);
            return Response.ok().build();
        } catch (Exception e) {
            logger.error("Failed to update report with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteReport(@PathParam("id") int id) {
        try {
            new ReportRepo().deleteReport(id);
            return Response.ok().build();
        } catch (Exception e) {
            logger.error("Failed to delete report with ID: " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

