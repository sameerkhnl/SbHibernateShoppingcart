package org.khanal.SbHibernateShoppingcart.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "ACCOUNTS")
public class Account {

    @Column(length = 20, nullable = false)
    @NotEmpty
    @NotNull
    @Id
    private String username;

    @Column(length = 1, nullable = false)
    @NotEmpty
    @NotNull
    private Boolean active;

    private static final String ROLE_MANAGER = "MANAGER";
    private static final String ROLE_EMPLOYEE = "EMPLOYEE";

    @Column(name="encrypted_password", length = 120, nullable = false)
    @NotEmpty
    @NotNull
    private String encryptedPassword;

    @Column(name = "user_role", length = 20, nullable = false)
    @NotEmpty
    @NotNull
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Transient
    private Integer failedLoginAttempts;

    @Transient
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
