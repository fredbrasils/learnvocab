package br.com.learnvocab.service;

import br.com.learnvocab.util.LearnVocabException;
import java.util.List;

public interface BaseService<E> {

    
    public void save(E entity) throws LearnVocabException;
    
    public void save(List<E> entities) throws LearnVocabException;
    
    public E update(E entity) throws LearnVocabException;
    
    public List<E> update(List<E> entities) throws LearnVocabException;
    
    public void delete(E entity) throws LearnVocabException;
    
    public void delete(List<E> entities) throws LearnVocabException;
    
    public E find(Long id);
    
    public List<E> findAll();
    
}
