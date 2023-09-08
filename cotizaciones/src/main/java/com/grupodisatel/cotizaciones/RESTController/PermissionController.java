package com.grupodisatel.cotizaciones.RESTController;

import com.grupodisatel.cotizaciones.Dao.PermissionDAO;
import com.grupodisatel.cotizaciones.Model.Permission;
import com.grupodisatel.cotizaciones.Utils.JWTUtil;
import com.grupodisatel.cotizaciones.Validation.UserRoleValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/permission/v1")
public class PermissionController {

        @Autowired
        private PermissionDAO permissionDAO;

        @Autowired
        private UserRoleValidation userRoleValidation;

        @RequestMapping(value = "new", method = RequestMethod.POST)
        public boolean newPermission(@RequestBody Permission permission, @RequestHeader(value = "Authorization") String token){
            if (userRoleValidation.validatePermission(token, 3)) {
                return permissionDAO.createPermission(permission);
            }else {
                return false;
            }
        }
        @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
        public boolean deletePermission(@PathVariable int id, @RequestHeader(value = "Authorization")String token){
            if (userRoleValidation.validatePermission(token, 3)) {
                return permissionDAO.deletePermission(id);
            }else {
                return false;
            }
        }

        @RequestMapping(value = "update", method = RequestMethod.PUT)
        public boolean updatePermission(@RequestBody Permission permission, @RequestHeader(value = "Authorization")String token){
            if (userRoleValidation.validatePermission(token, 3)) {
                return permissionDAO.updatePermision(permission);
            }
            else {
                return false;
            }
        }

        @RequestMapping(value = "", method = RequestMethod.GET)
        public List<Permission> getPermission(@RequestHeader(value = "Authorization")String token){
            if (userRoleValidation.validatePermission(token, 3)) {
                return permissionDAO.getPermissions();
            }
            else {
                return null;
            }

        }

}
