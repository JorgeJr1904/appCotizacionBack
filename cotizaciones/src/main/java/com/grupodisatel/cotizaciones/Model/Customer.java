package com.grupodisatel.cotizaciones.Model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cliente")
@ToString @EqualsAndHashCode
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "idcliente")
    private int idCustomer;

    @Getter @Setter @Column(name = "tipo")
    private String customerType;

    @Getter @Setter @Column(name = "vnombrecliente")
    private String name;

    @Getter @Setter @Column(name = "vapellidocliente")
    private String lastname;

    @Getter @Setter @Column(name = "vdescripcion")
    private String Description;
}
