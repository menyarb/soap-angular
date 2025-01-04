package tn.micro.service.cloud.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import tn.micro.service.cloud.request.CreateAdressRequest;
import tn.micro.service.cloud.response.AdressResponse;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")

@FeignClient(value = "api-gateway")
public interface AddressController {

	@PostMapping("/address-service/api/address/create")
	AdressResponse createAddress(@RequestBody CreateAdressRequest createAddressRequest);

	@GetMapping("/address-service/api/address/getById/{id}")
	AdressResponse getById(@PathVariable long id) ;

	@DeleteMapping("/address-service/api/address/{id}")
	void delete(@PathVariable long id);

}
