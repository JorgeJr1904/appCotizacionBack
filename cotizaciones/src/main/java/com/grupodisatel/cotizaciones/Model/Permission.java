package com.grupodisatel.cotizaciones.Model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "permisos")
@EqualsAndHashCode @ToString
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "idpermiso")
    private int idPermission;

    @Getter @Setter @Column(name = "vnombrepermiso")
    private String permissionName;

    @Getter @Setter @Column(name = "cestado")
    private char status;
}
