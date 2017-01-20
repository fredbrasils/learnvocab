package br.com.learnvocab.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 */
@Entity
@Table(name = "IDIOM")
public class Idiom implements Serializable {

    @Id
    @Column(name = "IDIO_CD_IDIOM", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_IDIO_CD_IDIOM")
    @SequenceGenerator(name = "SEQ_IDIO_CD_IDIOM", sequenceName = "SEQ_IDIO_CD_IDIOM", allocationSize = 1)
    private Long id;
    
    @Column(name = "IDIO_TX_NAME", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "idiom", fetch = FetchType.LAZY)
    private List<Word> words;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "IDIO_DT_DATE_REGISTER", nullable = false)
    private Calendar dateRegister;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
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
        final Idiom other = (Idiom) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Idiom{" + "id=" + id + ", name=" + name + '}';
    }    
    
}
