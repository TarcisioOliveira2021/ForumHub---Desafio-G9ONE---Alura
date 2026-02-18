package com.example.alura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.alura.domain.topico.Topico;

public interface ITopicoRepository extends JpaRepository<Topico, Long>{
    
    @Query("SELECT t FROM Topico t WHERE t.mensagem = :mensagem AND t.titulo = :titulo")
    boolean existsByTituloAndMensagem(String titulo, String mensagem);


}
