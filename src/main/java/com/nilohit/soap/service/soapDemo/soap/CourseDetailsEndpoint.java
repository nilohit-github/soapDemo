package com.nilohit.soap.service.soapDemo.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.in28minutes.courses.CourseDetails;
import com.in28minutes.courses.DeleteCourseDetailsRequest;
import com.in28minutes.courses.DeleteCourseDetailsResponse;
import com.in28minutes.courses.GetAllCourseDetailsRequest;
import com.in28minutes.courses.GetAllCourseDetailsResponse;
import com.in28minutes.courses.GetCourseDetailsRequest;
import com.in28minutes.courses.GetCourseDetailsResponse;
import com.nilohit.soap.service.soapDemo.soap.bean.Course;
import com.nilohit.soap.service.soapDemo.soap.bean.CourseDetailsService;
import com.nilohit.soap.service.soapDemo.soap.bean.CourseDetailsService.Status;
import com.nilohit.soap.service.soapDemo.soap.exception.CourseNotFoundException;

@Endpoint
public class CourseDetailsEndpoint {
	
	@Autowired
	CourseDetailsService coursedetailservice;
	
	@PayloadRoot(namespace="http://in28minutes.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request)
	{
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		CourseDetails courseDetails = new CourseDetails();
		Course course = coursedetailservice.findbyId(request.getId());
		if(course== null)throw new CourseNotFoundException("Course:"+request.getId()+ "was not found");
		response.setCourseDetails(mapCourse(course));
		return response;
		
	}
	
	@PayloadRoot(namespace="http://in28minutes.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request)
	{
		
		CourseDetails courseDetails = new CourseDetails();
		List<Course> courses = coursedetailservice.findAll();
		
		return mapAllCourse(courses);
		
	}
	
	private GetAllCourseDetailsResponse mapAllCourse(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		//List<CourseDetails> details = response.getCourseDetails();
			for(Course course:courses)
			{
				response.getCourseDetails().add(mapCourse(course));
			}
		return response;
	}

	private CourseDetails mapCourse(Course course) {
		
		
		CourseDetails courseDetails = new CourseDetails();
		
		courseDetails.setId(course.getId());
		
		courseDetails.setName(course.getName());
		
		courseDetails.setDescription(course.getDescription());
		
		//response.setCourseDetails(courseDetails);
		
		return courseDetails;
	}
	
	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(
			@RequestPayload DeleteCourseDetailsRequest request) {

		Status status = coursedetailservice.deleteById(request.getId());

		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(mapStatus(status));

		
		
		return response;
	}

	private com.in28minutes.courses.Status mapStatus(Status status) {
		if (status == Status.FAILURE)
			return com.in28minutes.courses.Status.FAILURE;
		return com.in28minutes.courses.Status.SUCCESS;
	}

}
