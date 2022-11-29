package com.xdavide9.turbobuy.user.api;

import com.xdavide9.turbobuy.sale.api.Sale;
import com.xdavide9.turbobuy.user.account.UsernameChange;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.xdavide9.turbobuy.user.api.AppUserRole.USER;

@Entity(name = "AppUser")
@Table(name = "app_user")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
    private Integer appUserId;
    @Column(unique = true)
    private String username;
    private String password;
    @Transient
    private Boolean canPost = true;
    @Enumerated(EnumType.STRING)
    private AppUserRole role = USER;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<SimpleGrantedAuthority> grantedAuthorities = USER.getGrantedAuthorities();
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "app_user_id")
    private Set<Sale> sales = new HashSet<>();
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "app_user_id")
    private Set<UsernameChange> usernameChanges = new HashSet<>();

    public AppUser(String username, String password, AppUserRole role, Set<SimpleGrantedAuthority> grantedAuthorities) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.grantedAuthorities = grantedAuthorities;
    }

    public AppUser(String username, String password, AppUserRole role, Set<SimpleGrantedAuthority> grantedAuthorities, Set<Sale> sales) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.grantedAuthorities = grantedAuthorities;
        this.sales = sales;
    }

    public AppUser(String username, String password, Set<Sale> sales) {
        this.username = username;
        this.password = password;
        this.sales = sales;
    }

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AppUser appUser = (AppUser) o;
        return appUserId != null && Objects.equals(appUserId, appUser.appUserId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
