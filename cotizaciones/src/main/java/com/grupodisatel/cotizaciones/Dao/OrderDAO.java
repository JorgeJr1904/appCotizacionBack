package com.grupodisatel.cotizaciones.Dao;

import com.grupodisatel.cotizaciones.Model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@Repository
public class OrderDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private QuoteDAO quoteDAO;

    public List<Order> getAll(){
        String sql= "FROM Order WHERE status = '1'";
        return entityManager.createQuery(sql).getResultList();
    }

    public Order getOrder(int id){
        return entityManager.find(Order.class, id);
    }

    public boolean newOrder(Order order){
        try{
            order.setStatus('1');
            entityManager.persist(order);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            quoteDAO.deleteQuote(order.getIdQuote());
            return false;
        }
    }

    public boolean updateOrder(Order order){
        Order order1 = entityManager.find(Order.class,order.getId());
        if (order1 == null){
            return false;
        }
        else {
            order1.setDescription(order.getDescription());
            order1.setMen(order.getMen());
            order1.setHourPrice(order.getHourPrice());
            order1.setHourDays(order.getHourDays());
            order1.setDays(order.getDays());
            order1.setTotalPrice(totalPriceOrder(order));
            entityManager.merge(order1);
            return true;
        }
    }

    public boolean deleteOrder(int id){
        Order order1 = entityManager.find(Order.class, id);
        if (order1 == null){
            return false;
        }
        else {
            order1.setStatus('0');
            entityManager.merge(order1);
            return true;
        }
    }


    public BigDecimal totalPriceOrder(Order order){
        return BigDecimal.valueOf(order.getMen()).multiply(order.getHourPrice()).multiply(BigDecimal.valueOf(order.getHourDays()).multiply(BigDecimal.valueOf(order.getDays())));
    }

}
