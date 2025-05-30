package Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.CajeroTU4.CajeroTU.entity.Cuenta;
import com.CajeroTU4.CajeroTU.entity.Movimiento;
import com.CajeroTU4.CajeroTU.repository.CuentaRepository;
import com.CajeroTU4.CajeroTU.repository.MovimientoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public Movimiento registrarMovimiento(Cuenta cuenta, com.CajeroTU4.CajeroTU.entity.TipoMovimiento tipo, double monto){
        Movimiento movimiento = Movimiento.builder()
            .cuenta(cuenta)
            .tipo(tipo)
            .monto(monto)
            .fecha(LocalDateTime.now())
            .build();
        return movimientoRepository.save(movimiento);
    }

    public List<Movimiento> obtenerMovimientosPorCuenta(Cuenta cuenta) {
        return movimientoRepository.findByCuenta(cuenta);
    }

    public boolean realizarRetiro(Cuenta cuenta, double monto){
        if (cuenta.getSaldo() >= monto) {
            cuenta.setSaldo(cuenta.getSaldo() - monto);
            cuentaRepository.save(cuenta);
            registrarMovimiento(cuenta, com.CajeroTU4.CajeroTU.entity.TipoMovimiento.RETIRO, monto);
            return true;       
        }
        return false;
    }

    public boolean realizarTransferencia(Cuenta origen, Cuenta destino, double monto) {
        if (origen.getSaldo() >= monto) {
            origen.setSaldo(origen.getSaldo() - monto);
            destino.setSaldo(destino.getSaldo() + monto);
            cuentaRepository.save(origen);
            cuentaRepository.save(destino);

            registrarMovimiento(origen, com.CajeroTU4.CajeroTU.entity.TipoMovimiento.TRANSFERENCIA, -monto);
            registrarMovimiento(destino, com.CajeroTU4.CajeroTU.entity.TipoMovimiento.TRANSFERENCIA, monto);
            return true;
        }
        return false;
    }

    public List<Movimiento> buscarPorCuenta(String numeroCuenta){
        Cuenta cuenta = cuentaRepository.findByNumero(numeroCuenta)
            .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return movimientoRepository.findByCuentaOrderByFechaDesc(cuenta);
    }

    public boolean realizarConsignacion(Cuenta cuenta, double monto){
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
        cuenta.setSaldo(cuenta.getSaldo() + monto);
        cuentaRepository.save(cuenta);
        registrarMovimiento(cuenta, com.CajeroTU4.CajeroTU.entity.TipoMovimiento.CONSIGNACION, monto);
        return true;
    }
}

