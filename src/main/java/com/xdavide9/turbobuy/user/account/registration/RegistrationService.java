package com.xdavide9.turbobuy.user.account.registration;

import com.xdavide9.turbobuy.exception.UsernameAlreadyTakenException;
import com.xdavide9.turbobuy.user.api.AppUser;
import com.xdavide9.turbobuy.user.api.AppUserRepository;
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

    public RedirectView register(HttpServletRequest request,
                                 String username,
                                 String password) {
        if (repository.findByUsername(username).isPresent())
            throw new UsernameAlreadyTakenException("Username '" + username + "' is taken.", true);
        String encodedPassword = encoder.encode(password);
        AppUser user = new AppUser(username, encodedPassword);
        repository.save(user);
        log.info("Successfully registered user '{}'", username);
        try {
            request.login(username, password);
        } catch (ServletException e) {
            log.error("Error while logging user '{}' in.", username);
            log.error(e.getMessage());
        }
        log.info("Redirecting to '{}'", HOME.getUrl());
        return new RedirectView(HOME.getUrl());
    }
}
