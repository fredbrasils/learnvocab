package br.com.learnvocab.dao;

import br.com.learnvocab.entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.TypedQuery;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class UserDAOImpl extends BaseDAOImpl<User> implements UserDetailsService{

    @Override
    public User loadUserByUsername(String email) {
        List<User> users = getManager()
                        .createQuery("select u from User u inner join fetch u.roles where u.email = :email",
                        User.class)
                .setParameter("email", email)
                .getResultList();

        if (users.isEmpty()) {
                throw new UsernameNotFoundException("User "  + email + " não foi encontrado");
        }

        return users.get(0);
    }
    
    /* verify if exists user */
    public boolean existUserEmail(String email, Long id) {
        
        Map<String,Object> params = new HashMap<String,Object>();
        
        StringBuilder sql = new StringBuilder();
        sql.append("select u from User u inner join fetch u.roles where u.email = :email ");
        
        params.put("email", email);
        
        if(id != null){
            sql.append(" and u.id != :id ");
            params.put("id", id);
        }
        
        List<User> users = createQuery(User.class,sql.toString(), params).getResultList();

        return !users.isEmpty();
        
    }
}