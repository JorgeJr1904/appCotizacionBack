package com.grupodisatel.cotizaciones.Dao;

import com.grupodisatel.cotizaciones.Model.Order;
import com.grupodisatel.cotizaciones.Model.PermissionsRole;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public class PermissionRoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<PermissionsRole> getAll(){
        String sql= "FROM PermissionsRole WHERE status = '1'";
        return entityManager.createQuery(sql).getResultList();
    }

    public PermissionsRole getPermissionRole(int id){
        return entityManager.find(PermissionsRole.class, id);
    }

    public boolean newPermissionsRole(PermissionsRole permissionsRole){
        if (duplicatePermissionRole(permissionsRole.getIdRole(), permissionsRole.getIdPermission()) == 0){
            permissionsRole.setStatus('1');
            entityManager.persist(permissionsRole);
            return true;
        }else {
            return false;
        }
    }

    public boolean updatePermissionRole(PermissionsRole permissionsRole){
        PermissionsRole permissionsRole1 = entityManager.find(PermissionsRole.class,permissionsRole.getId());
        if (permissionsRole1 != null) {
            if (duplicatePermissionRole(permissionsRole.getIdRole(), permissionsRole.getIdPermission()) == 0) {
                try{
                    permissionsRole1.setIdRole(permissionsRole.getIdRole());
                    permissionsRole1.setIdPermission(permissionsRole.getIdPermission());
                    return true;
                }catch (Exception e){
                    return false;
                }
            }
        }
        return false;
    }

    public boolean deletePermissionsRole(int id){
        PermissionsRole permissionsRole = entityManager.find(PermissionsRole.class, id);
        if (permissionsRole == null){
            return false;
        }
        else {
            permissionsRole.setStatus('0');
            return true;
        }
    }


    public int duplicatePermissionRole(int idRole, int idPermission){
        String jpql = "SELECT count(*) FROM PermissionsRole e WHERE e.idRole = :role AND e.idPermission = :permission";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("role", idRole);
        query.setParameter("permission", idPermission);
        long count = (long) query.getSingleResult();
        return (int) count;
    }
}
