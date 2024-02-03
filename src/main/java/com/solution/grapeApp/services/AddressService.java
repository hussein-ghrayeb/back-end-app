package com.solution.grapeApp.services;

import com.solution.grapeApp.entities.Address;
import com.solution.grapeApp.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository repository;
    public List<Address> getAllAddresses(){
        return  repository.findAll();
    }
    public Optional<Address> getAddressById(String id){
        return  repository.findById(id);
    }
    public Address saveAddress(Address  address){
        return repository.save(address);
    }
    public void deleteAddressById(String id){
        repository.deleteById(id);
    }
    public Boolean isAddressExists(String id){
        return repository.existsById(id);
    }

    public List<Address> getAddressesByCustomerId(String customerId){
        return repository.findAddressByCustomerId(customerId);
    }
}


