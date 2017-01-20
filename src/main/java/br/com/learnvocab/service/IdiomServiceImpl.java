package br.com.learnvocab.service;

import br.com.learnvocab.dao.IdiomDAOImpl;
import br.com.learnvocab.entity.Idiom;
import br.com.learnvocab.util.LearnVocabException;
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
public class IdiomServiceImpl extends BaseServiceImpl<Idiom,IdiomDAOImpl> implements IdiomService{

    public IdiomServiceImpl() {
        super(Idiom.class);
    }

    @Override
    public void save(Idiom idiom) throws LearnVocabException{
        
        try{
            if(exist(idiom)){ // if exists idiom
                throw new LearnVocabException(LearnVocabException.ERROR_RULE, "idiom.error.exist");
            }
        }catch(Exception ex){
            throw new LearnVocabException(LearnVocabException.ERROR_RULE, "idiom.error.exist");
        }
        
        super.save(idiom);
    }

    @Override
    public Idiom update(Idiom idiom) throws LearnVocabException {
        
        Idiom idiomAux = null;
        
        try{
            idiomAux = find(idiom.getName()); // search idiom by name
        }catch(Exception ex){
            throw new LearnVocabException(LearnVocabException.ERROR_RULE, "idiom.error.exist");
        }
        
        if(idiomAux != null && !idiomAux.getId().equals(idiom.getId())){ // if exists idiom
            throw new LearnVocabException(LearnVocabException.ERROR_RULE, "idiom.error.exist");
        }
        
        return super.update(idiom);
    }
    
    
    /* Verified if idiom already exists */    
    private boolean exist(Idiom idiom) throws Exception{
        
        Idiom idiomAux = find(idiom.getName()); // search idiom by name
        
        return idiomAux != null;
    }
    
    /*
        Remove idiom by id
    */
    @Override
    public void delete(Long id) throws LearnVocabException{
        
        Idiom idiom = findWithAllWord(id); // return idiom with fetch word
        
        // Does not allow remove idiom with words associated
        if(idiom.getWords() != null && !idiom.getWords().isEmpty()){ 
            throw new LearnVocabException(LearnVocabException.ERROR_RULE, "idiom.error.words.associated");
        }
        
        super.delete(idiom);
    }
   
    /*
        Remove idiom by id
    */
    @Override
    public void delete(Idiom idiom) throws LearnVocabException{
        delete(idiom.getId());
    }
    
    /*
        Return the idiom by name
    */
    @Override
    public Idiom find(String name) throws Exception{
        return getDao().find(name);
    }
    
    /*
        Return the idiom with words associated
    */
    @Override
    public Idiom findWithAllWord(Long id){
        return getDao().findWithAllWord(id);
    }
}
