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
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Transactional
@Repository
public class UserDAO {

    @PersistenceContext
    EntityManager entityManager;

    //Starts CRUD ------------------------------------------------------------------------------------------------------
    public String newUser(User user){
            if (!duplicateUser(user.getUserName())){
                return "user already exists";
            } else if (!securePass(user.getPassword())) {
                return "not secure password";
            } else {
                user.setPassword(encryptPass(user.getPassword()));
                user.setStatus('1');
                entityManager.persist(user);
                return "true";
            }
    }

    public boolean deleteUser(int id){
        try{
            User user = entityManager.find(User.class, id);
            entityManager.remove(user);
        }catch (Exception e){
            return false;
        }
            return false;
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

        return entityManager.createQuery("SELECT e FROM User e ORDER BY e.id").getResultList();
    }

    public User getOnlyUser(int id){
        Query query = entityManager.createQuery("SELECT e FROM User e WHERE id = :id");
        query.setParameter("id", id);
        User user = (User) query.getSingleResult();
        user.setPassword("");
        return user;
    }

    //Finish CRUD ------------------------------------------------------------------------------------------------------

    public Object findRoleUser(Object username){
        System.out.println(username);
        String jpql = "SELECT e.idRole FROM User e WHERE userName = :username";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    //this method enable or disable the user account
    public String d_activateUser(int id){
        try{
            User user = entityManager.find(User.class, id);
            if (user.getStatus()== '1'){
                user.setStatus('0');
                return "user disable";
            }if (user.getStatus()== '0') {
                user.setStatus('1');
                return "user enable";
            }else {
                return "false";
            }
        }catch (Exception e){
            return "error";
        }
    }

    //Verification user duplicate

    public boolean duplicateUser(String userName){
        String sql= "SELECT e.userName FROM User e WHERE e.userName = :a";
        Query query = entityManager.createQuery(sql);
        query.setParameter("a",userName);
        return query.getResultList().isEmpty();
    }

    //Verification requisits of security password
    public static boolean securePass(String pass){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&_ -])[A-Za-z\\d@$!%*?&_ -]{8,}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pass);
        
        return matcher.matches();
    }




    //encryption and password verification------------------------------------------------------------------------------------------------------------------
    public String encryptPass(String pass){
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    }

    public boolean passMatch(String pass, String hashPass){
        return BCrypt.checkpw(pass, hashPass);
    }

}
