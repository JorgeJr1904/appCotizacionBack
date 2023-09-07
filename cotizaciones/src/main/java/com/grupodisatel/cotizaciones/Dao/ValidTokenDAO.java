package com.grupodisatel.cotizaciones.Dao;

import com.grupodisatel.cotizaciones.Utils.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class ValidTokenDAO {

    @Autowired
    private JWTUtil jwtUtil;

    public boolean validToken(String token){
        try{
            String userId = jwtUtil.getKey(token);
            return userId != null;
        }catch (ExpiredJwtException | MalformedJwtException ex){
            ex.printStackTrace();
            return false;
        }catch(SignatureException ex){
            return false;
        }
    }

}
