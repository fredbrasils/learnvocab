package br.com.learnvocab.dao;

import br.com.learnvocab.entity.Role;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RoleDAOImpl extends BaseDAOImpl<Role>{

    
    
}
