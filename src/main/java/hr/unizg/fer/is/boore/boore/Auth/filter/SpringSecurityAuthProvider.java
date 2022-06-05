package hr.unizg.fer.is.boore.boore.Auth.filter;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationResult;
import org.camunda.bpm.engine.rest.security.auth.impl.ContainerBasedAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpringSecurityAuthProvider extends ContainerBasedAuthenticationProvider {

    @Override
    public AuthenticationResult extractAuthenticatedUser(HttpServletRequest request, ProcessEngine engine) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return AuthenticationResult.unsuccessful();
        }

        String name = authentication.getName();
        if (name == null || name.isEmpty()) {
            return AuthenticationResult.unsuccessful();
        }

        AuthenticationResult authenticationResult = new AuthenticationResult(name, true);
        List<String> groups = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority().substring(5)).collect(Collectors.toList());
        authenticationResult.setGroups(groups);

        return authenticationResult;
    }
}
