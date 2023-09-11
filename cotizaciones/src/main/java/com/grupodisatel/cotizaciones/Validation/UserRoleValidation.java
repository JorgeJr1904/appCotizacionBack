package com.grupodisatel.cotizaciones.Validation;

import com.grupodisatel.cotizaciones.DTO.PermissionsRoleDTO;
import com.grupodisatel.cotizaciones.Dao.PermissionRoleDAO;
import com.grupodisatel.cotizaciones.Utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRoleValidation {

    @Autowired
    private PermissionRoleDAO permissionRoleDAO;

    @Autowired
    private JWTUtil jwtUtil;

    public boolean validatePermission(String token, int moduleId){
        int idRole = jwtUtil.getRole(token);
        PermissionsRoleDTO permissionsRoleDTO = permissionRoleDAO.getPermissionRole(idRole);
        int[] permission = permissionsRoleDTO.getIdPermission();
        for (int j : permission) {
            if (j == moduleId) {
                return true;
            }
        }
        return false;
    }

    public int idUser(String token){
        return Integer.parseInt(jwtUtil.getKey(token));
    }
}
