package com.grupodisatel.cotizaciones.Model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cotizacion")
@EqualsAndHashCode @ToString
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "idcotizacion")
    private int id;

    @Getter @Setter @Column(name = "idusuario")
    private int user;

    @Getter @Setter @Column(name = "dfechacotizacion")
    private Date quoteDate;

    @Getter @Setter @Column(name = "vtipocliente")
    private String customerType;

    @Getter @Setter @Column(name = "vnombrecliente")
    private String Customername;

    @Getter @Setter @Column(name = "vapellidocliente")
    private String Customerlastname;

    @Getter @Setter @Column(name = "vdescripcion")
    private String Description;

    @Getter @Setter @Column(name = "dPrecioTotal")
    private BigDecimal totalPrice;

    @Getter @Setter @Column(name = "cestado")
    private char status;
}
