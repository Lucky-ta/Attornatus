package br.com.lucas.attornatus.repository;

import br.com.lucas.attornatus.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}