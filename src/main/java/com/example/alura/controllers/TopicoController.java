package com.example.alura.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.alura.domain.topico.DadosAtualizacaoTopico;
import com.example.alura.domain.topico.DadosCadastroTopico;
import com.example.alura.domain.topico.DadosDetalhamentoTopico;
import com.example.alura.domain.topico.DadosListagemTopico;
import com.example.alura.domain.topico.DadosTopicoAtualizados;
import com.example.alura.services.intefaces.ITopicoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private ITopicoService service;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody DadosCadastroTopico dados, UriComponentsBuilder uriBuilder){        
        var topicoCriado = service.cadastrar(dados);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topicoCriado.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosTopicoAtualizados(topicoCriado));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(sort = {"data_criacao"}) Pageable paginacao) {
        var page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoTopico dados) {
        var topicoAtualizado = service.atualizar(id, dados);
        return ResponseEntity.ok(new DadosTopicoAtualizados(topicoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> listarTopico(@PathVariable Long id) {
        var topicoRetornado = service.detalhar(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topicoRetornado));
    }
}
