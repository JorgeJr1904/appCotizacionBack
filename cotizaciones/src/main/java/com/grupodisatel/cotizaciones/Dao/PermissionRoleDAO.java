package com.grupodisatel.cotizaciones.Dao;

import com.grupodisatel.cotizaciones.DTO.PermissionsRoleDTO;
import com.grupodisatel.cotizaciones.Model.Permission;
import com.grupodisatel.cotizaciones.Model.PermissionsRole;
import com.grupodisatel.cotizaciones.Model.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public class PermissionRoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private RoleDAO roleDAO;

    public List<PermissionsRole> getAll(){
        String sql= "FROM PermissionsRole WHERE status = '1'";
        return entityManager.createQuery(sql).getResultList();
    }

    public PermissionsRoleDTO getPermissionRole(int idRole){
        int count = 0;
        List<PermissionsRole> permissionsRoles = getPermissionsRole(idRole);
        PermissionsRoleDTO permissionsRoleDTO = new PermissionsRoleDTO();
        int[] idPermissions = new int[permissionsRoles.size()];
        for (PermissionsRole permissionRole:permissionsRoles) {
            idPermissions[count] = permissionRole.getIdPermission();
            count++;
        }
        permissionsRoleDTO.setIdPermission(idPermissions);
        return permissionsRoleDTO;
    }

    public String newPermissionsRole(PermissionsRoleDTO permissionsRoleDTO){
        Role role = new Role();
        role.setRoleName(permissionsRoleDTO.getRoleName());
        if (roleDAO.existRole(role.getRoleName())){
            try{
                int[] idPermission = permissionsRoleDTO.getIdPermission();
                int idRole = roleDAO.createRole(role);
                if (idPermission.length > 0){
                    for (int j : idPermission) {
                        PermissionsRole permissionRole = new PermissionsRole();
                        permissionRole.setIdRole(idRole);
                        permissionRole.setIdPermission(j);
                        permissionRole.setStatus('1');
                        entityManager.persist(permissionRole);
                    }
                    return "true";
                }else {
                    return "null Permissions";
                }

            }catch (Exception e){
                e.printStackTrace();
                return "false";
            }
        }else {
            return "Existent Role";
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

    public List<PermissionsRole> getPermissionsRole(int idRole){
        String jpql = "FROM PermissionsRole WHERE idRole = :role";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("role", idRole);
        return query.getResultList();
    }
}
