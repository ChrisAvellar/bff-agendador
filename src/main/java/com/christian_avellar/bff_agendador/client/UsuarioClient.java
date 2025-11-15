package com.christian_avellar.bff_agendador.client;


import com.christian_avellar.bff_agendador.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario" , url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTO buscaUsuarioPorEmail(@RequestParam("email") String email,
                                    @RequestHeader ("Authorization") String token);

    @PostMapping
   UsuarioDTO salvarUsuario(@RequestBody UsuarioDTO usuarioDTO);



    @PostMapping("/login")
     String login(@RequestBody UsuarioDTO usuarioDTO);


    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable  String email,
                                                      @RequestHeader("Authorization") String token);


}
