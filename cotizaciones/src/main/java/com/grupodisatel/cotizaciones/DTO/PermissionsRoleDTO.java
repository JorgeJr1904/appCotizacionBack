package com.grupodisatel.cotizaciones.DTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @EqualsAndHashCode
public class PermissionsRoleDTO {

    @Getter @Setter
    String roleName;

    @Getter @Setter
    int[] idPermission;
}
