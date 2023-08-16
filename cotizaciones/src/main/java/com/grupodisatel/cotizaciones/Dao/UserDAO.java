package com.grupodisatel.cotizaciones.Dao;

import com.grupodisatel.cotizaciones.Model.Quote;
import com.grupodisatel.cotizaciones.Model.User;
import jakarta.persistence.Query;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class UserDAO {

    @PersistenceContext
    EntityManager entityManager;

    public boolean newUser(User user){
            if (!duplicateUser(user.getUserName())){
                return false;
            }else {
                user.setPassword(encryptPass(user.getPassword()));
                user.setStatus('1');
                entityManager.persist(user);
                return true;
            }
    }

    public boolean deleteUser(int id){
        try{
            User user = entityManager.find(User.class, id);
            if (user.getStatus()== '1'){
                user.setStatus('0');
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateUser(User user){
        try{
            User user1 = entityManager.find(User.class, user.getId());
            user1.setUserName(user.getUserName());
            user1.setName(user.getName());
            user1.setLastname(user.getLastname());
            user1.setIdRole(user.getIdRole());
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public List<User> getUsers(){

        return entityManager.createQuery("FROM User").getResultList();
    }

    //Verification user duplicate

    public boolean duplicateUser(String userName){
        String sql= "SELECT e.userName FROM User e WHERE e.userName = :a";
        Query query = entityManager.createQuery(sql);
        query.setParameter("a",userName);
        return query.getResultList().isEmpty();
    }




    //encryption and password verification------------------------------------------------------------------------------------------------------------------
    public String encryptPass(String pass){
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    }

    public boolean passMatch(String pass, String hashPass){
        return BCrypt.checkpw(pass, hashPass);
    }

}
