package com.grupodisatel.cotizaciones.Dao;

import com.grupodisatel.cotizaciones.Model.Price;
import com.grupodisatel.cotizaciones.Model.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.List;

@Transactional
@Repository
public class PriceDAO {

    @PersistenceContext
    private EntityManager entityManager;
    public boolean newPrice(Price price){
        try {
            entityManager.persist(price);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean deletePrice(int id){
        try{
            Price price = entityManager.find(Price.class, id);
            price.setStatus('0');
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updatePrice(int id){
        try{
            Price price = entityManager.find(Price.class, id);
            price.setStatus('0');
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public List<Price> getAdminPrice(){
        return entityManager.createQuery("FROM Price").getResultList();
    }

    public List<Price> getUserPrice(int id){
        String jpql = "SELECT e FROM Price e WHERE e.user = :id";
        Query query = entityManager.createQuery(jpql, Price.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
