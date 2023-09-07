package com.grupodisatel.cotizaciones.RESTController;

import com.grupodisatel.cotizaciones.Dao.PermissionDAO;
import com.grupodisatel.cotizaciones.Model.Order;
import com.grupodisatel.cotizaciones.Model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/permission/v1")
public class PermissionController {

        @Autowired
        private PermissionDAO permissionDAO;

        @RequestMapping(value = "new", method = RequestMethod.POST)
        public boolean newPermission(@RequestBody Permission permission){
            return permissionDAO.createPermission(permission);
        }
        @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
        public boolean deletePermission(@PathVariable int id){
            return permissionDAO.deletePermission(id);
        }

        @RequestMapping(value = "update", method = RequestMethod.PUT)
        public boolean updatePermission(@RequestBody Permission permission){
            return permissionDAO.updatePermision(permission);
        }

        @RequestMapping(value = "", method = RequestMethod.GET)
        public List<Permission> getPermission(){
            return permissionDAO.getPermissions();
        }

}
