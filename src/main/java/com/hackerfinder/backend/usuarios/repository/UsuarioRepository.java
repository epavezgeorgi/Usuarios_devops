package com.hackerfinder.backend.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.hackerfinder.backend.usuarios.model.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByIdUsuario(Long idUsuario);
    public Usuario findByUsername(String username);

    @Query(value = "SELECT * FROM usuario WHERE username = ?1 AND contrasena = ?2", nativeQuery = true)
    public Usuario validarCredenciales(String username, String contrasena);
}