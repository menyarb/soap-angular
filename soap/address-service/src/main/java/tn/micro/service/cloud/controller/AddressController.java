package tn.micro.service.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.micro.service.cloud.request.CreateAddressRequest;
import tn.micro.service.cloud.response.AddressResponse;
import tn.micro.service.cloud.service.AddressService;

@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")

public class AddressController {

	@Autowired
	AddressService addressService;

	@PostMapping("/create")
	public AddressResponse createAddress(@RequestBody CreateAddressRequest createAddressRequest) {
		return addressService.createAddress(createAddressRequest);
	}

	@GetMapping("/getById/{id}")
	public AddressResponse getById(@PathVariable long id) {
		System.out.println("********************************************");
		return addressService.getById(id);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id){
		addressService.delete(id);
	}

}
