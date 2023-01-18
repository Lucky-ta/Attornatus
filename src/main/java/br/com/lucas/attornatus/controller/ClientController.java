package br.com.lucas.attornatus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.attornatus.entity.Client;
import br.com.lucas.attornatus.repository.ClientRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Client salvar(@RequestBody Client user) {
        return clientRepository.save(user);
    }

    @PutMapping("/user/{id}")
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

    @GetMapping("user/{id}")
    public Client getClient(@PathVariable Long id) {
        Client existingClient = clientRepository.findById(id).get();
        return existingClient;
    }
}
