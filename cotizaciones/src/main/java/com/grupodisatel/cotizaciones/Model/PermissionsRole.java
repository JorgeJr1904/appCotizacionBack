package com.grupodisatel.cotizaciones.Model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "roles_permisos")
@EqualsAndHashCode @ToString
public class PermissionsRole {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "idrolpermiso")
    private int id;

    @Getter @Setter @Column(name = "idrol")
    private int idRole;

    @Getter @Setter @Column(name = "idpermiso")
    private int idPermission;

    @Getter @Setter @Column(name = "cestado")
    private char status;
}
