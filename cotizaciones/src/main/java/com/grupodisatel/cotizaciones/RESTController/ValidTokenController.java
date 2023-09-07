package com.grupodisatel.cotizaciones.RESTController;

import com.grupodisatel.cotizaciones.Dao.ValidTokenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.POST;

@RestController
@RequestMapping("api/validToken/v1")
public class ValidTokenController {

    @Autowired
    ValidTokenDAO validTokenDAO;

    @PostMapping("validation")
    public boolean validTkn(@RequestHeader(value = "Authorization") String token){
        return validTokenDAO.validToken(token);
    }
}
