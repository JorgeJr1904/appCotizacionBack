package com.grupodisatel.cotizaciones.RESTController;

import com.grupodisatel.cotizaciones.Dao.OrderDAO;
import com.grupodisatel.cotizaciones.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order/v1")
public class OrderController {

    @Autowired
    private OrderDAO orderDAO;

    @PostMapping(value = "new")
    public boolean newOrder(@RequestBody Order order){
        System.out.println(order.getDescription());
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
