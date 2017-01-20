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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 */
@Entity
@Table(name = "USER_WORD")
public class UserWord implements Serializable {

    @Id
    @Column(name = "USWO_CD_USER_WORD", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USWO_CD_USER_WORD")
    @SequenceGenerator(name = "SEQ_USWO_CD_USER_WORD", sequenceName = "SEQ_USWO_CD_USER_WORD", allocationSize = 1)
    private Long id;
    
    @JoinColumn(name = "IDIO_CD_IDIOM", referencedColumnName = "IDIO_CD_IDIOM", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Idiom idiom;
    
    @JoinColumn(name = "USER_CD_USER", referencedColumnName = "USER_CD_USER", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    
    @Column(name = "USWO_TX_WORD", nullable = false)
    private String word;    

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "userWords")
    private List<UserList> userList;    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "USWO_DT_DATE_REGISTER", nullable = false)
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

    public Idiom getIdiom() {
        return idiom;
    }

    public void setIdiom(Idiom idiom) {
        this.idiom = idiom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        final UserWord other = (UserWord) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
