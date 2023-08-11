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
@Table(name = "roles")
@ToString @EqualsAndHashCode
public class Role {

    @Id
    @Getter @Setter @Column(name = "idrol")
    private int idRole;

    @Getter @Setter @Column(name = "vnombrerol")
    private String roleName;

    @Getter @Setter @Column(name = "vpalabraclave")
    private String keyWord;

}
