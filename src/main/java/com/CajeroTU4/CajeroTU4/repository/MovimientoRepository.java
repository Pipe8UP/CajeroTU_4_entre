package com.CajeroTU4.CajeroTU4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CajeroTU4.CajeroTU4.Cuentaid;
import com.CajeroTU4.CajeroTU4.entity.Movimiento;



public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
   List<Movimiento> findByCuenta(Cuentaid cuenta);
   List<Movimiento> findByCuentaOrderByFechaDesc(Cuentaid cuenta);

}
