package com.grupodisatel.cotizaciones.RESTController;

import com.grupodisatel.cotizaciones.Dao.UserDAO;
import com.grupodisatel.cotizaciones.Model.User;
import com.grupodisatel.cotizaciones.Validation.UserRoleValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRoleValidation userRoleValidation;

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String newUser(@RequestBody User user){
        return userDAO.newUser(user);
    }

    @DeleteMapping(value = "delete/{id}")
    public boolean deleteUser(@PathVariable int id, @RequestHeader(value = "Authorization") String token){
        if (userRoleValidation.validatePermission(token, 3)) {
            return userDAO.deleteUser(id);
        }
        return false;
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable int id,@RequestBody User user){
        return userDAO.updateUser(id, user);
    }

    @RequestMapping(value = "enable-desable/{id}", method = RequestMethod.PATCH)
    public String enableDisableUser(@PathVariable int id){
        return userDAO.d_activateUser(id);
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public List<User> getUser(){
        return userDAO.getUsers();
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public User getOnlyUser(@PathVariable int id){
        return userDAO.getOnlyUser(id);
    }

    @PatchMapping(value = "update/password/{id}")
    public boolean updatePasswordAdmin(@PathVariable int id, @RequestBody User password){
        return userDAO.updatePasswordAdmin(id, password.getPassword());
    }

}
