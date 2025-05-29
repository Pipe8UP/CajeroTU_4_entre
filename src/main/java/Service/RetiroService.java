package Service;

import org.springframework.stereotype.Service;

import com.CajeroTU4.CajeroTU4.Cuentaid;
import com.CajeroTU4.CajeroTU4.entity.Cliente;
import com.CajeroTU4.CajeroTU4.repository.ClienteRepository;
import com.CajeroTU4.CajeroTU4.repository.CuentaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RetiroService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;
    private final MovimientoService movimientoService;

    public String realizarRetiro( String identificacion, String numeroCuenta, double monto){
        Cliente cliente = clienteRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Cuentaid cuenta = cuentaRepository.findByNumero(numeroCuenta)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        if (!cuenta.getCliente().equals(cliente)) {
            throw new RuntimeException("La cuenta no pertenece al cliente");
        }

        if(cliente.isBloqueado()){
            throw new RuntimeException("Estas bloqueado mi bro");
        }

        boolean exito = movimientoService.realizarRetiro(cuenta, monto);

        if (!exito) {
            throw new RuntimeException("Saldo insuficiente ¡¡Que pobre!!");
        }

        return "redirect:cajero/menu?mensaje=Retiro realizado con éxito";
    }

}
