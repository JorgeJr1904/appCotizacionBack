package com.grupodisatel.cotizaciones.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "pedido")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "idpedido")
    private int id;

    @Getter @Setter @Column(name = "vDescripcion")
    private String Description;

    @Getter @Setter @Column(name = "icantidadhombres")
    private int men;

    @Getter @Setter @Column(name = "dpreciohora")
    private BigDecimal hourPrice;

    @Getter @Setter @Column(name = "ihorasdia")
    private int hourDays;

    @Getter @Setter @Column(name = "idias")
    private int days;

    @Getter @Setter @Column(name = "dpreciopedido")
    private BigDecimal totalPrice;

    @Getter @Setter @Column(name = "idcotizacion")
    private int price;

}
