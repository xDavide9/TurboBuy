package com.xdavide9.turbobuy.registration;

import com.xdavide9.turbobuy.exception.UsernameAlreadyTakenException;
import com.xdavide9.turbobuy.user.AppUser;
import com.xdavide9.turbobuy.user.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import static com.xdavide9.turbobuy.security.Redirect.HOME;

@Service
@AllArgsConstructor
@Slf4j
public class RegistrationService {

    private final AppUserRepository repository;
    private final PasswordEncoder encoder;

    public RedirectView register(HttpServletRequest request, AppUser user) {
        String username = user.getUsername();
        if (repository.findByUsername(username).isPresent())
            throw new UsernameAlreadyTakenException("Username '" + username + "' is taken.", true);
        String decodedPassword = user.getPassword();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repository.save(user);
        log.info("Successfully registered user '{}'", username);
        try {
            request.login(username, decodedPassword);
        } catch (ServletException e) {
            log.error("Error while logging user '{}' in.", username);
            log.error(e.getMessage());
        }
        log.info("Redirecting to '{}'", HOME.getUrl());
        return new RedirectView(HOME.getUrl());
    }
}
