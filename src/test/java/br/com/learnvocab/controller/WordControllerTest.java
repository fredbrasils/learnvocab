package br.com.learnvocab.controller;

import br.com.learnvocab.conf.AppWebConfiguration;
import br.com.learnvocab.conf.DataSourceConfigurationTest;
import br.com.learnvocab.conf.JPAConfiguration;
import br.com.learnvocab.conf.SecurityConfiguration;
import br.com.learnvocab.conf.SpringSecurityFilterConfiguration;
import br.com.learnvocab.dao.WordDAOImpl;
import br.com.learnvocab.dao.UserDAOImpl;
import br.com.learnvocab.service.WordServiceImpl;
import br.com.learnvocab.service.UserServiceImpl;
import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
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
                                 UserServiceImpl.class, UserDAOImpl.class,WordServiceImpl.class,WordDAOImpl.class,
                                 SecurityConfiguration.class,SpringSecurityFilterConfiguration.class})
@ActiveProfiles("test")
public class WordControllerTest {

    @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mock;
    
    @Autowired    
    private Filter springSecurityFilterChain;
    
    @Before
    public void setup(){
        this.mock = MockMvcBuilders.webAppContextSetup(wac).addFilter(springSecurityFilterChain).build();
    }   
    
    @Test
    public void ShouldnotPermitAcessToRoleUSER() throws Exception{
        mock.perform(MockMvcRequestBuilders.get("/word")
                .with(SecurityMockMvcRequestPostProcessors
                        .user("admin@casadocodigo.com.br").password("123456")
                        .roles("USER")))
                .andExpect(MockMvcResultMatchers.status().is(403));
                
    }
    
    @Test
    public void onlyAdminShouldAcessForm() throws Exception{
        mock.perform(MockMvcRequestBuilders.get("/word")
                .with(SecurityMockMvcRequestPostProcessors
                        .user("admin@casadocodigo.com.br").password("123456")
                        .roles("ADMIN")))
                .andExpect(MockMvcResultMatchers.status().is(200));
                
    }    
    
    @Test
    public void shouldReturnPageWithWords() throws Exception{
        mock.perform(MockMvcRequestBuilders.get("/word")
                    .with(SecurityMockMvcRequestPostProcessors
                        .user("admin@casadocodigo.com.br").password("123456")
                        .roles("ADMIN")))
                .andExpect(MockMvcResultMatchers.model().attributeExists("words"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("priorities"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("idioms"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/word/word-list.jsp"));
    }
    
    @Test
    public void shouldDelete() throws Exception{
        mock.perform(MockMvcRequestBuilders.get("/word/deleteword/755")
                    .with(SecurityMockMvcRequestPostProcessors
                        .user("admin@casadocodigo.com.br").password("123456")
                        .roles("ADMIN")))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
