package com.CajeroTU4.CajeroTU4;

import java.util.List;

import com.CajeroTU4.CajeroTU4.entity.Cliente;
import com.CajeroTU4.CajeroTU4.entity.Movimiento;
import com.CajeroTU4.CajeroTU4.entity.TipoCuenta;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuentaid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    @Column(unique = true)
    private String numero;
    private double saldo;
    @Enumerated(EnumType.STRING)
    private TipoCuenta tipo;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<Movimiento> movimientos;

}