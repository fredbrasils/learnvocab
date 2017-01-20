package br.com.learnvocab.dao;

import br.com.learnvocab.entity.Idiom;
import br.com.learnvocab.entity.Word;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WordDAOImpl extends BaseDAOImpl<Word>{

    public Boolean existWordByIdiom(Word word, Idiom idiom){
        
        StringBuilder sb = new StringBuilder();
        sb.append("select distinct word from Word word ");
        sb.append("inner join word.idiom idiom ");
        sb.append("where idiom = :idiom ");
        sb.append("and word.word = :word ");
        
        if(word.getId() != null){
            sb.append("and word.id != :id ");
        }
        
        try {
            Query query = getManager().createQuery(sb.toString(), Word.class)
                    .setParameter("idiom", idiom)
                    .setParameter("word", word.getWord());
            
            if(word.getId() != null){
                query.setParameter("id", word.getId());
            }
            
            word = (Word) query.getSingleResult();
            
            return word != null;
            
        }catch (NoResultException e) {
            return false;
        }
    }    
    
}
