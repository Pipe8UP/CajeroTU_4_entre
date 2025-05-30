package com.CajeroTU4.CajeroTU.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CajeroTU4.CajeroTU.entity.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByIdentificacion(String identificacion);
    

}
