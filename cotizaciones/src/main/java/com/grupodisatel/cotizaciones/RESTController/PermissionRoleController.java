package com.grupodisatel.cotizaciones.RESTController;

import com.grupodisatel.cotizaciones.DTO.PermissionsRoleDTO;
import com.grupodisatel.cotizaciones.Dao.PermissionRoleDAO;
import com.grupodisatel.cotizaciones.Model.Permission;
import com.grupodisatel.cotizaciones.Model.PermissionsRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/permission_role/v1")
public class PermissionRoleController {

    @Autowired
    private PermissionRoleDAO permissionsRoleDAO;

    @PostMapping(value = "new")
    public String newPermissionRole(@RequestBody PermissionsRoleDTO permissionsRoleDTO){
        return permissionsRoleDAO.newPermissionsRole(permissionsRoleDTO);
    }
    @DeleteMapping(value = "delete/{id}")
    public boolean deletePermissionRole(@PathVariable int id){
        return permissionsRoleDAO.deletePermissionsRole(id);
    }

    @PutMapping(value = "update")
    public boolean updatePermissionRole(@RequestBody PermissionsRole permissionsRole){
        return permissionsRoleDAO.updatePermissionRole(permissionsRole);
    }

    @RequestMapping(value = "get/{idRole}", method = RequestMethod.GET)
    public PermissionsRoleDTO getPermissionRole(@PathVariable int idRole){
        return permissionsRoleDAO.getPermissionRole(idRole);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<PermissionsRole> getPermissionRole(){
        return permissionsRoleDAO.getAll();
    }
}
