package com.grupodisatel.cotizaciones.Dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class UserDAO {

    @PersistenceContext
    EntityManager entityManager;


}
