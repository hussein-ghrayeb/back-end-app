package com.solution.grapeApp.repositories;

import com.solution.grapeApp.entities.Address;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
    @Query(value = "SELECT a.* FROM addresses a WHERE a.customer_id = :customerId", nativeQuery = true)
    public List<Address> findAddressByCustomerId(String customerId);

    @Query(value = "SELECT a.* FROM addresses a WHERE a.is_default = true and a.customer_id = :customerId", nativeQuery = true)
    public List<Address> findDefaultAddressByCustomerId(String customerId);

    @Query(value = "SELECT count(*) FROM addresses WHERE is_default = true and customer_id = :customerId", nativeQuery = true)
    public int checkDefaultAddressesCount(String customerId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE addresses SET is_default = CASE id WHEN :id then true ELSE false END", nativeQuery = true)
    public void setAsDefault(String id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE addresses SET is_default = true where customer_id = :customerId and id = (SELECT id from addresses where customer_id = :customerId ORDER BY RAND() LIMIt 1)", nativeQuery = true)
    public void setRandomDefaultAddress(String customerId);
}
