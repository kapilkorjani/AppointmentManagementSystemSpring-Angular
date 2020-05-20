package com.appointmentmgmtsyst.api;

import java.util.Optional;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.simple.JSONObject;

import com.appointmentmgmtsyst.model.Patient;
import com.appointmentmgmtsyst.services.PatientServiceImpl;

@SuppressWarnings("unchecked")
@Path("patient")
public class PatientResource {

	PatientServiceImpl patientService = new PatientServiceImpl();
	
	@GET
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	public Response getPatients() {
		return Response.status(Status.OK).entity(patientService.getAllPatients()).build();
	}
	
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	public Response getPatient(@PathParam("id") int id) {
//		patientService.getPatientById(id).ifPresent(patient -> Response.status(Status.OK).entity(patient).build());
		Optional<Patient> patient = patientService.getPatientById(id);
		if(patient.isPresent()) {
			return Response.status(Status.OK).entity(patient.get()).build();
		}
		else {
			JSONObject msg = new JSONObject();
			msg.put("error", "patient with the id not found");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
	}
	
	@POST
	@Path("/update")
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	public Response updatePatient(Patient patient) {
		if(patientService.updatePatient(patient)) {
			JSONObject msg = new JSONObject();
			msg.put("success", "patient updated");
			return Response.status(Status.OK).entity(msg).build();
		}
		else {
			JSONObject msg = new JSONObject();
			msg.put("error", "unable to update the patient");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	public Response deletePatient(@PathParam("id")int patientId) {
		Optional<Patient> patient = patientService.getPatientById(patientId);
		if(patient.isPresent())
		{
		if(patientService.deletePatient(patient.get())) {
			JSONObject msg = new JSONObject();
			msg.put("success", "patient deleted");
			return Response.status(Status.OK).entity(msg).build();
		}
		else {
			JSONObject msg = new JSONObject();
			msg.put("error", "unable to delte the patient");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
		}
		else {
			JSONObject msg = new JSONObject();
			msg.put("error", "patient does not exists");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
	}


	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	public Response addPatient(Patient patient) {
		if(patientService.getPatientByEmail(patient.getPatientEmail()).isPresent()) {
			JSONObject msg = new JSONObject();
			msg.put("error", "Patient already exists");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
		else {
			JSONObject msg = new JSONObject();
			int id = patientService.addPatient(patient);
			msg.put("userid", id);
			msg.put("msg","patient created successfully");
		return Response.status(Status.CREATED)
		.entity(msg)
		.build();
		}
	}
	
}
