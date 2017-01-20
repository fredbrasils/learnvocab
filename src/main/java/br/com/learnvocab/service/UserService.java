package br.com.learnvocab.service;

import br.com.learnvocab.entity.User;
import br.com.learnvocab.util.LearnVocabException;

public interface UserService{

    
    public User loadUserByUsername(String email);
    public void delete(Long id) throws LearnVocabException;
}
