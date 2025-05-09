package com.platzi_pizzeria.web.controller.config;

import com.platzi_pizzeria.service.UserSecurityService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final UserSecurityService userSecurityService;

    public JwtFilter(JWTUtil jwtUtil, UserSecurityService userSecurityService) {
        this.jwtUtil = jwtUtil;
        this.userSecurityService = userSecurityService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. Validar que sea un header de autorización valido
        String token = request.getHeader("Authorization");

        if (token != null || token.isEmpty() || token.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Validar el token
        String jwt = token.split(" ")[1].trim();

        if (!this.jwtUtil.isValid(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Obtener el usuario
        String username = this.jwtUtil.getUsername(jwt);
        User user = (User) this.userSecurityService.loadUserByUsername(username);

        // 4. Cargar el usuario en el contexto de seguridad
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);

    }
}
