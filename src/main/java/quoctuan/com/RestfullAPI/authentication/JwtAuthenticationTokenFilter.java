package quoctuan.com.RestfullAPI.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import quoctuan.com.RestfullAPI.entities.UserEntity;
import quoctuan.com.RestfullAPI.services.JWTServices;
import quoctuan.com.RestfullAPI.services.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {
    private final static String TOKEN_HEADER = "authorization";

    @Autowired
    private JWTServices jwtServices;

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String authToken = httpServletRequest.getHeader(TOKEN_HEADER);

        if(jwtServices.validateTokenLogin(authToken)){
            String username = jwtServices.getUserNameFromToken(authToken);
            UserEntity user = userService.findByUser(username);
            if(user != null){
                boolean enabled = true;
                boolean accountNonExpired = true;
                boolean credentialsNonExpired = true;
                boolean accountNonLocked = true;
                UserDetails userDetails = new User(username, user.getPassword(), enabled,accountNonExpired, credentialsNonExpired,
                        accountNonLocked,user.getAuthorities());
            }
        }
    }
}
