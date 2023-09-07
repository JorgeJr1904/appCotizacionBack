package com.grupodisatel.cotizaciones.Model;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "cotizaciones")
@EqualsAndHashCode @ToString
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "idcotizacion")
    private int id;

    @Getter @Setter @Column(name = "idusuario")
    private int user;

    @Getter @Setter @Column(name = "dfechacotizacion")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date quoteDate;

    @Getter @Setter @Column(name = "vtipocliente")
    private String customerType;

    @Getter @Setter @Column(name = "vnombrecliente")
    private String customerName;

    @Getter @Setter @Column(name = "vapellidocliente")
    private String customerLastname;

    @Getter @Setter @Column(name = "dpreciototal")
    private BigDecimal totalPrice;

    @Getter @Setter @Column(name = "cestado")
    private char status;
}
