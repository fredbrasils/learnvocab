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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 */
@Entity
@Table(name = "BOX")
public class Box implements Serializable {

    @Id
    @Column(name = "BOX_CD_BOX", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BOX_CD_BOX")
    @SequenceGenerator(name = "SEQ_BOX_CD_BOX", sequenceName = "SEQ_BOX_CD_BOX", allocationSize = 1)
    private Long id;
    
    @Column(name = "BOX_TX_NAME", nullable = false)
    private String name;
    
    @Column(name = "BOX_NR_DAYS", nullable = false)
    private Integer daysNumber;
    
    @Column(name = "BOX_NR_ORDER", nullable = false)
    private Integer order;
    
    /*
    @JoinColumn(name = "USER_CD_USER", referencedColumnName = "USER_CD_USER", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    */
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "box")
    private List<Review> review;    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BOX_DT_DATE_REGISTER", nullable = false)
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

    public Integer getDaysNumber() {
        return daysNumber;
    }

    public void setDaysNumber(Integer daysNumber) {
        this.daysNumber = daysNumber;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
/*
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
*/
    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
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
        final Box other = (Box) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
