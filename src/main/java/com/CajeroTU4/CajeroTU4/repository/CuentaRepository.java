package com.CajeroTU4.CajeroTU4.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CajeroTU4.CajeroTU4.Cuentaid;
import com.CajeroTU4.CajeroTU4.entity.Cliente;



public interface CuentaRepository extends JpaRepository<Cuentaid , Long> {
    Optional<Cuentaid> findByNumero(String numero);
    List<Cuentaid> findByCliente(Cliente cliente);

}

