package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,String> {
    public List<Address> findAddressByCustomerId(String customerId);
}
