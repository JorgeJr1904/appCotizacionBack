package com.grupodisatel.cotizaciones.RESTController;

import com.grupodisatel.cotizaciones.Dao.OrderDAO;
import com.grupodisatel.cotizaciones.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderDAO orderDAO;

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public boolean newOrder(@RequestBody Order order){
        return orderDAO.newOrder(order);
    }
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteUser(@PathVariable int id){
        return orderDAO.deleteOrder(id);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody Order order){
        return orderDAO.updateOrder(order);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Order getUser(@PathVariable int id){
        return orderDAO.getOrder(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Order> getUser(){
        return orderDAO.getAll();
    }
}
