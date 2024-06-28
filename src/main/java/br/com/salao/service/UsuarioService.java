package br.com.salao.service;

import br.com.salao.entity.Usuario;
import br.com.salao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
    }


    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setName(usuarioAtualizado.getName());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setPassword(usuarioAtualizado.getPassword());
            usuario = usuarioRepository.save(usuario);
        }
        return usuario;
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
