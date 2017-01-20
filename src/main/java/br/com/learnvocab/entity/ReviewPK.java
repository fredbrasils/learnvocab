package br.com.learnvocab.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 */
@Embeddable
public class ReviewPK implements Serializable {
    
    @Column(name = "USLI_CD_USER_LIST", nullable = false)
    private Long idUserList;
    
    @Column(name = "BOX_CD_BOX", nullable = false)
    private Long idBox;

    public ReviewPK() {
    }

    public ReviewPK(Long idUserList, Long idBox) {
        this.idUserList = idUserList;
        this.idBox = idBox;
    }

    public Long getIdUserList() {
        return idUserList;
    }

    public void setIdUserList(Long idUserList) {
        this.idUserList = idUserList;
    }

    public Long getIdBox() {
        return idBox;
    }

    public void setIdBox(Long idBox) {
        this.idBox = idBox;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.idUserList);
        hash = 23 * hash + Objects.hashCode(this.idBox);
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
        final ReviewPK other = (ReviewPK) obj;
        if (!Objects.equals(this.idUserList, other.idUserList)) {
            return false;
        }
        if (!Objects.equals(this.idBox, other.idBox)) {
            return false;
        }
        return true;
    }
    
}
