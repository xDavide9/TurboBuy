package com.xdavide9.turbobuy.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class AppUser {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_generator"
    )
    @SequenceGenerator(
            name = "app_user_generator",
            sequenceName = "app_user_generator",
            allocationSize = 1
    )
    private Integer userId;
    private String username;
    private String password;
    @ElementCollection
    private Set<SimpleGrantedAuthority> grantedAuthorities;

    public AppUser(Integer userId, String username, String password, Set<SimpleGrantedAuthority> grantedAuthorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }

    public AppUser(String username, String password, Set<SimpleGrantedAuthority> grantedAuthorities) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }

    public AppUser() {}

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + grantedAuthorities + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return userId.equals(appUser.userId) && username.equals(appUser.username) && password.equals(appUser.password) && grantedAuthorities.equals(appUser.grantedAuthorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, grantedAuthorities);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setRoles(Set<SimpleGrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }
}
