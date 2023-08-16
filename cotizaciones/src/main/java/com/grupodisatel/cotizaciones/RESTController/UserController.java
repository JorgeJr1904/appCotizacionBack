package com.grupodisatel.cotizaciones.RESTController;

import com.grupodisatel.cotizaciones.Dao.UserDAO;
import com.grupodisatel.cotizaciones.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public boolean newUser(@RequestBody User user){
        return userDAO.newUser(user);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteUser(@PathVariable int id){
        return userDAO.deleteUser(id);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody User user){
        return userDAO.updateUser(user);
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public List<User> getUser(){
        return userDAO.getUsers();
    }

}
