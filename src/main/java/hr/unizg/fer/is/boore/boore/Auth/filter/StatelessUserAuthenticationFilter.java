package hr.unizg.fer.is.boore.boore.Auth.filter;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.GroupQuery;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.rest.util.EngineUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class StatelessUserAuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // Current limitation: Only works for the default engine
        ProcessEngine engine = EngineUtil.lookupProcessEngine("default");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        assert principal instanceof UserDetails;
        if(!username.equals("anonymousUser")){
            List<String> groups = ((UserDetails) principal).getAuthorities().stream()
                    .map(authority -> authority.getAuthority().substring(5)).collect(Collectors.toList());

            try {
                IdentityService identityService = engine.getIdentityService();
                if(identityService.createUserQuery().userId(username).count() == 0){
                    User camundaUser = identityService.newUser(username);
                    identityService.saveUser(camundaUser);
                }

                List<Group> memberOf = identityService.createGroupQuery().groupMember(username).list();
                if(memberOf.stream().noneMatch(group -> group.getId().equals(groups.get(0)))){
                    identityService.createMembership(username, groups.get(0));
                }
                identityService.setAuthentication(username, groups);
                filterChain.doFilter(servletRequest, servletResponse);
            } finally {
                clearAuthentication(engine);
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private void clearAuthentication(ProcessEngine engine) {
        engine.getIdentityService().clearAuthentication();
    }
}
