package com.grupodisatel.cotizaciones.Dao;

import com.grupodisatel.cotizaciones.Model.Price;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.SQLException;

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
            entityManager.remove(price);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
