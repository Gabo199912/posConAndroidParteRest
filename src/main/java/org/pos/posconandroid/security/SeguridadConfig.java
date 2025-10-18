package org.pos.posconandroid.security;


import org.pos.posconandroid.security.filtros.JwtAutenticacionFiltro;
import org.pos.posconandroid.security.filtros.JwtValidacionToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SeguridadConfig{

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests((authz) ->authz
                //ROLES QUE SEAN ADMINISTRADORES O SUPERIOR
                        .requestMatchers(HttpMethod.POST, "/usuarios/administrador/**").hasAnyRole("ADMINISTRADOR", "SUPER_ADMIN") // <- hay que .permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/administrador/**").hasAnyRole("ADMINISTRADOR", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuarios/administrador/**").hasAnyRole("ADMINISTRADOR", "SUPER_ADMIN")


                //ROLES PARA CLIENTES
                        .requestMatchers(HttpMethod.POST, "/cliente/administrador/**").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/cliente/administrador/**").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/cliente/vendedor/**").hasAnyRole("ADMINISTRADOR", "VENDEDOR", "SUPER_ADMIN")

                //ROLES PARA TIPOS DE PAGOS
                        .requestMatchers(HttpMethod.POST, "/TipoPagos/administrador/**").hasAnyRole("ADMINISTRADOR", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/TipoPagos/administrador/**").hasAnyRole("ADMINISTRADOR", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/TipoPagos/vendedor/**").hasAnyRole("ADMINISTRADOR", "VENDEDOR", "SUPER_ADMIN")

                    //ROLES PARA PRODUCTOS
                        .requestMatchers(HttpMethod.POST, "/producto/administrador/**").hasAnyRole("ADMINISTRADOR", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/producto/administrador/**").hasAnyRole("ADMINISTRADOR", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/producto/vendedor/**").hasAnyRole("ADMINISTRADOR", "VENDEDOR", "SUPER_ADMIN")

                //ROLES PARA PROVEEDORES
                        .requestMatchers(HttpMethod.POST, "/proveedor/administrador/**").hasAnyRole("ADMINISTRADOR", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/proveedor/administrador/**").hasAnyRole("ADMINISTRADOR", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/proveedor/vendedor/**").hasAnyRole("ADMINISTRADOR", "VENDEDOR", "SUPER_ADMIN")

                //PARA ROLES GENERALES
                        .requestMatchers(HttpMethod.GET, "/usuarios/vendedor/**").hasAnyRole("ADMINISTRADOR", "VENDEDOR", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/usuarios/vendedor/**").hasAnyRole("ADMINISTRADOR", "VENDEDOR", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/vendedor/**").hasAnyRole("ADMINISTRADOR", "VENDEDOR", "SUPER_ADMIN")

                //PARA GENERAR COMPRAS
                        .requestMatchers(HttpMethod.POST, "/compras/administrador/generarCompra/**").hasAnyRole("ADMINISTRADOR", "SUPER_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/compras/administrador/proveedores/**").hasAnyRole("ADMINISTRADOR", "SUPER_ADMIN")
                        .anyRequest().authenticated())
                .addFilter(new JwtAutenticacionFiltro(authenticationManager())) // <- debemos comentar esta linea
                .addFilter(new JwtValidacionToken(authenticationManager())) // <- debemos comentar esta linea
                .csrf(config -> config.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
