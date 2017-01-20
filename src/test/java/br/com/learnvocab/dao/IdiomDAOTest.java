/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.learnvocab.dao;

import br.com.learnvocab.builder.IdiomBuilder;
import br.com.learnvocab.builder.WordBuilder;
import br.com.learnvocab.conf.DataSourceConfigurationTest;
import br.com.learnvocab.conf.JPAConfiguration;
import br.com.learnvocab.entity.Idiom;
import br.com.learnvocab.entity.Word;
import br.com.learnvocab.entity.enums.WordPriority;
import br.com.learnvocab.service.IdiomServiceImpl;
import br.com.learnvocab.service.WordServiceImpl;
import br.com.learnvocab.util.LearnVocabException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@ContextConfiguration(classes = {IdiomServiceImpl.class, IdiomDAOImpl.class,
                                 WordServiceImpl.class, WordDAOImpl.class,
                                 JPAConfiguration.class,DataSourceConfigurationTest.class })
@ActiveProfiles("test")
public class IdiomDAOTest {
    
    @Autowired
    private IdiomServiceImpl idiomService;

    @Autowired
    private WordServiceImpl wordService;
    
    @Before
    @Test
    @Transactional
    public void shouldCleanDataBase() throws LearnVocabException {        

        List<Idiom> idioms = idiomService.findAll();
        idiomService.delete(idioms);
        idioms = idiomService.findAll();
        
        Assert.assertTrue(idioms.isEmpty());
    }
    
    @Transactional
    @Test
    public void shouldReturnThreeIdioms() throws LearnVocabException {        

        List<Idiom> idioms = IdiomBuilder.newIdiom("English").more("France","Portuguese").buildAll();
        
        for(Idiom idiom : idioms){
            idiomService.save(idiom);
        }        
        
        idioms = idiomService.findAll();
        
        Assert.assertEquals(3, idioms.size());
    }
    
    @Transactional
    @Test
    public void shouldNotReturnThreeIdioms() throws LearnVocabException{        

        List<Idiom> idioms = IdiomBuilder.newIdiom("English").more("France").buildAll();
        
        for(Idiom idiom : idioms){
            idiomService.save(idiom);
        }        
        
        idioms = idiomService.findAll();
        
        Assert.assertNotEquals(3, idioms.size());
        Assert.assertEquals(2, idioms.size());
    }
    
    @Transactional
    @Test
    public void shouldReturnTheIdiomEnglish() throws LearnVocabException{        

        List<Idiom> idioms = IdiomBuilder.newIdiom("English").more("France").buildAll();
        
        Long englishId = null;
        
        for(Idiom idiom : idioms){
            idiomService.save(idiom);
            
            if(idiom.getName().equals("English")){
                englishId = idiom.getId();
            }
        }        
        
        Idiom idiom = idiomService.find(englishId);
        
        Assert.assertEquals("English", idiom.getName());
    }
    
