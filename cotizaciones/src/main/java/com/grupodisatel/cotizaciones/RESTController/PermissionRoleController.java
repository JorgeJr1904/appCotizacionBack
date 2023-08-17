package com.grupodisatel.cotizaciones.RESTController;

import com.grupodisatel.cotizaciones.Dao.PermissionRoleDAO;
import com.grupodisatel.cotizaciones.Model.Permission;
import com.grupodisatel.cotizaciones.Model.PermissionsRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/permission_role")
public class PermissionRoleController {

    @Autowired
    private PermissionRoleDAO permissionsRoleDAO;

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public boolean newPermissionRole(@RequestBody PermissionsRole permissionsRole){
        return permissionsRoleDAO.newPermissionsRole(permissionsRole);
    }
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public boolean deletePermissionRole(@PathVariable int id){
        return permissionsRoleDAO.deletePermissionsRole(id);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public boolean updatePermissionRole(@RequestBody PermissionsRole permissionsRole){
        return permissionsRoleDAO.updatePermissionRole(permissionsRole);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public PermissionsRole getPermissionRole(@PathVariable int id){
        return permissionsRoleDAO.getPermissionRole(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<PermissionsRole> getPermissionRole(){
        return permissionsRoleDAO.getAll();
    }
}
