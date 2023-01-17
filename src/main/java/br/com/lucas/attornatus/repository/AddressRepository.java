package br.com.lucas.attornatus.repository;

import br.com.lucas.attornatus.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}