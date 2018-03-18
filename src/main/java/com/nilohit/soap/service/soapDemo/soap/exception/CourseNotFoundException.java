package com.nilohit.soap.service.soapDemo.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode =FaultCode.CUSTOM,customFaultCode="{http://in28minutes.com/courses}001" )
public class CourseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7653049621328386448L;
	
	
	public CourseNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
