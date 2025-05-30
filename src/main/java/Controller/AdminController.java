package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.CajeroTU4.CajeroTU.entity.Cliente;
import com.CajeroTU4.CajeroTU.entity.Cuenta;
import com.CajeroTU4.CajeroTU.entity.TipoCuenta;

import Service.ClienteService;
import Service.CuentaService;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ClienteService clienteService;
    private final CuentaService cuentaService;

    @GetMapping
    public String adminHome(){
        return "admin/index";
    }

    @GetMapping("/crear-cliente")
    public String mostrarFormularioCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "admin/crear-cliente";
    }

    @GetMapping("/crear-cuenta")
    public String mostrarFormularioCuenta(Model model) {
        model.addAttribute("cuenta", new Cuenta());     
        return "admin/crear-cuenta";
    }

    @PostMapping("/crear-cuenta")
public String crearCuenta(@RequestParam String identificacion,
                          @RequestParam String numero,
                          @RequestParam TipoCuenta tipo,
                          @RequestParam double saldo) {
    Cliente cliente = clienteService.buscarPorIdentificacion(identificacion).orElseThrow();
    cuentaService.crearCuenta(cliente, numero, tipo, saldo);       
    return "redirect:/admin";
}


    @GetMapping("/desbloquear")
    public String mostrarDesbloqueo() {
        return "admin/desbloquear";
    }

    @PostMapping("/desbloquear")
    public String desbloquearCuenta(@RequestParam String identificacion,
    @RequestParam String nuevoPin) {
        clienteService.desbloquearCliente(identificacion, nuevoPin);
        return "redirect:/admin";
    }
    
    


    
    
    

}
