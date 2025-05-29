package com.CajeroTU4.CajeroTU4.entity;

import java.time.LocalDateTime;

import com.CajeroTU4.CajeroTU4.Cuentaid;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipo;
    private double monto;
    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuentaid cuenta;
}
