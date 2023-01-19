package br.com.lucas.attornatus.controller;

import br.com.lucas.attornatus.repository.ClientRepository;
import br.com.lucas.attornatus.entity.Client;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@RequestBody Client user) {
        return clientRepository.save(user);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        Client existingClient = clientRepository.findById(id).get();
        if (client.getName() != null) {
            existingClient.setName(client.getName());
        }
        if (client.getBirthdate() != null) {
            existingClient.setBirthdate(client.getBirthdate());
        }
        return clientRepository.save(existingClient);
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        Client existingClient = clientRepository.findById(id).get();
        return existingClient;
    }

    @GetMapping
    public List<Client> getAllClients() {
        List<Client> existingClients = clientRepository.findAll();
        return existingClients;
    }
}
