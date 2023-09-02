package com.grupodisatel.cotizaciones.Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Login extends User {

    @Getter @Setter
    private String message;

    @Getter @Setter
    private String token;

    public Login(){
        super();
    }
    public Login(String status, String userName, int idRole, String token){
        super(userName, idRole);
        this.message = status;
        this.token = token;
    }

}
