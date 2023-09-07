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

    // CRUD -------------------------------------------------------------------------------------------------------------
    public List<Role> getRoles(){
        String sql = "FROM Role WHERE status = '1'";
        return entityManager.createQuery(sql).getResultList();
    }

    public int createRole(Role role){
            if (existRole(role.getRoleName())) {
                role.setStatus('1');
                entityManager.persist(role);
                return role.getIdRole();
            }
        return 0;
    }

    public boolean deleteRole(int id){
        try {
            if (existRole(id) == null){
                return false;
            }else {
                Role role = entityManager.find(Role.class, id);
                role.setStatus('0');
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
            if (!CoincidenceRoles(id, role.getRoleName())){
                return false;
            }
            else {
                Role existingRole = existRole(id);
                existingRole.setRoleName(role.getRoleName());
                return true;
            }

        }catch (Exception e){
            return false;
        }

    }
    //Finish CRUD ------------------------------------------------------------------------------------------------------

    public Role existRole(int id){
        return entityManager.find(Role.class, id);
    }

    public boolean existRole(String role){
        String jpql = "SELECT e FROM Role e WHERE e.roleName = :rolName";
        Query query = entityManager.createQuery(jpql, Role.class);
        query.setParameter("rolName", role);
        return query.getResultList().isEmpty();
    }

    public List<Integer> existingRoles(String role){
        String jpql = "SELECT e.idRole FROM Role e WHERE e.roleName = :rolName";
        Query query = entityManager.createQuery(jpql, Role.class);
        query.setParameter("rolName", role);
        return query.getResultList();
    }

    public boolean CoincidenceRoles(Integer id, String role){
        List<Integer> idRole = existingRoles(role);
        int listSize = idRole.size();
        for (int idrole : idRole){
            if (idrole == id){
                listSize--;
            }
        }
        return listSize == 0;
    }

}
