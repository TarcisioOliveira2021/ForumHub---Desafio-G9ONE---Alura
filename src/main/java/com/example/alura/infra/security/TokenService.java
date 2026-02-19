package com.example.alura.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.alura.domain.usuario.Usuario;

@Service
public class TokenService {

    @Value("${com.example.alura.security.secret}")
    private String secret;


    public String gerarToken(Usuario usuario){
        try{
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer("alura.forumhub").withSubject(usuario.getLogin()).withExpiresAt(dataExpiracao()).sign(algoritmo);
        }catch(JWTCreationException exp){
            throw new JWTCreationException("Erro ao criar token JWT",exp);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT){
        try{
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo).withIssuer("alura.forumhub").build().verify(tokenJWT).getSubject();
        }catch(JWTVerificationException exception){
            throw new RuntimeException("Erro ao verificar TokenJWT, Token inválido ou expirado.");
        }
    }
}
