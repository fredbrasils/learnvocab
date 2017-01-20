package br.com.learnvocab.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 */
@Entity
@Table(name = "SUGGESTED_TRANSLATION")
public class SuggestedTranslation implements Serializable {
    
    @EmbeddedId
    protected SuggestedTranslationPK id;
    
    @JoinColumn(name = "WORD_CD_WORD", referencedColumnName = "WORD_CD_WORD", nullable = false, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Word word;
    
    @JoinColumn(name = "IDIO_CD_IDIOM", referencedColumnName = "IDIO_CD_IDIOM", nullable = false, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Idiom translationIdiom;
    
    @Column(name = "SUTR_TX_TRANSLATION", nullable = false)
    private String translation;    

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SUTR_DT_DATE_REGISTER", nullable = false)
    private Calendar dateRegister;

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
    
    public Calendar getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Calendar dateRegister) {
        this.dateRegister = dateRegister;
    }

    public SuggestedTranslationPK getId() {
        return id;
    }

    public void setId(SuggestedTranslationPK id) {
        this.id = id;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Idiom getTranslationIdiom() {
        return translationIdiom;
    }

    public void setTranslationIdiom(Idiom translationIdiom) {
        this.translationIdiom = translationIdiom;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SuggestedTranslation other = (SuggestedTranslation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
