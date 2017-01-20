package br.com.learnvocab.controller;

import br.com.learnvocab.conf.AppWebConfiguration;
import br.com.learnvocab.conf.DataSourceConfigurationTest;
import br.com.learnvocab.conf.JPAConfiguration;
import br.com.learnvocab.dao.IdiomDAOImpl;
import br.com.learnvocab.dao.UserDAOImpl;
import br.com.learnvocab.service.IdiomServiceImpl;
import br.com.learnvocab.service.UserServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppWebConfiguration.class, JPAConfiguration.class, DataSourceConfigurationTest.class,
                                 UserServiceImpl.class,UserDAOImpl.class,
                                 IdiomServiceImpl.class,IdiomDAOImpl.class
                                 /*SpringSecurityFilterConfiguration.class*/})
@ActiveProfiles("test")
public class HomeControllerTest {

    @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mock;
    
    @Before
    public void setup(){
        this.mock = MockMvcBuilders.webAppContextSetup(wac)/*.addFilter(springSecurityFilterChain)*/.build();
    }
    
    @Test
    public void deveRetornarParaHome() throws Exception{
        mock.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("priorities"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/index.jsp"));
    }
    
    @Test
    public void deveRetornarErro() throws Exception{
        mock.perform(MockMvcRequestBuilders.get("/TESTE"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                ;
    }
    
}
