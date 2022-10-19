package com.xdavide9.turbobuy.user;

import com.xdavide9.turbobuy.sale.Sale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.xdavide9.turbobuy.user.AppUserRole.USER;

@Entity(name = "AppUser")
@Table(name = "app_user")
@Data
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
    private List<Sale> sales = new ArrayList<>();

    public AppUser(String username, String password, AppUserRole role, Set<SimpleGrantedAuthority> grantedAuthorities) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.grantedAuthorities = grantedAuthorities;
    }

    public AppUser(String username, String password, AppUserRole role, Set<SimpleGrantedAuthority> grantedAuthorities, List<Sale> sales) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.grantedAuthorities = grantedAuthorities;
        this.sales = sales;
    }

    public AppUser(String username, String password, List<Sale> sales) {
        this.username = username;
        this.password = password;
        this.sales = sales;
    }

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
