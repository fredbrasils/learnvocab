package br.com.learnvocab.builder;


import br.com.learnvocab.entity.Idiom;
import br.com.learnvocab.entity.Word;
import br.com.learnvocab.entity.enums.WordPriority;
import java.util.Calendar;

/**
 * @author Fred Brasil
 */
public class WordBuilder {

    private Word word;

    private WordBuilder(Word word) {
        this.word = word;
    }

    public static WordBuilder newWord(String name) {
        Word word = create(name);
        return new WordBuilder(word);
    }
    
    public WordBuilder fromIdiom(Idiom idiom) {
        this.word.setIdiom(idiom);
        return this;
    }
    
    public WordBuilder withPriority(WordPriority priority) {
        this.word.setPriority(priority);
        return this;
    }

    private static Word create(String name) {
        Word word = new Word();
        word.setWord(name);
        word.setDateRegister(Calendar.getInstance());        
        return word;
    }
     
    public Word build() {
        return word;
    }

}
