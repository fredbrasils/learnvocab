package br.com.learnvocab.service;

import br.com.learnvocab.dao.UserDAOImpl;
import br.com.learnvocab.entity.Role;
import br.com.learnvocab.entity.User;
import br.com.learnvocab.util.LearnVocabException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
@Transactional
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class UserServiceImpl extends BaseServiceImpl<User,UserDAOImpl> implements UserService{

    @Autowired
    private RoleServiceImpl  roleService;
    
    public UserServiceImpl() {
        super(User.class);
    }

    @Override
    public User loadUserByUsername(String email) {
        return getDao().loadUserByUsername(email);
    }    

    @Override
    public void save(User user) throws LearnVocabException {
                
        // if exists user
        if(getDao().existUserEmail(user.getEmail(), null)){             
            throw new LearnVocabException(LearnVocabException.ERROR_RULE, "user.error.exist", new Object[]{user.getEmail()});
        }
        
        // update user's roles
        user.setRoles(getRoles(user));
        
        super.save(user);
    }

    @Override
    public User update(User user) throws LearnVocabException {
        
        // if exists user
        if(getDao().existUserEmail(user.getEmail(), user.getId())){             
            throw new LearnVocabException(LearnVocabException.ERROR_RULE, "user.error.exist", new Object[]{user.getEmail()});
        }
        
        // update user's roles
        user.setRoles(getRoles(user));
        
        return super.update(user);
    }

    // return Roles from database
    private List<Role> getRoles(User user){
        
        // get all roles
        List<Role> rolesAux = roleService.findAll();
        List<Role> roles = new ArrayList<>();
        
        // get the roles from the database
        for(Role roleUser : user.getRoles()){
            for(Role role : rolesAux){
                if(roleUser.getName().equals(role.getName())){
                    roles.add(role);
                }
            }
        }
        
        return roles;
    }
    
    @Override
    public void delete(Long id) throws LearnVocabException {
        
        // get the user
        User user = find(id);
        
        // remove the roles associated to user
        user.setRoles(null);
        
        // remove user
        super.delete(user);
    }
    
    
    
}
