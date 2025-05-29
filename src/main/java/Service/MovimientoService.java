package Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.CajeroTU4.CajeroTU4.Cuentaid;
import com.CajeroTU4.CajeroTU4.entity.Movimiento;
import com.CajeroTU4.CajeroTU4.entity.TipoMovimiento;
import com.CajeroTU4.CajeroTU4.repository.CuentaRepository;
import com.CajeroTU4.CajeroTU4.repository.MovimientoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public Movimiento registrarMovimiento(Cuentaid cuenta, 
    TipoMovimiento tipo, 
    double monto){
        Movimiento movimiento = Movimiento.builder()
            .cuenta(cuenta)
            .tipo(tipo)
            .monto(monto)
            .fecha(LocalDateTime.now())
            .build();
        return movimientoRepository.save(movimiento);
    }

    public List<Movimiento> obtenerMovimientosPorCuenta(Cuentaid cuenta,
    double monto) {
        return movimientoRepository.findByCuenta(cuenta);
    }

    public boolean realizarRetiro(Cuentaid cuenta, double monto){
        if (cuenta.getSaldo() >= monto) {
            cuenta.setSaldo(cuenta.getSaldo() - monto);
            cuentaRepository.save(cuenta);
            registrarMovimiento(cuenta, TipoMovimiento.RETIRO, monto);
            return true;       
            
        }
        return false;
    }

    public boolean realizarTransferencia(Cuentaid origen, 
    Cuentaid destino, double monto) {
        if (origen.getSaldo()>= monto) {
            origen.setSaldo(origen.getSaldo()- monto);
            destino.setSaldo(destino.getSaldo() + monto);
            cuentaRepository.save(origen);
            cuentaRepository.save(destino);

            registrarMovimiento(origen, TipoMovimiento.TRANSFERENCIA, -monto);
            registrarMovimiento(destino, TipoMovimiento.TRANSFERENCIA, monto);
            return true;
        }
        return false;
    }

    public List<Movimiento> buscarPorCuenta(String numeroCuenta){
        Cuentaid cuenta = cuentaRepository.findByNumero(numeroCuenta)
            .orElseThrow(() -> 
            new RuntimeException("No se encontro la cuenta ojo con eso mijo"));
            return movimientoRepository
            .findByCuentaOrderByFechaDesc(cuenta);
    }

    public boolean realizarConsignacion(Cuentaid cuenta, double monto){
        if (monto <= 0) {
            throw new IllegalArgumentException("Mi rey mas de cero pues ¡¡avispate!!!");
        }
        cuenta.setSaldo(cuenta.getSaldo() + monto);
        cuentaRepository.save(cuenta);
        registrarMovimiento(cuenta, TipoMovimiento.CONSIGNACION, monto);
        return true;
    }


}
