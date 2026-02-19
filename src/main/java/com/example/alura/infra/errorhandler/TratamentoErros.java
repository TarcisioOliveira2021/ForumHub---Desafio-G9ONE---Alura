package com.example.alura.infra.errorhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.JWTCreationException;

import com.example.alura.infra.errorhandler.records.DadosErroValidacao;

import jakarta.persistence.EntityNotFoundException;


@RestControllerAdvice
public class TratamentoErros {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<?> tratarErroJWT(JWTCreationException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
