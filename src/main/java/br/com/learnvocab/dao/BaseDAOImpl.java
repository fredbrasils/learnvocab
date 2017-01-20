package br.com.learnvocab.dao;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Repository
@Transactional
public abstract class BaseDAOImpl<E> implements BaseDAO<E>{

    @PersistenceContext
    private EntityManager manager;
    
    @Override
    public void save(E entity){
        manager.persist(entity);
    }
    
    @Override
    public void save(List<E> entities){
        for(E entity : entities ){
            manager.persist(entity);
        }
    }
    
    @Override
    public E update(E entity){
        return manager.merge(entity);
    }
    
    @Override
    public List<E> update(List<E> entities){
        for(E entity : entities ){
            manager.merge(entity);
        }
        
        return entities;
    }
    
    @Override
    public void delete(E entity){
        manager.remove(entity);
    }
    
    @Override
    public void delete(List<E> entities){
        for(E entity : entities ){
            manager.remove(entity);
        }
    }
    
    @Override
    public E find(Class<E> entity,Long id){
        return manager.find(entity, id);
    }    
    
    @Override
    public List<E> findAll(Class<E> entity){
        return manager.createQuery("select tb from Clazz tb".replace("Clazz", entity.getSimpleName()),entity).getResultList();
    }

    public EntityManager getManager() {
        return manager;
    }    

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }    
    
    public TypedQuery<E> createQuery(String query, Map<String,Object> params){        
        return createQuery(null, query, params);
    }
    
    public TypedQuery<E> createQuery(Class<E> entity, String query, Map<String,Object> params){
        
        TypedQuery<E> typeQuery = getManager().createQuery(query,entity);
        
        if(params != null && !params.isEmpty()){
            for(String key : params.keySet()){
                typeQuery.setParameter(key, params.get(key));
            }
        }
        
        return typeQuery;
    }
}
