package com.grupodisatel.cotizaciones.Dao;

import com.grupodisatel.cotizaciones.Model.Quote;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.List;

@Transactional
@Repository
public class QuoteDAO {

    @PersistenceContext
    private EntityManager entityManager;
    public boolean newPrice(Quote price){
        try {
            entityManager.persist(price);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean deletePrice(int id){
        try{
            Quote quote = entityManager.find(Quote.class, id);
            quote.setStatus('0');
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updatePrice(Quote quote){
        try{
            Quote quote1 = entityManager.find(Quote.class, quote.getId());
            quote1.setCustomername(quote.getCustomername());
            quote1.setCustomerlastname(quote.getCustomername());
            quote1.setCustomerType(quote.getCustomername());
            entityManager.merge(quote1);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public List<Quote> getAdminPrice(){
        return entityManager.createQuery("FROM Price").getResultList();
    }

    public List<Quote> getUserPrice(int id){
        String jpql = "SELECT e FROM Quote e WHERE e.user = :id";
        Query query = entityManager.createQuery(jpql, Quote.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
