package br.com.learnvocab.service;

import br.com.learnvocab.dao.RoleDAOImpl;
import br.com.learnvocab.entity.Role;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fred Brasil
 */
@Service
@Transactional
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RoleServiceImpl extends BaseServiceImpl<Role,RoleDAOImpl> implements RoleService{

    public RoleServiceImpl() {
        super(Role.class);
    }
      
    
}
