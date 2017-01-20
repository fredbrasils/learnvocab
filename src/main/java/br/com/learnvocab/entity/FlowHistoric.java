package br.com.learnvocab.entity;

import br.com.learnvocab.entity.enums.FlowAction;
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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 */
@Entity
@Table(name = "FLOW_HISTORIC")
public class FlowHistoric implements Serializable {

    @Id
    @Column(name = "FLHI_CD_FLHI", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FLHI_CD_FLHI")
    @SequenceGenerator(name = "SEQ_FLHI_CD_FLHI", sequenceName = "SEQ_FLHI_CD_FLHI", allocationSize = 1)
    private Long id;
    
    @JoinColumn(name = "USLI_CD_USER_LIST", referencedColumnName = "USLI_CD_USER_LIST", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UserList userList;
    
    @Column(name = "FLHI_CD_ACTION", nullable = false)
    @Enumerated(EnumType.STRING)
    private FlowAction action;
    
    @JoinColumn(name = "BOX_CD_SOURCE_BOX", referencedColumnName = "BOX_CD_BOX", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Box sourceBox;
    
    @JoinColumn(name = "BOX_CD_DESTINATION_BOX", referencedColumnName = "BOX_CD_BOX", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Box destinationBox;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FLHI_DT_DATE_REGISTER", nullable = false)
    private Calendar dateRegister;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserList getUserList() {
        return userList;
    }

    public void setUserList(UserList userList) {
        this.userList = userList;
    }

    public FlowAction getAction() {
        return action;
    }

    public void setAction(FlowAction action) {
        this.action = action;
    }

    public Box getSourceBox() {
        return sourceBox;
    }

    public void setSourceBox(Box sourceBox) {
        this.sourceBox = sourceBox;
    }

    public Box getDestinationBox() {
        return destinationBox;
    }

    public void setDestinationBox(Box destinationBox) {
        this.destinationBox = destinationBox;
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
        final FlowHistoric other = (FlowHistoric) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
