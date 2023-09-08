package com.grupodisatel.cotizaciones.RESTController;

import com.grupodisatel.cotizaciones.Dao.RoleDAO;
import com.grupodisatel.cotizaciones.Model.Role;
import com.grupodisatel.cotizaciones.Utils.JWTUtil;
import com.grupodisatel.cotizaciones.Validation.UserRoleValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/role")
public class RoleController {

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private UserRoleValidation userRoleValidation;

    @GetMapping(value = "")
    public List<Role> get(@RequestHeader (value = "Authorization") String token){
        if (userRoleValidation.validatePermission(token, 3)){
            return roleDAO.getRoles();
        }
        return null;
    }

    @RequestMapping(value = "getRoleName/{id}", method = RequestMethod.GET)
    public Role getRoleName(@PathVariable int id, @RequestHeader(value = "Authorization") String token){
        if (userRoleValidation.validatePermission(token, 3)){
            return roleDAO.existRole(id);
        }
        return null;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteRole(@PathVariable int id, @RequestHeader(value = "Authorization") String token){
        if (userRoleValidation.validatePermission(token, 3)){
            return roleDAO.deleteRole(id);
        }
        return false;
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public boolean updateRole(@PathVariable int id, @RequestBody Role role){
        return roleDAO.updateRole(id, role);
    }


}
