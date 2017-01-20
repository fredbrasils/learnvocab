package br.com.learnvocab.service;

import org.springframework.beans.factory.annotation.Autowired;
import br.com.learnvocab.dao.*;
import br.com.learnvocab.util.LearnVocabException;
import java.util.List;
/**
 *
 * @param <E> Entity
 * @param <T> Class
 */
public abstract class BaseServiceImpl<E,T extends BaseDAOImpl<E>> implements BaseService<E>{

    @Autowired
    private T dao;
    
    private Class<E> entity;

    public BaseServiceImpl(Class<E> entity) {
        this.entity = entity;
    }     
    
    @Override
    public void save(E entity) throws LearnVocabException{
        dao.save(entity);
    }
    
    @Override
    public void save(List<E> entities) throws LearnVocabException{
        dao.save(entities);
    }
    
    @Override
    public E update(E entity) throws LearnVocabException{
        return dao.update(entity);
    }
    
    @Override
    public List<E> update(List<E> entities) throws LearnVocabException{
        return dao.update(entities);
    }
    
    @Override
    public void delete(E entity) throws LearnVocabException{
        dao.delete(entity);
    }
    
    @Override
    public void delete(List<E> entities) throws LearnVocabException{
        dao.delete(entities);
    }
    
    @Override
    public E find(Long id){
        return dao.find(entity,id);
    }    
    
    @Override
    public List<E> findAll(){
        return dao.findAll(entity);
    }

    public T getDao() {
        return dao;
    }

    public void setDao(T dao) {
        this.dao = dao;
    }
    
}
