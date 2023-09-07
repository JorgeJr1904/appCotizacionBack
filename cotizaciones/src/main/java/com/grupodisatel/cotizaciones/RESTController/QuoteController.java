package com.grupodisatel.cotizaciones.RESTController;

import com.grupodisatel.cotizaciones.Dao.QuoteDAO;
import com.grupodisatel.cotizaciones.Model.Order;
import com.grupodisatel.cotizaciones.Model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/quote/v1")
public class QuoteController {

    @Autowired
    private QuoteDAO quoteDAO;

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public Quote newQuote(@RequestBody Quote quote){
        return quoteDAO.newQuote(quote);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteQuote(@PathVariable int id){
        return quoteDAO.deleteQuote(id);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public boolean updateQuote(@RequestBody Quote quote){
        return quoteDAO.updateQuote(quote);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public List<Quote> getQuote(@PathVariable int id){
        return quoteDAO.getUserQuote(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Quote> getUser(){
        return quoteDAO.getAdminQuote();
    }
}
