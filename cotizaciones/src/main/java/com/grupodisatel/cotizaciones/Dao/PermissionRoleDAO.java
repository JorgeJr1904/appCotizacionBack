package com.grupodisatel.cotizaciones.Dao;

import com.grupodisatel.cotizaciones.Model.Order;
import com.grupodisatel.cotizaciones.Model.PermissionsRole;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public class PermissionRoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<PermissionsRole> getAll(){
        String sql= "FROM PermissionsRole WHERE status = 1";
        return entityManager.createQuery(sql).getResultList();
    }

    public PermissionsRole getPermissionRole(int id){
        return entityManager.find(PermissionsRole.class, id);
    }

    public boolean newPermissionsRole(PermissionsRole permissionsRole){
        permissionsRole.setStatus('1');
        entityManager.persist(permissionsRole);
        return true;
    }

    public boolean updatePermissionRole(PermissionsRole permissionsRole){
        PermissionsRole permissionsRole1 = entityManager.find(PermissionsRole.class,permissionsRole.getId());
        if (permissionsRole1 == null){
            return false;
        }
        else {
            permissionsRole1.setIdRole(permissionsRole.getIdRole());
            permissionsRole1.setIdPermission(permissionsRole.getIdPermission());
            entityManager.merge(permissionsRole1);
            return true;
        }
    }

    public boolean deletePermissionsRole(int id){
        PermissionsRole permissionsRole = entityManager.find(PermissionsRole.class, id);
        if (permissionsRole == null){
            return false;
        }
        else {
            permissionsRole.setStatus('0');
            entityManager.merge(permissionsRole);
            return true;
        }
    }
}
