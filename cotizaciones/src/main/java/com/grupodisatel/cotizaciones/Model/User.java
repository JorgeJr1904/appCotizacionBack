package com.grupodisatel.cotizaciones.Model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "idusuario")
    private int id;

    @Getter @Setter @Column(name = "vnombre")
    private String name;

    @Getter @Setter @Column(name = "vapellido")
    private String lastname;

    @Getter @Setter @Column(name = "vnombreusuario")
    private String userName;

    @Getter @Setter @Column(name = "vpassword")
    private String password;

    @Getter @Setter @Column(name = "idrol")
    private int idRole;

}
