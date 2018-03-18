package ru.vsu.personalWallet.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.vsu.personalWallet.domain.entity.UserEntity;
import ru.vsu.personalWallet.domain.repository.UserRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static ru.vsu.personalWallet.util.Constant.USER_ID_HEADER;


public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String authToken = req.getHeader(AUTHORIZATION_HEADER);
        String username = null;
        if (authToken != null)
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            }catch (Exception e) {}

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null,
                                Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                // logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);

                validateAuthTokenWithUserIdToken(req, res, username);
            }
        }
        chain.doFilter(req, res);
    }

    private void validateAuthTokenWithUserIdToken(HttpServletRequest req,
                                                  HttpServletResponse res, String username) throws IOException {
        String userIdToken = req.getHeader(USER_ID_HEADER);
        if (userIdToken != null) {
            Long id=Long.parseLong(userIdToken);
            UserEntity userEntity = userRepository.findOne(id);
            if (userEntity != null) {
                String usernameFromIdToken = userEntity.getEmail();
                if (!username.equals(usernameFromIdToken))
                    res.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden access for user id="+userIdToken);
            } else res.sendError(HttpServletResponse.SC_NOT_FOUND, "User with id="+userIdToken+" not found");
        }
    }
}