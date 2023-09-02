package com.grupodisatel.cotizaciones.Dao;

import com.grupodisatel.cotizaciones.Model.Login;
import com.grupodisatel.cotizaciones.Model.User;
import com.grupodisatel.cotizaciones.Utils.JWTUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public class LoginDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JWTUtil jwtUtil;

    public Login login(String userName, String password){
        User user = findUser(userName, password);
        Login login = new Login();
        if (user == null) {
            login.setMessage("Nombre de Usuario Incorrecto");
            return login;
        }else {
            if (user.getStatus() == '0'){
                login.setMessage("Usuario Deshabilitado");
                return login;
            }
            if (passMatch(password, user.getPassword())){
                String JWTtoken = jwtUtil.create(String.valueOf(user.getId()), user.getUserName(), user.getIdRole());
                return new Login("Login Correcto", user.getUserName(), user.getIdRole(), JWTtoken);
            }
            else {
                login.setMessage("Contrasenia Incorrecta");
                return login;
            }
        }
    }


    public boolean passMatch(String pass, String hashPass){
        return BCrypt.checkpw(pass, hashPass);
    }

    public User findUser(String userName, String password){
        String sql = "SELECT e FROM User e WHERE e.userName = :userName";
        Query query = entityManager.createQuery(sql);
        query.setParameter("userName", userName);
        try{
            return (User) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

}
