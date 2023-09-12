package com.grupodisatel.cotizaciones.Dao;

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
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String updateUser(int id, User user){
        try{
            User user1 = entityManager.find(User.class, id);
            if (!Objects.equals(user.getUserName(), user1.getUserName())){
                if (!duplicateUser(user.getUserName())){
                    return "user already exists";
                }else {
                    user1.setUserName(user.getUserName());
                    user1.setName(user.getName());
                    user1.setLastname(user.getLastname());
                    user1.setIdRole(user.getIdRole());
                    return "true";
                }
            }else {
                user1.setUserName(user.getUserName());
                user1.setName(user.getName());
                user1.setLastname(user.getLastname());
                user1.setIdRole(user.getIdRole());
                return "true";
            }

        }catch (Exception e){
            return "false";
        }
    }
    public List<User> getUsers(){

        return entityManager.createQuery("SELECT e FROM User e ORDER BY e.id").getResultList();
    }

    public User getOnlyUser(int id){
        Query query = entityManager.createQuery("SELECT e.name, e.lastname, e.userName, e.idRole FROM User e WHERE e.id = :id");
        query.setParameter("id", id);
        Object[] userData = (Object[]) query.getSingleResult();
        return new User(userData[0].toString(), userData[1].toString(), userData[2].toString(), (Integer) userData[3]);
    }

    public boolean updatePasswordAdmin(int id, String password){
        try {
            User user = entityManager.find(User.class, id);
            if (!securePass(user.getPassword())){
                return false;
            }
            else {
                user.setPassword(encryptPass(password));
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //Finish CRUD ------------------------------------------------------------------------------------------------------

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

}
