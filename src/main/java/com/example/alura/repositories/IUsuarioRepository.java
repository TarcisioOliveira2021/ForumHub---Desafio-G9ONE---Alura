package com.example.alura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.alura.domain.usuario.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{

    UserDetails findByLogin(String login);

}
