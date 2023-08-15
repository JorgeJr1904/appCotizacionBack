package com.grupodisatel.cotizaciones.Dao;

import com.grupodisatel.cotizaciones.Model.Price;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
<<<<<<< Updated upstream
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.SQLException;
=======
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
>>>>>>> Stashed changes

@Transactional
@Repository
public class PriceDAO {

    @PersistenceContext
    private EntityManager entityManager;

<<<<<<< Updated upstream
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
=======
    public List<Price> getPrice(){
        return entityManager.createQuery("FROM Price").getResultList();
    }

>>>>>>> Stashed changes
}
