package com.grupodisatel.cotizaciones.RESTController;

import com.grupodisatel.cotizaciones.Dao.QuoteDAO;
import com.grupodisatel.cotizaciones.Model.Order;
import com.grupodisatel.cotizaciones.Model.Quote;
import com.grupodisatel.cotizaciones.Utils.JWTUtil;
import com.grupodisatel.cotizaciones.Validation.UserRoleValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/quote/v1")
public class QuoteController {

    @Autowired
    private QuoteDAO quoteDAO;

    @Autowired
    private UserRoleValidation userRoleValidation;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public Quote newQuote(@RequestBody Quote quote, @RequestHeader(value = "Authorization") String token){
        return quoteDAO.newQuote(quote, token);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteQuote(@PathVariable int id){
        return quoteDAO.deleteQuote(id);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public boolean updateQuote(@RequestBody Quote quote){
        return quoteDAO.updateQuote(quote);
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public List<Quote> getQuote(@RequestHeader(value = "Authorization") String token){
        if (userRoleValidation.validatePermission(token, 2)){
            int id = Integer.parseInt(jwtUtil.getKey(token));
            return quoteDAO.getUserQuote(id);
        }
        return null;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Quote> getUser(){
        return quoteDAO.getAdminQuote();
    }
}
