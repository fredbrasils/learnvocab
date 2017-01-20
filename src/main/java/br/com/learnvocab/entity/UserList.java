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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 */
@Entity
@Table(name = "USER_LIST")
public class UserList implements Serializable {

    @Id
    @Column(name = "USLI_CD_USER_LIST", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USLI_CD_USER_LIST")
    @SequenceGenerator(name = "SEQ_USLI_CD_USER_LIST", sequenceName = "SEQ_USLI_CD_USER_LIST", allocationSize = 1)
    private Long id;
    
    @JoinColumn(name = "USER_CD_USER", referencedColumnName = "USER_CD_USER", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    
    @JoinColumn(name = "USWO_CD_USER_WORD", referencedColumnName = "USWO_CD_USER_WORD", nullable = true)
    @ManyToMany(fetch = FetchType.LAZY)
    private List<UserWord> userWords;
    
    @JoinColumn(name = "WORD_CD_WORD", referencedColumnName = "WORD_CD_WORD", nullable = true)
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Word> words;        
    
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userList")
    private Review review;        
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userList")
    private List<FlowHistoric> flowHistoric;        
    
    @Column(name = "USLI_NR_GROUP", nullable = false)
    private Integer groupNumber;
    
    @Column(name = "USLI_TX_OBSERVATION", nullable = true)
    private String observation;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "USWO_DT_DATE_REGISTER", nullable = false)
    private Calendar dateRegister;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserWord> getUserWords() {
        return userWords;
    }

    public void setUserWords(List<UserWord> userWords) {
        this.userWords = userWords;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }    

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Review getReview() {
        return review;
    }

    public List<FlowHistoric> getFlowHistoric() {
        return flowHistoric;
    }

    public void setFlowHistoric(List<FlowHistoric> flowHistoric) {
        this.flowHistoric = flowHistoric;
    }

    public void setReview(Review review) {
        this.review = review;
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
        final UserList other = (UserList) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
