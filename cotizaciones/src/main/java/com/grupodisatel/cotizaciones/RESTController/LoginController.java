package com.grupodisatel.cotizaciones.RESTController;

import com.grupodisatel.cotizaciones.Dao.LoginDAO;
import com.grupodisatel.cotizaciones.Model.Login;
import com.grupodisatel.cotizaciones.Model.User;
import com.grupodisatel.cotizaciones.Utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class LoginController {

    @Autowired
    private LoginDAO loginDAO;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Login authLogin(@RequestBody User user){
        return loginDAO.login(user.getUserName(), user.getPassword());
    }

}
