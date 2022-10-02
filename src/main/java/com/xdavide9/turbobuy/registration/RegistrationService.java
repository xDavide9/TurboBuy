package com.xdavide9.turbobuy.registration;

import com.xdavide9.turbobuy.exception.UsernameAlreadyTakenException;
import com.xdavide9.turbobuy.user.AppUser;
import com.xdavide9.turbobuy.user.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

@Service
@AllArgsConstructor
@Slf4j
public class RegistrationService {

    private final AppUserRepository repository;
    private final PasswordEncoder encoder;

    public RedirectView register(AppUser user) {
        String username = user.getUsername();
        if (repository.findByUsername(username).isPresent())
            throw new UsernameAlreadyTakenException("Username " + username + " is taken.");
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repository.save(user);
        log.info("Successfully registered user '{}'", username);
        return new RedirectView("/login");
    }
}
