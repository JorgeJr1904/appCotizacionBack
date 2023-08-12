package com.grupodisatel.cotizaciones.Model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "cotizacion")
@EqualsAndHashCode @ToString
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "idcotizacion")
    private int id;

    @Getter @Setter @Column(name = "idcliente")
    private int customer;

    @Getter @Setter @Column(name = "idusuario")
    private int user;

    @Getter @Setter @Column(name = "dfechacotizacion")
    private Date priceDate;
}
