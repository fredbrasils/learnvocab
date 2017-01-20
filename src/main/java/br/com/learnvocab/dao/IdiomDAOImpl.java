package br.com.learnvocab.dao;

import br.com.learnvocab.entity.Idiom;
import javax.persistence.NoResultException;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class IdiomDAOImpl extends BaseDAOImpl<Idiom>{

    public Idiom find(String name) throws Exception{
        try {
            return getManager().createQuery("select idiom from Idiom idiom where idiom.name = :name", Idiom.class).setParameter("name", name).getSingleResult();            
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public Idiom findWithAllWord(Long id){
        
        StringBuilder sb = new StringBuilder();
        sb.append("select distinct idiom from Idiom idiom ");
        sb.append("left join fetch idiom.words ");
        sb.append("where idiom.id = :id");
        
        return getManager().createQuery(sb.toString(), Idiom.class).setParameter("id", id).getSingleResult();
    }
}
