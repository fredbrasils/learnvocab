/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.learnvocab.dao;

import br.com.learnvocab.builder.UserBuilder;
import br.com.learnvocab.conf.DataSourceConfigurationTest;
import br.com.learnvocab.conf.JPAConfiguration;
import br.com.learnvocab.entity.Idiom;
import br.com.learnvocab.entity.Role;
import br.com.learnvocab.entity.User;
import br.com.learnvocab.entity.enums.RoleUser;
import br.com.learnvocab.service.RoleServiceImpl;
import br.com.learnvocab.service.UserServiceImpl;
import br.com.learnvocab.util.LearnVocabException;
import java.util.Arrays;
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
@ContextConfiguration(classes = {UserServiceImpl.class, UserDAOImpl.class,
                                 RoleServiceImpl.class, RoleDAOImpl.class,
                                 JPAConfiguration.class,DataSourceConfigurationTest.class })
@ActiveProfiles("test")
public class UserDAOTest {
    
    @Autowired 
    private UserServiceImpl userService;
    
    @Autowired 
    private RoleServiceImpl roleService;

    @Before
    @Test
    @Transactional
    public void shouldCreateRoles() throws LearnVocabException {        

        List<Role> roles = RoleUser.getAllRoles();
        
        roleService.save(roles);
        
        Assert.assertEquals(3, roleService.findAll().size());
    }
    
    @Transactional
    @Test
    public void shouldReturnUserName() throws LearnVocabException{        

        List<User> users = UserBuilder.newUser("Fred","fredbrasils@hotmail.com","$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu",
                Arrays.asList(new Role("ROLE_ADMIN"),new Role("ROLE_MODERATOR"))).buildAll();
        
        for(User user : users){
            userService.save(user);
        }        
        
        User user = userService.loadUserByUsername("fredbrasils@hotmail.com");
        
        Assert.assertEquals("Fred", user.getName());
    }
    
    @Transactional
    @Test
    public void shouldDeleteUser() throws LearnVocabException{        

        List<User> users = UserBuilder.newUser("Joao","joao@hotmail.com","$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu",
                Arrays.asList(RoleUser.ADMIN.getRole(),RoleUser.MODERATOR.getRole())).buildAll();
        
        for(User user : users){
            userService.save(user);
        }        
        
        User user = userService.loadUserByUsername("joao@hotmail.com");
        
        user = userService.find(user.getId());
                
        try{
            userService.delete(user);
            Assert.assertTrue(userService.findAll().isEmpty());        
        }catch(Exception ex){
            Assert.assertTrue(false);
        }
        
    }
    
    @Transactional
    @Test
    public void shouldUpdateUser() throws LearnVocabException{        

        List<User> users = UserBuilder.newUser("Joao","joao@hotmail.com","$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu",
                Arrays.asList(RoleUser.ADMIN.getRole(),RoleUser.MODERATOR.getRole())).buildAll();
        
        for(User user : users){
            userService.save(user);
        }        
        
        User user = userService.loadUserByUsername("joao@hotmail.com");
        
        user = userService.find(user.getId());
                
        user.setEmail("jp@hotmai.com");
        
        userService.update(user);
        
        user = userService.find(user.getId());
        
        Assert.assertEquals("jp@hotmai.com",user.getEmail());        
    }
    
    @Transactional
    @Test
    public void shouldntInsertUser() throws LearnVocabException{        

        User user = UserBuilder.newUser("Fred","fredbrasils@hotmail.com","$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu",
                Arrays.asList(new Role("ROLE_ADMIN"),new Role("ROLE_MODERATOR"))).buildOne();
        
        userService.save(user);
        
        user = UserBuilder.newUser("João","fredbrasils@hotmail.com","$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu",
                Arrays.asList(new Role("ROLE_ADMIN"))).buildOne();
        
        try{
            userService.save(user);
            Assert.assertTrue(false);
        }catch(LearnVocabException ex){
            Assert.assertEquals("user.error.exist",ex.getMessage());
        }
        
    }
    
    @Transactional
    @Test
    public void shouldntUpdateUser() throws LearnVocabException{        

        User user1 = UserBuilder.newUser("Fred","fredbrasils@hotmail.com","$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu",
                Arrays.asList(new Role("ROLE_ADMIN"),new Role("ROLE_MODERATOR"))).buildOne();
        User user2 = UserBuilder.newUser("João","joao@hotmail.com","$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu",
                Arrays.asList(new Role("ROLE_ADMIN"))).buildOne();
        
        userService.save(user1);
        userService.save(user2);
        
        try{
            user2.setEmail("fredbrasils@hotmail.com");
            userService.update(user2);
            Assert.assertTrue(false);
        }catch(LearnVocabException ex){
            Assert.assertEquals("user.error.exist",ex.getMessage());
        }
        
    }
    
}
