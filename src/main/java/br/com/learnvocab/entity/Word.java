package br.com.learnvocab.entity;

import br.com.learnvocab.entity.enums.WordPriority;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Fred Brasil
 */
@Entity
@Table(name = "WORD")
public class Word implements Serializable {

    @Id
    @Column(name = "WORD_CD_WORD", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_WORD_CD_WORD")
    @SequenceGenerator(name = "SEQ_WORD_CD_WORD", sequenceName = "SEQ_WORD_CD_WORD", allocationSize = 1)
    private Long id;
    
    @JoinColumn(name = "IDIO_CD_IDIOM", referencedColumnName = "IDIO_CD_IDIOM", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Idiom idiom;
    
    @Column(name = "WORD_TX_WORD", nullable = false)
    private String word;
    
    @Column(name = "WORD_IN_PRIORITY", nullable = false)
    @Enumerated(EnumType.STRING)
    private WordPriority priority;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "word")
    List<SuggestedTranslation> suggestions;
            
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "words")
    private List<UserList> userList;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "WORD_DT_DATE_REGISTER", nullable = false)
    private Calendar dateRegister;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public WordPriority getPriority() {
        return priority;
    }

    public void setPriority(WordPriority priority) {
        this.priority = priority;
    }

    public Idiom getIdiom() {
        return idiom;
    }

    public void setIdiom(Idiom idiom) {
        this.idiom = idiom;
    }

    public List<SuggestedTranslation> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<SuggestedTranslation> suggestions) {
        this.suggestions = suggestions;
    }

    public List<UserList> getUserList() {
        return userList;
    }

    public void setUserList(List<UserList> userList) {
        this.userList = userList;
    }

    public Calendar getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Calendar dateRegister) {
        this.dateRegister = dateRegister;
    }    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Word other = (Word) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
