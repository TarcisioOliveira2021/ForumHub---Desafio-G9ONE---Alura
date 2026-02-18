package com.example.alura.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.alura.domain.topico.DadosAtualizacaoTopico;
import com.example.alura.domain.topico.DadosCadastroTopico;
import com.example.alura.domain.topico.DadosListagemTopico;
import com.example.alura.domain.topico.Topico;
import com.example.alura.domain.topico.validacoes.interfaces.IValidadorTopicos;
import com.example.alura.repositories.ITopicoRepository;
import com.example.alura.services.intefaces.ITopicoService;
import jakarta.transaction.Transactional;

@Service
public class TopicoService implements ITopicoService{

    @Autowired
    private ITopicoRepository repository;

    @Autowired
    private List<IValidadorTopicos> validadores;

    @Override
    @Transactional
    public Topico cadastrar(DadosCadastroTopico dados) {
        validadores.forEach(v -> v.validar(dados));
        //TODO: Tratamento de erros
        var topico = new Topico(null, dados.titulo(),dados.mensagem(),LocalDateTime.now(),"","",dados.curso());
        repository.save(topico);

        return topico;
    }

    @Override
    public Page<DadosListagemTopico> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemTopico::new);
    }

    @Override
    @Transactional
    public Topico atualizar(Long id, DadosAtualizacaoTopico dados) {
       validadores.forEach(v-> v.validar(dados));
       
       Optional<Topico> topicoRecuperado = repository.findById(id);
       if(topicoRecuperado.isPresent()){
         topicoRecuperado.get().atualizarDados(dados);
       }

       return topicoRecuperado.get();
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Topico detalhar(Long id) {
        return repository.getReferenceById(id);
    }

}
