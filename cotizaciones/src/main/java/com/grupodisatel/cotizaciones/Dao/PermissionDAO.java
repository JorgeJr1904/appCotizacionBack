package com.grupodisatel.cotizaciones.Dao;

import com.grupodisatel.cotizaciones.Model.Permission;
import com.grupodisatel.cotizaciones.Model.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;

@Transactional
@Repository
public class PermissionDAO {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Permission> getPermissions(){
        String query = "FROM Permission";
        return entityManager.createQuery(query).getResultList();
    }

    public boolean createPermission(Permission permission){
        if (existPermission(permission.getPermissionName())){
            entityManager.persist(permission);
            return true;
        }
        return false;
    }

    public boolean deletePermission(int id){
        try{
            Permission permission = findPermission(id);
            entityManager.remove(permission);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updatePermision(int id, Permission permission){
        try{
            if (!existPermission(findPermission(id).getPermissionName())){
                Permission permissionDB = findPermission(id);
                permissionDB.setPermissionName(permission.getPermissionName());
                entityManager.merge(permissionDB);
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;

        }


    //This Methods will be used for RestMethods ------------------------------------------------------------------------------------------------

    //This method find if the user try to create new permission name and it already exists
    public boolean existPermission(String permissionName){
        String jpql = "SELECT e FROM Permission e WHERE e.permissionName = :name";
        Query query = entityManager.createQuery(jpql, Role.class);
        query.setParameter("name", permissionName);
        return query.getResultList().isEmpty();
    }

    //this method only find the record in the db with the id
    public Permission findPermission(int id){
        return entityManager.find(Permission.class, id);
    }
}
