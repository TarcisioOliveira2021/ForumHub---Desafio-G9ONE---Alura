package com.example.alura.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.alura.repositories.IUsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    private TokenService service;

    @Autowired
    private IUsuarioRepository repository;    

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        var tokenJWTRecuperado = recuperarTokenJWT(request);

        if(tokenJWTRecuperado != null){
            var tokenSubject = service.getSubject(tokenJWTRecuperado);
            var usuario = repository.findByLogin(tokenSubject);
            var authentication  = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        filterChain.doFilter(request, response);
    }

    private String recuperarTokenJWT(HttpServletRequest request) {
        var autorizationHeader = request.getHeader("Authorization");
        if(autorizationHeader != null){
            return autorizationHeader.replace("Bearer ","").trim();
        }
        
        return null;
    }

}
