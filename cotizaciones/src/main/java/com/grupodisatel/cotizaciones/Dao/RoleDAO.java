package com.grupodisatel.cotizaciones.Dao;

import com.grupodisatel.cotizaciones.Model.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public class RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Role> getRoles(){
        String sql = "FROM Role";
        return entityManager.createQuery(sql).getResultList();
    }

    public boolean createRole(Role role){
            if (existRole(role.getRoleName(), role.getKeyWord())) {
                entityManager.persist(role);
                return true;
            }
        return false;
    }

    public boolean deleteRole(int id){
        try {
            if (existRole(id) == null){
                return false;
            }else {
                entityManager.remove(existRole(id));
                return true;
            }

        }catch (Exception e){
            return false;
        }
    }

    public boolean updateRole(int id, Role role){
        try {
            if (existRole(id) == null){
                return false;
            }
            if (!CoincidenceRoles(id, role.getRoleName(), role.getKeyWord())){
                return false;
            }
            else {
                Role existingRole = existRole(id);
                existingRole.setRoleName(role.getRoleName());
                existingRole.setKeyWord(role.getKeyWord());
                entityManager.merge(existingRole);
                return true;
            }

        }catch (Exception e){
            return false;
        }

    }

    public Role existRole(int id){
        return entityManager.find(Role.class, id);
    }

    public boolean existRole(String role, String key){
        String jpql = "SELECT e FROM Role e WHERE e.roleName = :rolName OR e.keyWord = :keyWord";
        Query query = entityManager.createQuery(jpql, Role.class);
        query.setParameter("rolName", role);
        query.setParameter("keyWord", key);
        return query.getResultList().isEmpty();
    }

    public List<Integer> existingRoles(String role, String key){
        String jpql = "SELECT e.idRole FROM Role e WHERE e.roleName = :rolName OR e.keyWord = :keyWord";
        Query query = entityManager.createQuery(jpql, Role.class);
        query.setParameter("rolName", role);
        query.setParameter("keyWord", key);
        return query.getResultList();
    }

    public boolean CoincidenceRoles(Integer id, String role, String key){
        List<Integer> idRole = existingRoles(role, key);
        int listSize = idRole.size();
        for (int idrole : idRole){
            if (idrole == id){
                listSize--;
            }
        }
        return listSize == 0;
    }

}
