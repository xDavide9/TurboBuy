package com.xdavide9.turbobuy.security;

import com.xdavide9.turbobuy.auth.AppUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.xdavide9.turbobuy.user.AppUserRole.ADMIN;
import static com.xdavide9.turbobuy.user.AppUserRole.USER;

@Component
@Slf4j
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        String redirect = request.getContextPath();
        if (userDetails.hasRole(USER)) {
            redirect = "/";
        } else if (userDetails.hasRole(ADMIN)) {
            redirect = "/api/v1/users";
        }
        response.sendRedirect(redirect);
        log.info("Redirecting to '{}'", redirect);
    }
}
