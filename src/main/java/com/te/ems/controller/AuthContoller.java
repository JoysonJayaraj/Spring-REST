package com.te.ems.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.ems.dto.EmployeeDTO;
import com.te.ems.dto.EmployeeLoginDTO;
import com.te.ems.dto.EmployeeRegistrationDTO;
import com.te.ems.dto.UpdateEmployeeDTO;
import com.te.ems.response.SuccessResponse;
import com.te.ems.service.EmployeeService;

import lombok.RequiredArgsConstructor;

//@Controller
//@ResponseBody
//or
@RequiredArgsConstructor
@RequestMapping(path = "/app/employee")
@RestController
public class AuthContoller {
	 
	private final EmployeeService employeeService;

//	@GetMapping(path = "/dummy", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//	public String dummy() {
//		return "Dummy String";
//	}
	
	@GetMapping(path = "/dummyEmployee", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public EmployeeRegistrationDTO dummyEmployee() {
		return new EmployeeRegistrationDTO();
	}
	
	@GetMapping(path = "/dummyPassword", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public UpdateEmployeeDTO dummyPassword() {
		return new UpdateEmployeeDTO();
	}

//	@GetMapping(path = "/{employeeId}", produces = { MediaType.APPLICATION_JSON_VALUE,
//			MediaType.APPLICATION_XML_VALUE })
//	public String getEmployee(@PathVariable String employeeId) {
//		System.out.println("WE reached here");
//		System.out.println(employeeId);
//		return "Some string data";
//	}
	
	@GetMapping(path = "/{employeeId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<SuccessResponse> getEmployee(@PathVariable String employeeId) {
		
		EmployeeDTO employeeDTO =  employeeService.getEmployee(employeeId);
		
		return ResponseEntity.ok().body(
				SuccessResponse.builder()
				.message(employeeId + " Details")
				.data(new EmployeeDTO())
				.status(HttpStatus.OK)
				.timestamp("@ " + LocalDateTime.now().toString())
				.build()
				);			
	}
	
	@PostMapping(path = "/", 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<SuccessResponse> register(@RequestBody EmployeeRegistrationDTO registrationDTO ) {
		
		String employeeId = employeeService.insertEmployee(registrationDTO);
		System.out.println(employeeId + " added");
		
		return ResponseEntity.ok().body(
				SuccessResponse.builder()
				.message(registrationDTO.getEmployeeId() + " registered")
				.data(registrationDTO)
				.status(HttpStatus.OK)
				.timestamp("@ " + LocalDateTime.now().toString())
				.build());			
	}
	
	@PostMapping(path = "/login", 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<SuccessResponse> login(@RequestBody EmployeeLoginDTO employeeLoginDTO ) {
		System.out.println("-> " + employeeLoginDTO);
		boolean isLoggedIn = employeeService.loginEmployee(employeeLoginDTO);
		System.out.println(isLoggedIn + " Success");
		
		return ResponseEntity.ok().body(
				SuccessResponse.builder()
				.message(employeeLoginDTO.getUsername() + " Login Successful")
				.data(employeeLoginDTO)
				.status(HttpStatus.OK)
				.timestamp("@ " + LocalDateTime.now().toString())
				.build());			
	}
	
	@PutMapping(path = "/",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<SuccessResponse> update(@RequestBody UpdateEmployeeDTO updateEmployeeDTO) {
		
		boolean isUpdated = employeeService.updateEmployeePassword(updateEmployeeDTO);
		System.out.println(isUpdated);
		
		return ResponseEntity.ok().body(
				SuccessResponse.builder()
				.message(updateEmployeeDTO.getEmployeeId() + " Updated")
				.data(updateEmployeeDTO.getEmployeeId())
				.status(HttpStatus.OK)
				.timestamp("@ " + LocalDateTime.now().toString())
				.build());			
	}	
	
	
	@DeleteMapping(path = "/{employeeId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<SuccessResponse> deleteEmployee(@PathVariable String employeeId) {
		
		boolean isDeleted =  employeeService.deleteEmployee(employeeId);
		if(isDeleted) System.out.println(employeeId + " deleted");
		
		return ResponseEntity.ok().body(
				SuccessResponse.builder()
				.message(employeeId + " Deleted")
				.data(new EmployeeDTO())
				.status(HttpStatus.OK)
				.timestamp("@ " + LocalDateTime.now().toString())
				.build()
				);			
	}
	
	
}
