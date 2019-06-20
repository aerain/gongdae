package kr.ac.jejunu.sslab.gongdae.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String redirectUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            log.info("redirect failed");
            return;
        }
        redirectStrategy.sendRedirect(request, response, redirectUrl);
    }

    private String determineTargetUrl(Authentication authentication) {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<>() {{
            for(GrantedAuthority g : authorities)
                add(g.getAuthority());
        }};

        String url;
        if(roles.contains(MemberRoleEnum.user.getRole())) {
            url = "/user";
        } else if(roles.contains(MemberRoleEnum.company.getRole())) {
            url = "/company";
        } else {
            url = "/";
        }
        return url;
    }

    private boolean isUser(List<String> roles) {
        return false;
    }
}
