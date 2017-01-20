package br.com.learnvocab.service;

import br.com.learnvocab.util.LearnVocabException;

public interface WordService{

    public void delete(Long id) throws LearnVocabException;
}
