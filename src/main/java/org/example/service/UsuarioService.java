package org.example.service;

import org.example.model.Usuario;
import org.example.repository.UsuarioRepository;
import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll() throws SQLException {
        return usuarioRepository.findAll();
    }

    public int save(Usuario usuario) {
        String plainTextPassword = usuario.getContraseña();
        String hashedPassword = BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
        usuario.setContraseña(hashedPassword);
        return usuarioRepository.save(usuario);
    }

    public boolean update(int id_usuario, Usuario usuario) {
        if (usuario.getContraseña() != null && !usuario.getContraseña().isEmpty()) {
            String plainTextPassword = usuario.getContraseña();
            String hashedPassword = BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
            usuario.setContraseña(hashedPassword);
        }
        return usuarioRepository.update(id_usuario, usuario);
    }

    public boolean delete(int id_usuario) {
        return usuarioRepository.delete(id_usuario);
    }

    public Usuario authenticate(String correo, String plainTextPassword) throws SQLException {
        Usuario usuario = usuarioRepository.findByCorreo(correo);

        if (usuario != null) {
            if (BCrypt.checkpw(plainTextPassword, usuario.getContraseña())) {
                return usuario;
            }
        }
        return null;
    }
}