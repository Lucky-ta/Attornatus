package br.com.lucas.attornatus.controller;

import br.com.lucas.attornatus.repository.*;
import br.com.lucas.attornatus.entity.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/client/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Address> createAddress(@PathVariable Long id, @RequestBody Address address) {
        Client client = clientRepository.findById(id).get();
        address.setClient(client);
        Address newAddress = addressRepository.save(address);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAddress.getId())
                .toUri();

        return ResponseEntity.created(location).body(newAddress);
    }

    @GetMapping("/{id}")
    public Address getUserAddress(@PathVariable Long id) {
        Address existingAddress = addressRepository.findById(id).get();
        return existingAddress;
    }

}
