package com.example.alura.domain.topico.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.alura.domain.topico.DadosAtualizacaoTopico;
import com.example.alura.domain.topico.DadosCadastroTopico;
import com.example.alura.domain.topico.exceptions.ValidacaoException;
import com.example.alura.domain.topico.validacoes.interfaces.IValidadorTopicos;
import com.example.alura.repositories.ITopicoRepository;

import jakarta.transaction.Transactional;

@Component
public class ValidardorTopicosExistentes implements IValidadorTopicos{
    
    @Autowired
    private ITopicoRepository repository;


    @Override
    @Transactional
    public void validar(DadosCadastroTopico topico) {
        boolean topicoExistente = repository.existsByTituloAndMensagem(topico.titulo(), topico.mensagem());

        if(!topicoExistente){
            throw new ValidacaoException("O tópico criado já existe");
        }
    }

    @Override
    @Transactional
    public void validar(DadosAtualizacaoTopico topico) {
        boolean topicoExistente = repository.existsByTituloAndMensagem(topico.titulo(), topico.mensagem());

        if(!topicoExistente){
            throw new ValidacaoException("O tópico criado já existe");
        }
    }
}
