package com.xdavide9.turbobuy.account;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "UsernameChange")
@Table(name = "username_change")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsernameChange {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "username_change_generator"
    )
    @SequenceGenerator(
            name = "username_change_generator",
            sequenceName = "username_change_generator",
            allocationSize = 1
    )
    private Integer usernameChangeId;
    @Column(name = "app_user_id")
    private Integer appUserId;
    private String currentUsername;
    @Column(unique = true)
    private String newUsername;

    public UsernameChange(String currentUsername, String newUsername) {
        this.currentUsername = currentUsername;
        this.newUsername = newUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsernameChange that = (UsernameChange) o;
        return Objects.equals(usernameChangeId, that.usernameChangeId) && Objects.equals(appUserId, that.appUserId) && Objects.equals(currentUsername, that.currentUsername) && Objects.equals(newUsername, that.newUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usernameChangeId, appUserId, currentUsername, newUsername);
    }
}
