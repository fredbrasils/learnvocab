package br.com.learnvocab.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 */
@Entity
@Table(name = "CONFIGURATION")
public class Configuration implements Serializable {

    @Id
    @Column(name = "CONF_CD_CONF", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONF_CD_CONF")
    @SequenceGenerator(name = "SEQ_CONF_CD_CONF", sequenceName = "SEQ_CONF_CD_CONF", allocationSize = 1)
    private Long id;
    
    @Column(name = "CONF_NR_WORD", nullable = false)
    private Integer wordNumber;
    
    @Column(name = "CONF_NR_BOX", nullable = false)
    private Integer boxNumber;

    @JoinColumn(name = "USER_CD_USER", referencedColumnName = "USER_CD_USER", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CONF_DT_DATE_REGISTER", nullable = false)
    private Calendar dateRegister;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWordNumber() {
        return wordNumber;
    }

    public void setWordNumber(Integer wordNumber) {
        this.wordNumber = wordNumber;
    }

    public Integer getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(Integer boxNumber) {
        this.boxNumber = boxNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        final Configuration other = (Configuration) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
