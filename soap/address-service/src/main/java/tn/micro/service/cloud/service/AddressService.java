package tn.micro.service.cloud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.micro.service.cloud.entity.Address;
import tn.micro.service.cloud.repository.AddressRepository;
import tn.micro.service.cloud.request.CreateAddressRequest;
import tn.micro.service.cloud.response.AddressResponse;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public AddressResponse createAddress(CreateAddressRequest createAddressRequest) {
        Address address = new Address();
        address.setStreet(createAddressRequest.getStreet());
        address.setCity(createAddressRequest.getCity());

        address = addressRepository.save(address);

        return new AddressResponse(address);
    }

    public AddressResponse getById(long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
        return new AddressResponse(address);
    }
    public void delete(long id){

        addressRepository.deleteById(id);
    }

    public List<AddressResponse> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(AddressResponse::new)
                .collect(Collectors.toList());
    }
}
