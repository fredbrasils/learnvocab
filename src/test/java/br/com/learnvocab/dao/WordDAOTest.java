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
@ContextConfiguration(classes = {WordServiceImpl.class, WordDAOImpl.class,
                                 IdiomServiceImpl.class, IdiomDAOImpl.class,
                                 JPAConfiguration.class,DataSourceConfigurationTest.class })
@ActiveProfiles("test")
public class WordDAOTest {
    
    @Autowired
    private WordServiceImpl wordService;

    @Autowired
    private IdiomServiceImpl idiomService;

    
    @Before
    @Test
    @Transactional
    public void shouldCreateIdioms() throws LearnVocabException {        

        List<Idiom> idioms = idiomService.findAll();
        idiomService.delete(idioms);
        
        idioms = IdiomBuilder.newIdiom("English").more("France","Portuguese").buildAll();
        
        for(Idiom idiom : idioms){
            idiomService.save(idiom);
        }        
        
        idioms = idiomService.findAll();
        
        Assert.assertEquals(3, idioms.size());
    }
    
    @Transactional
    @Test
    public void shouldCreateWordFromEnglish() throws LearnVocabException, Exception {        
        
        Idiom idiom = idiomService.find("English");
        
        Word word = WordBuilder.newWord("Hello").fromIdiom(idiom).withPriority(WordPriority.HIGHT).build();
        
        wordService.save(word);
        
        List<Word> words = wordService.findAll();
        
        Assert.assertEquals(1, words.size());
    }
    
    @Transactional
    @Test
    public void shouldndCreateEqualsWordsFromEnglish() throws LearnVocabException, Exception {        
        
        Idiom idiom = idiomService.find("English");
        
        Word word1 = WordBuilder.newWord("Hello").fromIdiom(idiom).withPriority(WordPriority.HIGHT).build();
        Word word2 = WordBuilder.newWord("Hello").fromIdiom(idiom).withPriority(WordPriority.HIGHT).build();
        
        wordService.save(word1);
        
        try {
            wordService.save(word2);
            Assert.assertTrue(false);
        } catch (LearnVocabException ex) {
            Assert.assertTrue(ex.getMessage().equals("word.error.exist"));
        }        
    }
    
    @Transactional
    @Test
    public void shouldUpdateWordFromEnglish() throws LearnVocabException, Exception {        
        
        Idiom idiom = idiomService.find("English");
        
        Word word = WordBuilder.newWord("Hello").fromIdiom(idiom).withPriority(WordPriority.HIGHT).build();
        
        wordService.save(word);
        
        word.setWord("Bye");
        
        wordService.update(word);
        
        List<Word> words = wordService.findAll();
        
        Assert.assertEquals("Bye", words.get(0).getWord());
    }
    
    @Transactional
    @Test
    public void shouldntUpdateWordFromEnglish() throws LearnVocabException, Exception {        
        
        Idiom idiom = idiomService.find("English");
        
        Word word1 = WordBuilder.newWord("Hello").fromIdiom(idiom).withPriority(WordPriority.HIGHT).build();
        Word word2 = WordBuilder.newWord("Bye").fromIdiom(idiom).withPriority(WordPriority.HIGHT).build();
        
        wordService.save(word1);
        wordService.save(word2);
        
        try {
            word1.setWord("Bye");            
            wordService.update(word1);
            Assert.assertTrue(false);
        } catch (LearnVocabException ex) {
            Assert.assertTrue(ex.getMessage().equals("word.error.exist"));
        }
        
    }
    
    @Transactional
    @Test
    public void shouldDeleteWordFromEnglish() throws LearnVocabException, Exception {        
        
        Idiom idiom = idiomService.find("English");
        
        Word word1 = WordBuilder.newWord("Hello").fromIdiom(idiom).withPriority(WordPriority.HIGHT).build();
        
        wordService.delete(word1);
        
        List<Word> words = wordService.findAll();
        
        Assert.assertTrue(words.isEmpty());
    }
}
