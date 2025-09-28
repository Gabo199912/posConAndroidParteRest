package org.pos.posconandroid.security.filtros;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.pos.posconandroid.security.TokenJwtConfig.*;

public class JwtValidacionToken extends BasicAuthenticationFilter {

    public JwtValidacionToken(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
            String header = request.getHeader(HEADER_AUTHORIZATION);
            if (header == null || !header.startsWith(TOKEN_PREFIX)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            String token = header.replace(TOKEN_PREFIX, "");

        try {
            Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
            String nombreUsuario = claims.getSubject();
            String rol = claims.get("rol").toString();

            GrantedAuthority authority = new SimpleGrantedAuthority(rol);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(nombreUsuario, null, List.of(authority));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);



        } catch (JwtException e) {
            Map<String, String> body = new HashMap<>();
            body.put("mensaje", "ERROR EN LA VALIDACION DEL TOKEN");
            body.put("error", e.getMessage());


            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
