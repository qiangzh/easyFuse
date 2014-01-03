package com.maodr.system.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import com.maodr.base.model.BaseObject;

/**
 * 
 *  用户PO
 *  @author Administrator
 *  @created 2013年12月31日 上午4:05:24
 *  @lastModified       
 *  @history
 */
@Entity
@Table(name = "T_SYS_USER")
public class UserPO extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359411L;

    private String id;

    private String username; // required

    private String password; // required   

    private String email; // required; unique

    private String phoneNumber;

    private boolean enabled;

    private boolean accountExpired;

    private boolean accountLocked;

    private boolean credentialsExpired;

    public UserPO() {
    }

    public UserPO(final String username) {
        this.username = username;
    }
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "C_ID", unique = true, nullable = false, length = 32)
    public String getId() {
        return id;
    }

    @Column(name = "C_USERNAME", nullable = false, length = 50, unique = true)
    public String getUsername() {
        return username;
    }

    @Column(name = "C_PASSWORD", nullable = false)
    public String getPassword() {
        return password;
    }

    @Column(name = "C_EMAIL", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    @Column(name = "C_PHONENUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Column(name = "C_ACCOUNT_ENABLED")
    public boolean isEnabled() {
        return enabled;
    }

    @Column(name = "C_ACCOUNT_EXPIRED", nullable = false)
    public boolean isAccountExpired() {
        return accountExpired;
    }

    @Column(name = "C_ACCOUNT_LOCKED", nullable = false)
    public boolean isAccountLocked() {
        return accountLocked;
    }

    @Column(name = "C_CREDENTIALS_EXPIRED", nullable = false)
    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserPO)) {
            return false;
        }

        final UserPO user = (UserPO) o;

        return !(username != null ? !username.equals(user.getUsername()) : user.getUsername() != null);

    }

    public int hashCode() {
        return (username != null ? username.hashCode() : 0);
    }

    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("username", this.username)
                .append("enabled", this.enabled).append("accountExpired", this.accountExpired)
                .append("credentialsExpired", this.credentialsExpired).append("accountLocked", this.accountLocked);

        return sb.toString();
    }
}
