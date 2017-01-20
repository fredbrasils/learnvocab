package br.com.learnvocab.builder;


import br.com.learnvocab.entity.Idiom;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 */
public class IdiomBuilder {

    private List<Idiom> idioms = new ArrayList<>();

    private IdiomBuilder(Idiom idiom) {
        idioms.add(idiom);
    }

    public static IdiomBuilder newIdiom(String name) {
        Idiom idiom = create(name);
        return new IdiomBuilder(idiom);
    }

    private static Idiom create(String idiomName) {
        Idiom idiom = new Idiom();
        idiom.setName(idiomName);
        idiom.setDateRegister(Calendar.getInstance());        
        return idiom;
    }

    public IdiomBuilder more(String... names) {        
        
        for (String idiom : names) {
            idioms.add(create(idiom));
        }        
        return this;
    }
     
    public Idiom buildOne() {
        return idioms.get(0);
    }

    public List<Idiom> buildAll() {
        return idioms;
    }
}
