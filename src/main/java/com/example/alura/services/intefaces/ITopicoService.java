package com.example.alura.services.intefaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.alura.domain.topico.DadosAtualizacaoTopico;
import com.example.alura.domain.topico.DadosCadastroTopico;
import com.example.alura.domain.topico.DadosListagemTopico;
import com.example.alura.domain.topico.Topico;

public interface ITopicoService {
    Topico cadastrar(DadosCadastroTopico topico);
    Page<DadosListagemTopico> listar(Pageable paginacao);
    Topico atualizar(Long id ,DadosAtualizacaoTopico dados);
    void deletar(Long id);
    Topico detalhar(Long id);
}