    @Transactional
    @Test
    public void shouldReturnTheIdiomFrance() throws LearnVocabException{        

        List<Idiom> idioms = IdiomBuilder.newIdiom("English").more("France").buildAll();
        
        for(Idiom idiom : idioms){
            idiomService.save(idiom);
        }        
        
        Idiom idiom = null;
        try {
            idiom = idiomService.find("France");
        } catch (Exception ex) {
            Logger.getLogger(IdiomDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Assert.assertEquals("France", idiom.getName());
    }
    
    @Transactional
    @Test
    public void shouldInsertThreeIdiomsAndRemoveTwoOfThem() throws LearnVocabException{        

        List<Idiom> idioms = IdiomBuilder.newIdiom("English").more("France","Portuguese").buildAll();
        
        Long englishId = null;
        Long franceId = null;
        
        for(Idiom idiom : idioms){
            idiomService.save(idiom);
            
            if(idiom.getName().equals("English")){
                englishId = idiom.getId();
            }else if(idiom.getName().equals("France")){
                franceId = idiom.getId();
            }
        }        
        
        idioms = new ArrayList<Idiom>();
        idioms.add(idiomService.find(englishId));
        idioms.add(idiomService.find(franceId));
        
        idiomService.delete(idioms);
        
        idioms = idiomService.findAll();
        
        Assert.assertEquals(1, idioms.size());
    }
    
    @Transactional
    @Test
    public void shouldInsertThreeIdiomsAndRemoveOneOfThem() throws LearnVocabException{        

        List<Idiom> idioms = IdiomBuilder.newIdiom("English").more("France","Portuguese").buildAll();
        
        Idiom englishId = null;
        
        for(Idiom idiom : idioms){
            idiomService.save(idiom);
            
            if(idiom.getName().equals("English")){
                englishId = idiom;
            }
        }        
        
        idiomService.delete(englishId);
        
        idioms = idiomService.findAll();
        
        Assert.assertEquals(2, idioms.size());
    }
    
    @Transactional
    @Test
    public void shouldInsertThreeIdioms() throws LearnVocabException{        

        List<Idiom> idioms = IdiomBuilder.newIdiom("English").more("France","Portuguese").buildAll();
        idiomService.save(idioms);
        
        idioms = idiomService.findAll();
        
        Assert.assertEquals(3, idioms.size());
    }
    
    @Transactional
    @Test
    public void shouldUpdateIdioms() throws LearnVocabException{        

        List<Idiom> idioms = IdiomBuilder.newIdiom("English").more("France","Portuguese").buildAll();
        idiomService.save(idioms);
        
        idioms = idiomService.findAll();
        
        for(Idiom id : idioms){
            id.setName(id.getName().concat("-new"));
        }
        
        idiomService.update(idioms);
        
        idioms = idiomService.findAll();
        
        for(Idiom id : idioms){
            Assert.assertTrue(id.getName().contains("-new"));
        }
        
    }
    @Transactional
    @Test
    public void shouldUpdateIdiomEnglish() throws LearnVocabException{        

        List<Idiom> idioms = IdiomBuilder.newIdiom("English").more("France","Portuguese").buildAll();
        idiomService.save(idioms);
        
        idioms = idiomService.findAll();
        
        Idiom englishId = null;
        
        for(Idiom idiom : idioms){            
            
            if(idiom.getName().equals("English")){
                englishId = idiom;
            }
        }
        
        englishId.setName("newEnglish");
        
        englishId = idiomService.update(englishId);
                
        Assert.assertTrue(englishId.getName().equals("newEnglish"));
    }
    
    @Test
    @Transactional
    public void shouldnotUpdateIdiomEnglish() throws LearnVocabException{        

        List<Idiom> idioms = IdiomBuilder.newIdiom("Idiom1").more("Idiom2","Idiom3").buildAll();
        idiomService.save(idioms);
        
        idioms = idiomService.findAll();
        
        Idiom englishId = null;
        
        for(Idiom idiom : idioms){            
            
            if(idiom.getName().equals("Idiom1")){
                englishId = idiom;
            }
        }
        
        englishId.setName("Idiom2");
        try {
            idiomService.update(englishId);            
            Assert.assertTrue(false);
        } catch (LearnVocabException ex) {
            Assert.assertTrue(ex.getMessage().equals("idiom.error.exist"));
        }       
    }
    
    @Transactional
    @Test
    public void shouldntInsertIdiom() throws LearnVocabException{        

        Idiom idiom = IdiomBuilder.newIdiom("English").buildOne();
        idiomService.save(idiom);
        
        Idiom idiomAux = IdiomBuilder.newIdiom("English").buildOne();
        
       try {
            idiomService.save(idiomAux);            
            Assert.assertTrue(false);
        } catch (LearnVocabException ex) {
            Assert.assertTrue(ex.getMessage().equals("idiom.error.exist"));
        } 
        
    }
    
    @Transactional
    @Test
    public void shouldntDeleteEnglishWithWordsAssociated() throws LearnVocabException, Exception {        
        
        List<Idiom> idioms = IdiomBuilder.newIdiom("English").more("France","Portuguese").buildAll();
        idiomService.save(idioms);
        
        Idiom englishId = null;
        
        for(Idiom idiom : idioms){            
            
            if(idiom.getName().equals("English")){
                englishId = idiom;
            }
        }
        
        Word word1 = WordBuilder.newWord("Hello").fromIdiom(englishId).withPriority(WordPriority.HIGHT).build();
        wordService.save(word1);        
        List<Word> list = new ArrayList<Word>();
        list.add(word1);
        englishId.setWords(list);
        
        try {
            idiomService.delete(englishId.getId());            
            Assert.assertTrue(false);
        } catch (LearnVocabException ex) {
            Assert.assertTrue(ex.getMessage().equals("idiom.error.words.associated"));
        } 
        
    }
}
