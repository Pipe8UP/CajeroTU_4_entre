package com.CajeroTU4.CajeroTU4.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CajeroTU4.CajeroTU4.entity.Cliente;



public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByIdentificacion(String identificacion);
    

}
