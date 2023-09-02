package com.grupodisatel.cotizaciones.RESTController;

import com.grupodisatel.cotizaciones.Dao.RoleDAO;
import com.grupodisatel.cotizaciones.Model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/role")
public class RoleController {

    @Autowired
    private RoleDAO roleDAO;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Role> get(){
        return roleDAO.getRoles();
    }

    @RequestMapping(value = "getRoleName/{id}", method = RequestMethod.GET)
    public Role getRoleName(@PathVariable int id){
        return roleDAO.existRole(id);
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public boolean newRole(@RequestBody Role role){
        return roleDAO.createRole(role);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteRole(@PathVariable int id){
        return roleDAO.deleteRole(id);
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public boolean updateRole(@PathVariable int id, @RequestBody Role role){
        return roleDAO.updateRole(id, role);
    }


}
