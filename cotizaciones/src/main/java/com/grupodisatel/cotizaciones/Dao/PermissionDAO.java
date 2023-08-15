package com.grupodisatel.cotizaciones.Dao;

import com.grupodisatel.cotizaciones.Model.Permission;
import com.grupodisatel.cotizaciones.Model.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Transactional
@Repository
public class PermissionDAO {

    @PersistenceContext
    private EntityManager entityManager;

<<<<<<< Updated upstream
    public List<Permission> getPermissions(){
=======
    //This are RESTMethods

    public List<Permission> permissionsName(){
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
            Permission permission = findPermission(id);
=======
            Permission permission = entityManager.find(Permission.class, id);
>>>>>>> Stashed changes
            entityManager.remove(permission);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updatePermision(int id, Permission permission){
        try{
<<<<<<< Updated upstream
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
=======

        }
>>>>>>> Stashed changes
    }


    //This Methods will be used for RestMethods ------------------------------------------------------------------------------------------------
<<<<<<< Updated upstream

    //This method find if the user try to create new permission name and it already exists
=======
>>>>>>> Stashed changes
    public boolean existPermission(String permissionName){
        String jpql = "SELECT e FROM Permission e WHERE e.permissionName = :name";
        Query query = entityManager.createQuery(jpql, Role.class);
        query.setParameter("name", permissionName);
        return query.getResultList().isEmpty();
    }
<<<<<<< Updated upstream

    //this method only find the record in the db with the id
    public Permission findPermission(int id){
        return entityManager.find(Permission.class, id);
    }
=======
>>>>>>> Stashed changes
}
