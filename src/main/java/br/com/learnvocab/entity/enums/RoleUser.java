package br.com.learnvocab.entity.enums;

import br.com.learnvocab.entity.Role;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public enum RoleUser {
    ADMIN(new Role(1l,"ROLE_ADMIN")),
    MODERATOR(new Role(2l,"ROLE_MODERATOR")),
    USER(new Role(3l,"ROLE_USER"));
    
    private Role role;

    private RoleUser(Role role) {
        this.role = role;
    }    

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static RoleUser getRoleUserByRole(Role role){
        
        for(RoleUser ru : values()){
            if(ru.getRole().equals(role)){
                return ru;
            }
        }
        
        return null;
    }
    
    public static List<Role> getAllRoles(){
        
        List<Role> roles = new ArrayList<Role>();
        
        for(RoleUser ru : values()){
            roles.add(ru.getRole());
        }
        
        return roles;
    }
    
    @Override
    public String toString() {
        return this.name();
    }
    
}
