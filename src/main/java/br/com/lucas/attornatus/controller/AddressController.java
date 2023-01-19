package br.com.lucas.attornatus.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lucas.attornatus.entity.Address;
import br.com.lucas.attornatus.entity.Client;
import org.springframework.http.ResponseEntity;

import br.com.lucas.attornatus.repository.AddressRepository;
import br.com.lucas.attornatus.repository.ClientRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/user/{id}")
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

    @GetMapping("/user/address/{id}")
    public Address getUserAddress(@PathVariable Long id) {
        Address existingAddress = addressRepository.findById(id).get();
        return existingAddress;
    }

}
