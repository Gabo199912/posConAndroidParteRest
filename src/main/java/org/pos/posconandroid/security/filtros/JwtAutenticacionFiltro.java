package org.pos.posconandroid.security.filtros;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.pos.posconandroid.modelos.UsuarioModelo;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.pos.posconandroid.security.TokenJwtConfig.*;

public class JwtAutenticacionFiltro extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;


    public JwtAutenticacionFiltro(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsuarioModelo usuario = null;
        String contrasenia = null;
        String nombreUsuario = null;

        try {
            usuario = new ObjectMapper().readValue(request.getInputStream(), UsuarioModelo.class);
            nombreUsuario = usuario.getNombreUsuario();
            contrasenia = usuario.getContraseniaHash();
        } catch (StreamReadException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(nombreUsuario, contrasenia);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        org.springframework.security.core.userdetails.User usuario = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        String nombreUsuario = usuario.getUsername();
        String rol = authResult.getAuthorities().iterator().next().getAuthority();
        Claims claims = Jwts.claims().add("rol", rol).build();

        String jwt = Jwts.builder()
                .subject(nombreUsuario)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 43200000))
                .issuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();

        response.addHeader(HEADER_AUTHORIZATION, TOKEN_PREFIX + jwt);

        Map<String, String> body = new HashMap<>();
        body.put("token", jwt);
        body.put("nombre_usuario", nombreUsuario);
        body.put("mensaje", String.format("Usuario %s autenticado exitosamente", nombreUsuario));

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType("application/json");
        response.setStatus(HttpStatus.OK.value());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
            Map<String, String> body = new HashMap<>();
            body.put("mensaje", "ERROR EN LA AUTENTICACION");
            body.put("error", failed.getMessage());

            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    }
}
