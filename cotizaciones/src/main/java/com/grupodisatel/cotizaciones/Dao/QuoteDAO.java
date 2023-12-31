package com.grupodisatel.cotizaciones.Dao;

import com.grupodisatel.cotizaciones.DTO.PermissionsRoleDTO;
import com.grupodisatel.cotizaciones.Model.Quote;
import com.grupodisatel.cotizaciones.Utils.JWTUtil;
import com.grupodisatel.cotizaciones.Validation.UserRoleValidation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
@Repository
public class QuoteDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRoleValidation userRoleValidation;
    public Quote newQuote(Quote quote, String token){
        String userId = jwtUtil.getKey(token);
        if (userRoleValidation.validatePermission(token, 1)){
            try {
                quote.setUser(Integer.parseInt(userId));
                quote.setStatus('1');
                entityManager.persist(quote);
                return quote;
            }catch (Exception ex){
                ex.printStackTrace();
                return null;
            }
        }
        return null;
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
            quote1.setCustomerName(quote.getCustomerName());
            quote1.setCustomerLastname(quote.getCustomerLastname());
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
        String jpql = "SELECT e FROM Quote e WHERE e.user = :id AND e.status = '1' ORDER BY quoteDate DESC";
        Query query = entityManager.createQuery(jpql, Quote.class);
        query.setParameter("id", idUser);
        return query.getResultList();
    }

    public Quote getQuote(int id){
        String jpql = "SELECT e FROM Quote e WHERE e.id = :id";
        Query query = entityManager.createQuery(jpql, Quote.class);
        query.setParameter("id", id);
        return (Quote) query.getSingleResult();
    }
}
