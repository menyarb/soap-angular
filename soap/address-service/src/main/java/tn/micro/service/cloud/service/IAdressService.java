package tn.micro.service.cloud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.micro.service.cloud.request.CreateAddressRequest;
import tn.micro.service.cloud.response.AddressResponse;

@Service
public interface IAdressService {

	AddressResponse createAddress(CreateAddressRequest createAddressRequest);

	AddressResponse getById(long id);
	void delete(long id);
	List<AddressResponse> getAllAddresss();
}
