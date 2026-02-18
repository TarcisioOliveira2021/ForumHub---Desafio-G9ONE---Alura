package com.example.alura.domain.topico;

import java.time.LocalDateTime;

public record DadosTopicoAtualizados(
    Long id,
    String titulo,
    String mensagem,
    LocalDateTime data_criacao
) 

{
    public DadosTopicoAtualizados(Topico dados){
        this(
            dados.getId(), 
            dados.getTitulo(), 
            dados.getMensagem(),
            dados.getData_criacao()
        );
    }

}
