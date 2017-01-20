package br.com.learnvocab.service;

import br.com.learnvocab.entity.Idiom;
import br.com.learnvocab.util.LearnVocabException;

public interface IdiomService{

    public Idiom find(String name) throws Exception;
    
    public void delete(Long id) throws LearnVocabException;
    
    public Idiom findWithAllWord(Long id);
}
