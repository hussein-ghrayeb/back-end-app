package com.solution.grapeApp.controllers;

import com.solution.grapeApp.entities.Address;
import com.solution.grapeApp.entities.Customer;
import com.solution.grapeApp.services.AddressService;
import com.solution.grapeApp.services.CustomerService;

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

    @Autowired
    CustomerService customerService;

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
            if (addressService.checkDefaultAddressesCount(address.getCustomer().getId()) == 0) {
                address.setIsDefault(true);
            }
            Address savedAddress = addressService.saveAddress(address);
            Optional<Customer> customerOptional = customerService
                    .getCustomerById(address.getCustomer().getId());
            if (!customerOptional.get().getHasDefaultAddress()) {
                customerOptional.get().setHasDefaultAddress(true);
                customerService.saveCustomer(customerOptional.get());
            }
            return ResponseEntity.ok(savedAddress);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/setAsDefaultAddress")
    public ResponseEntity<?> setAsDefault(@RequestParam String id) {
        try {
            Optional<Address> optionalAddress = addressService.getAddressById(id);

            if (optionalAddress.isPresent()) {
                addressService.setAsDefault(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteAddress")
    public ResponseEntity<Void> deleteAddress(@RequestParam String id) {
        try {
            Optional<Address> optionalAddress = addressService.getAddressById(id);
            if (optionalAddress.isPresent()) {
                if (optionalAddress.get().getIsDefault()) {
                    addressService.setRandomDefaultAddress(optionalAddress.get().getId());
                }
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

    @GetMapping("/getDefaultAddressByCustomerId")
    public ResponseEntity<Address> getDefaultAddressByCustomerId(@RequestParam String customerId) {
        if (addressService.getDefaultAddress(customerId).size() > 0)
            return ResponseEntity.ok(addressService.getDefaultAddress(customerId).get(0));
        else
            return ResponseEntity.noContent().build();
    }

}
