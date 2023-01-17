package br.com.lucas.attornatus.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.attornatus.entity.Client;
import br.com.lucas.attornatus.service.UserService;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Client salvar(@RequestBody Client user) {
        return userService.salvar(user);
    }
}
