package com.example.alura.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.alura.domain.usuario.DadosLogin;
import com.example.alura.domain.usuario.Usuario;
import com.example.alura.infra.security.DadosTokenJWT;
import com.example.alura.infra.security.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService serviceToken;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody DadosLogin dados) {
        var authtoken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var auteticado = manager.authenticate(authtoken);   
        var tokenJWT = serviceToken.gerarToken((Usuario) auteticado.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
    
}
