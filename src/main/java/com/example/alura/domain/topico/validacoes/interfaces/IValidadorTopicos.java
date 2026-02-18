package com.example.alura.domain.topico.validacoes.interfaces;

import com.example.alura.domain.topico.DadosAtualizacaoTopico;
import com.example.alura.domain.topico.DadosCadastroTopico;

public interface IValidadorTopicos {
    default void validar(DadosCadastroTopico topico) {}
    default void validar(DadosAtualizacaoTopico dados) {}
}
