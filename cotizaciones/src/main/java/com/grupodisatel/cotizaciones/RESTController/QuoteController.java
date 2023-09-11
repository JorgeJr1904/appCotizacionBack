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

    @PostMapping(value = "new")
    public Quote newQuote(@RequestBody Quote quote, @RequestHeader(value = "Authorization") String token){
        return quoteDAO.newQuote(quote, token);
    }

    @DeleteMapping(value = "delete/{id}")
    public boolean deleteQuote(@PathVariable int id){
        return quoteDAO.deleteQuote(id);
    }

    @PutMapping(value = "update")
    public boolean updateQuote(@RequestBody Quote quote){
        return quoteDAO.updateQuote(quote);
    }

    @GetMapping(value = "get")
    public List<Quote> getQuote(@RequestHeader(value = "Authorization") String token){
        if (userRoleValidation.validatePermission(token, 2)){
            int id = Integer.parseInt(jwtUtil.getKey(token));
            return quoteDAO.getUserQuote(id);
        }
        return null;
    }

    @GetMapping(value = "get/{id}")
    public Quote getQuote(@PathVariable int id, @RequestHeader(value = "Authorization") String token){
        if (userRoleValidation.validatePermission(token, 2)){
            return quoteDAO.getQuote(id);
        }
        return null;

    }
}
