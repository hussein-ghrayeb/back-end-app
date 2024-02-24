package com.solution.grapeApp.controllers;

import com.solution.grapeApp.entities.Address;
import com.solution.grapeApp.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/getAllAddresses")
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @GetMapping("/getAddressById")
    public ResponseEntity<Address> getAddressById(@RequestParam String id) {
        Optional<Address> optionalAddress = addressService.getAddressById(id);

        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            return ResponseEntity.ok(address);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/saveAddress")
    public ResponseEntity<Address> saveAddress(@RequestBody Address address) {
        try {
            Address savedAddress = addressService.saveAddress(address);
            return ResponseEntity.ok(savedAddress);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/deleteAddress")
    public ResponseEntity<Void> deleteAddress(@RequestParam String id) {
        try {
            if (addressService.isAddressExists(id)) {
                addressService.deleteAddressById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAddressesByCustomerId")
    public ResponseEntity<List<Address>> getAddressesByCustomerId(@RequestParam String customerId) {
        return ResponseEntity.ok(addressService.getAddressesByCustomerId(customerId));
    }

}
