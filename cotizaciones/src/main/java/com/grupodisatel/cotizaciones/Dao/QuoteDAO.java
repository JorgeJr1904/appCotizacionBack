package com.grupodisatel.cotizaciones.Dao;

import com.grupodisatel.cotizaciones.Model.Quote;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
@Repository
public class QuoteDAO {

    @PersistenceContext
    private EntityManager entityManager;
    public boolean newQuote(Quote quote){
        try {
            entityManager.persist(quote);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean deleteQuote(int id){
        Quote quote = entityManager.find(Quote.class, id);
        try{
            if (quote == null){
                return false;
            }else {
                quote.setStatus('0');
                return true;
            }
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateQuote(Quote quote){
        try{
            Quote quote1 = entityManager.find(Quote.class, quote.getId());
            quote1.setCustomername(quote.getCustomername());
            quote1.setCustomerlastname(quote.getCustomerlastname());
            quote1.setCustomerType(quote.getCustomerType());
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public List<Quote> getAdminQuote(){
        return entityManager.createQuery("FROM Quote WHERE status = '1'").getResultList();
    }

    public List<Quote> getUserQuote(int idUser){
        String jpql = "SELECT e FROM Quote e WHERE e.user = :id AND e.status = '1'";
        Query query = entityManager.createQuery(jpql, Quote.class);
        query.setParameter("id", idUser);
        return query.getResultList();
    }
}
