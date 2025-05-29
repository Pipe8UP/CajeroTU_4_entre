package Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.CajeroTU4.CajeroTU4.Cuentaid;
import com.CajeroTU4.CajeroTU4.entity.Cliente;
import com.CajeroTU4.CajeroTU4.entity.TipoCuenta;
import com.CajeroTU4.CajeroTU4.repository.CuentaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuentaService {
     private final CuentaRepository cuentaRepository;
     public Cuentaid crearCuenta(Cliente cliente, 
     String numero, TipoCuenta tipo, double saldoInicial){
        Cuentaid cuenta = Cuentaid.builder()
        .cliente(null)
        .numero(numero)
        .tipo(tipo)
        .saldo(saldoInicial)
        .build();
        return cuentaRepository.save(cuenta);
    }

    public Optional<Cuentaid> buscarPorNumero(String numero){
        return cuentaRepository.findByNumero(numero);
    }

    public double consultarSaldo(Cuentaid cuenta){
        return cuenta.getSaldo();
    }

    public List<Cuentaid> obtenerCuentasCliente(Cliente cliente){
        return cliente.getCuentas(); 
    }

    public void actualizarSaldo(Cuentaid cuenta, double nuevoSaldo) {
        cuenta.setSaldo(nuevoSaldo);
        cuentaRepository.save(cuenta);
    }

    public List<Cuentaid> buscarPorCliente(Cliente cliente) {
        return cuentaRepository.findByCliente(cliente);
    }

    public Cuentaid obtenerCuentaPorClienteActual(String username){
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}

