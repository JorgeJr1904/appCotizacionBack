package com.grupodisatel.cotizaciones.Model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "roles")
@ToString @EqualsAndHashCode
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "idrol")
    private int idRole;

    @Getter @Setter @Column(name = "vnombrerol")
    private String roleName;

    @Getter @Setter @Column(name = "vpalabraclave")
    private String keyWord;

    @Getter @Setter @Column(name = "cestado")
    private char status;

}
