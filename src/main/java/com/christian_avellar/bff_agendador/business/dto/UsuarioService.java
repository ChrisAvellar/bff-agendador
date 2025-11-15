package com.christian_avellar.bff_agendador.business.dto;



import com.christian_avellar.bff_agendador.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public  UsuarioDTO buscaUsuarioPorEmail (String email, String token){
        return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
        return usuarioClient.salvarUsuario(usuarioDTO);
    }

    public String loginUsuario(UsuarioDTO usuarioDTO) {
        return usuarioClient.login(usuarioDTO);
    }


    public void deletaUsuarioPorEmail(String email,  String token) {
        usuarioClient.deletaUsuarioPorEmail(email, token);

    }





}


