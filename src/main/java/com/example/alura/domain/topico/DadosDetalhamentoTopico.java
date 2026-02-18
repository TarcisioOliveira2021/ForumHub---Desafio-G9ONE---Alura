package com.example.alura.domain.topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(
    Long id,
    String titulo,
    String mensagem,
    LocalDateTime data_criacao,
    String autor,
    String status
) 
{
    public DadosDetalhamentoTopico(Topico dados){
        this(
            dados.getId(),
            dados.getTitulo(),
            dados.getMensagem(),
            dados.getData_criacao(),
            dados.getAutor(),
            dados.getEstatus()
        );
    }

}
