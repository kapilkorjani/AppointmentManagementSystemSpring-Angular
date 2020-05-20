package com.appointmentmgmtsyst.api;

import java.util.Optional;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.simple.JSONObject;

import com.appointmentmgmtsyst.model.Doctor;
import com.appointmentmgmtsyst.services.DoctorServiceImpl;

@SuppressWarnings("unchecked")
@Path("doctor")
public class DoctorResources {

	DoctorServiceImpl doctorService = new DoctorServiceImpl();

	@GET
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	public Response getDoctors() {
//		String json = new Gson().toJson(doctorService.getAllDoctors());
//		JSONArray array = new JSONArray();
//		array.addAll(doctorService.getAllDoctors());
//		System.out.println(array);
		return Response.status(Status.OK).entity(doctorService.getAllDoctors()).build();
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	public Response getDoctor(@PathParam("id") int id) {
//		doctorService.getDoctorById(id).ifPresent(doctor -> Response.status(Status.OK).entity(doctor).build());
		Optional<Doctor> doctor = doctorService.getDoctorById(id);
		if (doctor.isPresent()) {
			return Response.status(Status.OK).entity(doctor.get()).build();
		} else {
			JSONObject msg = new JSONObject();
			msg.put("error", "doctor with the id not found");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
	}

	@POST
	@Path("/update")
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	public Response updateDoctor(Doctor doctor) {
		System.out.println(doctor);
		if (doctorService.updateDoctor(doctor)) {
			JSONObject msg = new JSONObject();
			msg.put("success", "doctor updated");
			return Response.status(Status.OK).entity(msg).build();
		} else {
			JSONObject msg = new JSONObject();
			msg.put("error", "unable to update the doctor");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
	}

	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	public Response deleteDoctor(@PathParam("id") int doctorId) {
		Optional<Doctor> doctor = doctorService.getDoctorById(doctorId);
		if (doctor.isPresent()) {
			if (doctorService.deleteDoctor(doctor.get())) {
				JSONObject msg = new JSONObject();
				msg.put("success", "doctor deleted");
				return Response.status(Status.OK).entity(msg).build();
			} else {
				JSONObject msg = new JSONObject();
				msg.put("error", "unable to delete the doctor");
				return Response.status(Status.BAD_REQUEST).entity(msg).build();
			}
		} else {
			JSONObject msg = new JSONObject();
			msg.put("error", "doctor not found");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
	}

	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	public Response addDoctor(Doctor doctor) {
		System.out.println(doctor);
		if (doctorService.getDoctorByEmail(doctor.getDoctorEmail()).isPresent()) {
			JSONObject msg = new JSONObject();
			msg.put("error", "Email already exists");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		} else {
			JSONObject msg = new JSONObject();
			int id = doctorService.addDoctor(doctor);
			msg.put("userid", id);
			msg.put("success", "doctor added successfully");
			return Response.status(Status.CREATED).entity(msg).build();
		}
	}
}
