package br.com.lucas.attornatus.service;

import br.com.lucas.attornatus.entity.Client;
import br.com.lucas.attornatus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Client salvar(Client cliente) {
        return userRepository.save(cliente);
    }

    public List<Client> listaCliente() {
        return userRepository.findAll();
    }

    public Optional<Client> buscarPorId(Long id) {
        return userRepository.findById(id);
    }

    public void removerPorId(Long id) {
        userRepository.deleteById(id);
    }
}
