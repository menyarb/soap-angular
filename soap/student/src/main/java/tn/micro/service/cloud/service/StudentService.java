package tn.micro.service.cloud.service;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import tn.micro.service.cloud.entity.Student;
import tn.micro.service.cloud.proxy.AddressController;
import tn.micro.service.cloud.repository.StudentRepository;
import tn.micro.service.cloud.request.CreateAdressRequest;
import tn.micro.service.cloud.request.CreateStudentRequest;
import tn.micro.service.cloud.response.AdressResponse;
import tn.micro.service.cloud.response.StudentResponse;

@Service
public class StudentService implements IStudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	AddressController client;

	//@Autowired
	//WebClient webClient;

	@Override
	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
		System.out.println("create service : "+createStudentRequest.getCity());
		CreateAdressRequest adressRequest = new CreateAdressRequest(createStudentRequest.getCity(),createStudentRequest.getStreet());
		System.out.println("adressRequest: "+adressRequest.getCity());
		AdressResponse adress = client.createAddress(adressRequest);
		System.out.println("addresse: "+adress.getCity());
		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		student.setAddressId(adress.getId());

		student = studentRepository.save(student);

		return new StudentResponse(student, adress);
	}

//	private AdressResponse createAdressWithWebClient(CreateAdressRequest request) {
//		
//		return webClient.post().uri("/create").contentType(MediaType.APPLICATION_JSON)
//				.bodyValue(request).retrieve().bodyToMono(AdressResponse.class).block();
//	}

	@Override
	public StudentResponse getById(long id) {
		Student student = studentRepository.findById(id).get();
		AdressResponse adress = client.getById(student.getAddressId());
		return new StudentResponse(student, adress);
	}
	@Override
	public void delete(long id) {
		Student student = studentRepository.findById(id).get();
		AdressResponse adress = client.getById(student.getAddressId());
		client.delete(adress.getId());
		studentRepository.deleteById(id);
	}

	@Override
	public List<StudentResponse> getAllStudents() {
		return StudentResponse.toArrayList(studentRepository.findAll());
	}

//	public Mono<AdressResponse> getAddress(Long addressId) {
//		return webClient.get() // Déclare une requête GET
//				.uri("/getById/" + addressId) // Ajoute l'ID à l'URI
//				.retrieve() // Exécute l'appel
//				.bodyToMono(AdressResponse.class); // Convertit la réponse en un objet de type Response
//	}
@Override
public List<StudentResponse> getAllStudentsWithAddress() {
	// Récupère tous les étudiants de la base de données
	List<Student> students = studentRepository.findAll();

	// Mappe chaque étudiant à un StudentResponse avec l'adresse associée
	List<StudentResponse> studentResponses = students.stream().map(student -> {
		AdressResponse address = client.getById(student.getAddressId());
		return new StudentResponse(student, address);
	}).toList();

	return studentResponses;
}


}
