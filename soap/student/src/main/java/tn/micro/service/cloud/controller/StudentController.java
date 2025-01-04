package tn.micro.service.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.micro.service.cloud.request.CreateStudentRequest;
import tn.micro.service.cloud.response.StudentResponse;
import tn.micro.service.cloud.service.StudentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	StudentService studentService;


	@PostMapping("/create")
	public StudentResponse createStudent (@RequestBody CreateStudentRequest createStudentRequest) {
		System.out.println("create: "+createStudentRequest.getEmail());
		return studentService.createStudent(createStudentRequest);
	}

	@GetMapping("/getById/{id}")
	public StudentResponse getById (@PathVariable long id) {
		return studentService.getById(id);
	}
	@GetMapping("/getAllStudent")
	public List<StudentResponse> getAll (){
		return studentService.getAllStudents();
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id){studentService.delete(id);}
	@GetMapping("/all")
	public List<StudentResponse> getAllStudentsWithAddress(){return studentService.getAllStudentsWithAddress();}

}
