package com.CajeroTU4.CajeroTU.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CajeroTU4.CajeroTU.entity.Cuenta;
import com.CajeroTU4.CajeroTU.entity.Movimiento;


public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
   List<Movimiento> findByCuenta(Cuenta cuenta);
   List<Movimiento> findByCuentaOrderByFechaDesc(Cuenta cuenta);

}
