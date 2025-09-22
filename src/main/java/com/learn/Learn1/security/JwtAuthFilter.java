// security/JwtAuthFilter.java
package com.learn.Learn1.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.learn.Learn1.models.User;
import com.learn.Learn1.services.JwtService;
import com.learn.Learn1.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.List;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired private JwtService jwtService;
    @Autowired private UserService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
        throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String jwt = null;
        String email = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            email = jwtService.extractEmail(jwt);
                System.out.println("[JwtAuthFilter] Extracted JWT: " + jwt);
                System.out.println("[JwtAuthFilter] Extracted email: " + email);
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userService.findByEmail(email);
                System.out.println("[JwtAuthFilter] User found: " + (user != null ? user.getEmail() : "null"));
                if (user != null && jwtService.isTokenValid(jwt, email)) {
                    String role = user.getRole();
                    org.springframework.security.core.authority.SimpleGrantedAuthority authority =
                        new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + role.replace("ROLE_", ""));
                    UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(user, null, java.util.Collections.singletonList(authority));
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                        System.out.println("[JwtAuthFilter] Authentication set for: " + user.getEmail() + ", role: " + authority.getAuthority());
                        System.out.println("[JwtAuthFilter] Authorities: " + auth.getAuthorities());
                } else {
                    System.out.println("[JwtAuthFilter] Token invalid or user not found.");
                }
        }
        chain.doFilter(request, response);
    }
}
