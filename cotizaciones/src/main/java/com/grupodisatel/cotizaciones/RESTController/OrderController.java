package com.grupodisatel.cotizaciones.RESTController;

import com.grupodisatel.cotizaciones.Dao.OrderDAO;
import com.grupodisatel.cotizaciones.Model.Order;
import com.grupodisatel.cotizaciones.Utils.JWTUtil;
import com.grupodisatel.cotizaciones.Validation.UserRoleValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order/v1")
public class OrderController {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private UserRoleValidation userRoleValidation;

    @PostMapping(value = "new")
    public boolean newOrder(@RequestBody Order order){
        return orderDAO.newOrder(order);
    }
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public boolean deleteOrder(@PathVariable int id){
        return orderDAO.deleteOrder(id);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public boolean updateOrder(@RequestBody Order order){
        return orderDAO.updateOrder(order);
    }

    @GetMapping(value = "get/{id}")
    public List<Order> getQuoteOrders(@PathVariable int id, @RequestHeader(value = "Authorization") String token){
        if (userRoleValidation.validatePermission(token, 2)){
                return orderDAO.getQuoteOrders(id);
        }
        return null;
    }
}
