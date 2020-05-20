package com.appointmentmgmtsyst.api;

import java.time.LocalDate;
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

import com.appointmentmgmtsyst.model.Appointment;
import com.appointmentmgmtsyst.model.Doctor;
import com.appointmentmgmtsyst.model.Patient;
import com.appointmentmgmtsyst.services.AppointmentServiceImpl;
import com.appointmentmgmtsyst.services.DoctorServiceImpl;
import com.appointmentmgmtsyst.services.PatientServiceImpl;

@SuppressWarnings("unchecked")
@Path("appointment")
public class AppointmentResources {

	AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();
	DoctorServiceImpl doctorService = new DoctorServiceImpl();
	PatientServiceImpl patientService = new PatientServiceImpl();
	
	@GET
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	public Response getAppointments() {
		return Response.status(Status.OK).entity(appointmentService.getAllAppointments()).build();
	}
	
	@POST
	@Path("date")
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	public Response getAppointmentByDate(String date) {
		return Response.status(Status.OK).entity(appointmentService.getAppointmentsByDate(LocalDate.parse(date))).build();
	}
	
	@POST
	@Path("patient")
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	public Response getAppointmentByPatient(Patient patient) {
		return Response.status(Status.OK).entity(appointmentService.getAppointmentsByPatient(patient)).build();
	}
	
	@GET
	@Path("doctor")
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	public Response getAppointmentByDoctor(Doctor doctor) {
		return Response.status(Status.OK).entity(appointmentService.getAppointmentsByDoctor(doctor)).build();
	}
	
	@POST
	@Path("add")
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	public Response addAppointment(Appointment appointment) {
		System.out.println(appointment);
		if(!doctorService.getDoctorById(appointment.getDoctor().getDoctorId()).isPresent()) {
			JSONObject msg = new JSONObject();
			msg.put("error", "select a valid doctor");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
		else if(!patientService.getPatientById(appointment.getPatient().getPatientId()).isPresent()) {
			JSONObject msg = new JSONObject();
			msg.put("error", "select a valid patient");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
		else if(LocalDate.now().compareTo(appointment.getDate())<0) {
			JSONObject msg = new JSONObject();
			msg.put("error", "select a valid date");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
		else {
			JSONObject msg = new JSONObject();
			int id = appointmentService.addAppointment(appointment);
			msg.put("userid", id);
			msg.put("msg","user created successfully");
			return Response.status(Status.CREATED).entity(msg).build();
		}
	}
	
	@POST
	@Path("update")
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	public Response updateAppointment(Appointment appointment) {
		if(!doctorService.getDoctorById(appointment.getDoctor().getDoctorId()).isPresent()) {
			JSONObject msg = new JSONObject();
			msg.put("error", "select a valid doctor");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
		else if(!patientService.getPatientById(appointment.getPatient().getPatientId()).isPresent()) {
			JSONObject msg = new JSONObject();
			msg.put("error", "select a valid patient");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
		else if(LocalDate.now().compareTo(appointment.getDate())<0) {
			JSONObject msg = new JSONObject();
			msg.put("error", "select a valid date");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
//		else if(appointmentService.getAllAppointments().stream().filter(a -> appointment.getDoctor().getDoctorId() == a.getDoctor().getDoctorId()).filter(a -> appointment.getDate().equals(a.getDate())).anyMatch(a -> appointment.getTime().equals(a.getTime()))) {
//			JSONObject msg = new JSONObject();
//			msg.put("error", "doctor already has a appoinment at that date and time");
//			return Response.status(Status.BAD_REQUEST).entity(msg).build();
//		}
		else {
			JSONObject msg = new JSONObject();
			int id = appointmentService.addAppointment(appointment);
			msg.put("userid", id);
			msg.put("msg","updated appointment successfullly");
			return Response.status(Status.CREATED).entity(msg).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON })
	public Response deleteAppointment(@PathParam("id")int id) {
		Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
		if(appointment.isPresent()) {
		if(appointmentService.deleteAppointment(appointment.get())) {
			JSONObject msg = new JSONObject();
			msg.put("success", "apppointment deleted");
			return Response.status(Status.OK).entity(msg).build();
		}
		else {
			JSONObject msg = new JSONObject();
			msg.put("error", "unable to delte the appointment");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
		}
		else {
			JSONObject msg = new JSONObject();
			msg.put("error", "appointment doesn't exists");
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
	}
	
}
