package br.com.learnvocab.entity;

import br.com.learnvocab.entity.enums.RoleUser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 *
 */
@Entity
@Table(name = "USERS")
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "USER_CD_USER", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_CD_USER")
    @SequenceGenerator(name = "SEQ_USER_CD_USER", sequenceName = "SEQ_USER_CD_USER", allocationSize = 1)
    private Long id;
    
    @Column(name = "USER_TX_NAME", nullable = false)
    private String name;
    
    @Column(name = "USER_TX_EMAIL", nullable = false)
    private String email;
    
    @Column(name = "USER_TX_PASSWORD", nullable = false)
    private String password;
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserList> userList;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "USER_DT_DATE_REGISTER", nullable = false)
    private Calendar dateRegister;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE", 
        joinColumns = { @JoinColumn(name = "USER_CD_USER", nullable = false, updatable = false) },
        inverseJoinColumns = { @JoinColumn(name = "ROLE_CD_ROLE", nullable = false, updatable = false) }
    )
    private List<Role> roles = new ArrayList<Role>();
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            return this.roles;
    }

    @Override
    public String getUsername() {
            return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
            return true;
    }

    @Override
    public boolean isAccountNonLocked() {
            return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
            return true;
    }

    @Override
    public boolean isEnabled() {
            return true;
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }        

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
        
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password != null){
            BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
            this.password = crypt.encode(password);
        }else{
            this.password = password;
        }
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
    
    public String getRolesName(){
        StringBuilder sb = new StringBuilder();
        
        for(Role role : this.roles){
            sb.append(RoleUser.getRoleUserByRole(role).toString()).append(", ");
        }
        
        sb.delete(sb.length()-2, sb.length());
        
        return sb.toString();
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
        
    
}
