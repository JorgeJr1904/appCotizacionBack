package com.grupodisatel.cotizaciones.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cliente")
@ToString @EqualsAndHashCode
public class Customer {

    @Id
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
