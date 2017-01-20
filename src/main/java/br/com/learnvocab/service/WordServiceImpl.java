package br.com.learnvocab.service;

import br.com.learnvocab.dao.WordDAOImpl;
import br.com.learnvocab.entity.Idiom;
import br.com.learnvocab.entity.Word;
import br.com.learnvocab.util.LearnVocabException;
import org.springframework.beans.factory.annotation.Autowired;
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
public class WordServiceImpl extends BaseServiceImpl<Word,WordDAOImpl> implements WordService{

    @Autowired
    private IdiomServiceImpl  idiomService;
    
    public WordServiceImpl() {
        super(Word.class);
    }

    @Override
    public void save(Word word) throws LearnVocabException{
        
        try{
            if(exist(word)){ // if exists word
                Idiom idiom = idiomService.find(word.getIdiom().getId());
                throw new LearnVocabException(LearnVocabException.ERROR_RULE, "word.error.exist", new Object[]{word.getWord(), idiom.getName()});
            }
        }catch(Exception ex){
            Idiom idiom = idiomService.find(word.getIdiom().getId());
            throw new LearnVocabException(LearnVocabException.ERROR_RULE, "word.error.exist", new Object[]{word.getWord(), idiom.getName()});
        }
        
        super.save(word);
    }
    
    /* Verified if word and idiom already exists */    
    private boolean exist(Word word) throws Exception{        
        return getDao().existWordByIdiom(word, word.getIdiom());
    }
    
    @Override
    public Word update(Word word) throws LearnVocabException {
        
        Boolean exist = false;
        
        try{
             exist = exist(word); 
        }catch(Exception ex){
            Idiom idiom = idiomService.find(word.getIdiom().getId());
            throw new LearnVocabException(LearnVocabException.ERROR_RULE, "word.error.exist", new Object[]{word.getWord(), idiom.getName()});
        }
        
        if(exist){ // if exists word
            Idiom idiom = idiomService.find(word.getIdiom().getId());
            throw new LearnVocabException(LearnVocabException.ERROR_RULE, "word.error.exist", new Object[]{word.getWord(), idiom.getName()});
        }
        
        return super.update(word);
    }
    
    /*
        Remove word by id
    */
    @Override
    public void delete(Long id) throws LearnVocabException{
        //TODO: VERIFICAR SER TEM ALGO ASSOCIADO a palavra
        
        Word word = find(id);        
        super.delete(word);
    }
    
    
}
