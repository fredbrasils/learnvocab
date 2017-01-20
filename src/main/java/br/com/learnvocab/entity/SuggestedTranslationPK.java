package br.com.learnvocab.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 */
@Embeddable
public class SuggestedTranslationPK implements Serializable {
    
    @Column(name = "WORD_CD_WORD", nullable = false)
    private Long idWord;
    
    @Column(name = "IDIO_CD_IDIOM", nullable = false)
    private Long idIdiom;

    public SuggestedTranslationPK() {
    }

    public SuggestedTranslationPK(Long idWord, Long idIdiom) {
        this.idWord = idWord;
        this.idIdiom = idIdiom;
    }

    public Long getIdWord() {
        return idWord;
    }

    public void setIdWord(Long idWord) {
        this.idWord = idWord;
    }

    public Long getIdIdiom() {
        return idIdiom;
    }

    public void setIdIdiom(Long idIdiom) {
        this.idIdiom = idIdiom;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idWord);
        hash = 97 * hash + Objects.hashCode(this.idIdiom);
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
        final SuggestedTranslationPK other = (SuggestedTranslationPK) obj;
        if (!Objects.equals(this.idWord, other.idWord)) {
            return false;
        }
        if (!Objects.equals(this.idIdiom, other.idIdiom)) {
            return false;
        }
        return true;
    }
    
}
