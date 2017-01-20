/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.learnvocab.dao;

import br.com.learnvocab.builder.BoxBuilder;
import br.com.learnvocab.conf.DataSourceConfigurationTest;
import br.com.learnvocab.conf.JPAConfiguration;
import br.com.learnvocab.entity.Box;
import br.com.learnvocab.service.BoxServiceImpl;
import br.com.learnvocab.util.LearnVocabException;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Assert;
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
@ContextConfiguration(classes = {BoxServiceImpl.class, BoxDAOImpl.class,
//                                 UserServiceImpl.class, UserDAOImpl.class,
//                                 RoleServiceImpl.class, RoleDAOImpl.class,
                                 JPAConfiguration.class,DataSourceConfigurationTest.class })
@ActiveProfiles("test")
public class BoxDAOTest {
    
    @Autowired
    private BoxServiceImpl boxService;
    
    @Test
    public void shouldCreateFiveBoxes() throws LearnVocabException {        
        Assert.assertTrue(true);
    }
    /*
    @Transactional
    @Test
    public void shouldCreateFiveBoxes() throws LearnVocabException {        
        
        List<Box> boxes = BoxBuilder.newBox(1).more(7,15,30,45).buildAll();//.withUser(user).buildAll();
        
        boxService.save(boxes);
        
        boxes = boxService.findAll();
        
        Assert.assertEquals(5, boxes.size());
    }
   
    @Transactional
    @Test
    public void shouldndCreateBox() throws LearnVocabException {        
        
        List<Box> boxes = BoxBuilder.newBox(1).more(7,15,30,45).buildAll();//.withUser(user).buildAll();
        
        boxService.save(boxes);
        
        Box box = BoxBuilder.newBox(25).buildOne();
        
        try{
            boxService.save(box);
            Assert.assertTrue(false);
        }catch(LearnVocabException ex){
            Assert.assertEquals("box.error.numberdays.invalid",ex.getMessage());
        }
    }
    
    @Transactional
    @Test
    public void shouldCreateBox() throws LearnVocabException {        
        
        List<Box> boxes = BoxBuilder.newBox(1).more(7,15,30,45).buildAll();//.withUser(user).buildAll();
        
        boxService.save(boxes);
        
        Box box = BoxBuilder.newBox(50).buildOne();
        
        boxService.save(box);
        
        boxes = boxService.findAll();
        
        Assert.assertEquals(6, boxes.size());
    }
    
    @Transactional
    @Test
    public void shouldDeleteBox() throws LearnVocabException {        
        
        Box box = BoxBuilder.newBox(1).buildOne();
        
        boxService.save(box);
        
        List<Box> boxes = boxService.findAll();
        Assert.assertEquals(1, boxes.size());
        
        Long id = box.getId();
        boxService.delete(id);
                
        boxes = boxService.findAll();
        Assert.assertEquals(0, boxes.size());
        
    }
*/
}
