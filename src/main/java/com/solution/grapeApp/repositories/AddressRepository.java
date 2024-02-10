package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<Address,String> {
    public List<Address> findAddressByCustomerId(String customerId);
}
