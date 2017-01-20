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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 */
@Entity
@Table(name = "REVIEW")
public class Review implements Serializable {
    
    @EmbeddedId
    protected ReviewPK id;
    
    @JoinColumn(name = "USLI_CD_USER_LIST", referencedColumnName = "USLI_CD_USER_LIST", nullable = false, insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private UserList userList;
    
    @JoinColumn(name = "BOX_CD_BOX", referencedColumnName = "BOX_CD_BOX", nullable = false, insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Box box;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REVI_DT_DATE_REVIEW", nullable = false)
    private Calendar dateReview;

    public ReviewPK getId() {
        return id;
    }

    public void setId(ReviewPK id) {
        this.id = id;
    }

    public UserList getUserList() {
        return userList;
    }

    public void setUserList(UserList userList) {
        this.userList = userList;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public Calendar getDateReview() {
        return dateReview;
    }

    public void setDateReview(Calendar dateReview) {
        this.dateReview = dateReview;
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
        final Review other = (Review) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
