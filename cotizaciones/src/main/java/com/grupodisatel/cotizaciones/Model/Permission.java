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
@Table(name = "permisos")
@EqualsAndHashCode @ToString
public class Permission {

    @Id
    @Getter @Setter @Column(name = "idpermiso")
    private int idPermission;

    @Getter @Setter @Column(name = "vnombrepermiso")
    private String permissionName;
}
