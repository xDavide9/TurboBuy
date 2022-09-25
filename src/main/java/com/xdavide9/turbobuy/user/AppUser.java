package com.xdavide9.turbobuy.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import javax.persistence.*;
import java.util.Set;

import static com.xdavide9.turbobuy.security.AppUserRole.USER;

@Entity
@Table
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
    private Integer userId;
    @Column(unique = true)
    private String username;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<SimpleGrantedAuthority> grantedAuthorities = USER.getGrantedAuthorities();
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    public AppUser(String username, String password, Set<SimpleGrantedAuthority> grantedAuthorities) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
