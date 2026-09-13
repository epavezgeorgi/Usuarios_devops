package com.hackerfinder.backend.usuarios.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.hackerfinder.backend.usuarios.repository.UsuarioRepository;
import java.util.List;
import com.hackerfinder.backend.usuarios.model.Usuario;

@Service
@Transactional
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Usuario inicioSesion(String username, String password) {
        return usuarioRepository.validarCredenciales(username, password);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}
