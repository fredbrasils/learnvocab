/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.learnvocab.dao;

import br.com.learnvocab.conf.DataSourceConfigurationTest;
import br.com.learnvocab.conf.JPAConfiguration;
import br.com.learnvocab.entity.Role;
import br.com.learnvocab.entity.enums.RoleUser;
import br.com.learnvocab.service.RoleServiceImpl;
import br.com.learnvocab.util.LearnVocabException;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Fred
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RoleServiceImpl.class, RoleDAOImpl.class,
                                 JPAConfiguration.class,DataSourceConfigurationTest.class })
@ActiveProfiles("test")
public class RoleDAOTest {
    
    @Autowired
    private RoleServiceImpl roleService;
    
    @Test
    @Transactional
    public void shouldCreateRoles() throws LearnVocabException {        

        List<Role> roles = RoleUser.getAllRoles();
             
        roleService.save(roles);
    
        Assert.assertEquals(3, roleService.findAll().size());
    }
    
}
