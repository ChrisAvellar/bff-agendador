package com.christian_avellar.bff_agendador.controller;




import com.christian_avellar.bff_agendador.business.dto.UsuarioService;
import com.christian_avellar.bff_agendador.business.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário" , description = "Cadastro e login de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;



    @PostMapping
    @Operation(summary = "Salvar Usuários", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200" , description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400" , description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return  ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuários", description = "Login do usuário")
    @ApiResponse(responseCode = "200" , description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401" , description = "credenciais inválidas")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
      return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de Usuários por Email", description = "busca dados do usuário")
    @ApiResponse(responseCode = "200" , description = "Usuário encontrado")
    @ApiResponse(responseCode = "404" , description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<UsuarioDTO> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                          @RequestHeader(name = "Authorization" , required = false) String token) {
        return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar Usuários por ID", description = "Deleta usuário")
    @ApiResponse(responseCode = "200" , description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404" , description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable  String email,
                                                     @RequestHeader(name = "Authorization" , required = false) String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return  ResponseEntity.ok().build();
    }
}




