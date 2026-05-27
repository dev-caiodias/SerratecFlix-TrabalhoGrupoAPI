package com.SerratecFlix.trabalhoApi.Service;

import com.SerratecFlix.trabalhoApi.Domain.Usuario;
import com.SerratecFlix.trabalhoApi.Dto.Request.UsuarioDTORequest;
import com.SerratecFlix.trabalhoApi.Dto.Response.UsuarioDTOResponse;
import com.SerratecFlix.trabalhoApi.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario toUsuario(UsuarioDTORequest request){
        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setUserName(request.getUserName());
        usuario.setSenha(request.getSenha());

        return usuario;
    }

    public UsuarioDTOResponse toUsuarioResponse(Usuario usuario){
        return new UsuarioDTOResponse(usuario);
    }

    public List<UsuarioDTOResponse> listarTodos(){
        return usuarioRepository.findAll()
                .stream()
                .map(this :: toUsuarioResponse)
                .toList();
    }

    public UsuarioDTOResponse buscarPorId(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResorceNotFoundException("Usuario não foi encontrado"));
        return toUsuarioResponse(usuario);
    }

    public UsuarioDTOResponse criar (UsuarioDTORequest request){
        if(usuarioRepository.existsByUsername(request.getUserName())){
            throw new ConflictException("Username já está em uso:");
        }
        if(usuarioRepository.existsByEmail(request.getEmail())){
            throw new ConflictException("Email já está em uso");
        }

        Usuario usuario = Usuario.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .userName(request.getUserName())
                .senha(passwordEncoder.encode(request.getSenha()))
                .build();

        return toUsuarioResponse(usuarioRepository.save(usuario));
    }

    public UsuarioDTOResponse atualizar(Long id, UsuarioDTORequest request){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResorceNotFoundException("Usuario não encontrado."));

        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setUserName(request.getUserName());

        if (request.getSenha() != null && !request.getSenha().isBlank()){
            usuario.setSenha(passwordEncoder.encode(request.getSenha());
        }

        return toUsuarioResponse(usuarioRepository.save(usuario));
    }

    public void deletar(Long id){
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com id: " + id);
        }

        usuarioRepository.deleteById(id);
    }

}
