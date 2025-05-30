package com.CajeroTU4.CajeroTU.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import com.CajeroTU4.CajeroTU.entity.Cliente;
import com.CajeroTU4.CajeroTU.entity.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    Optional<Cuenta> findByNumero(String numero);

    List<Cuenta> findByCliente(Cliente cliente);  // <-- corregido aquí

}

