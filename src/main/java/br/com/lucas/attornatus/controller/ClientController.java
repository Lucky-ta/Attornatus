package br.com.lucas.attornatus.controller;

import org.springframework.web.bind.annotation.PostMapping;
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

}
