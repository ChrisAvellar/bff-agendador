package com.christian_avellar.bff_agendador.controller;


import com.christian_avellar.bff_agendador.business.dto.TarefaService;
import com.christian_avellar.bff_agendador.business.dto.TarefasDTO;
import com.christian_avellar.bff_agendador.business.enums.StatusNotificacaoEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas" , description = "Cadastra tarefas de usuários")
@SecurityRequirement(name = "SecurityConfig.SECURITY_SCHEME")
public class TarefaController {

    private final TarefaService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar tarefas de usuários", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200" , description = "tarefa salva com sucesso")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<TarefasDTO>gravarTarefa(@RequestBody TarefasDTO dto,
                                                  @RequestHeader( value = "Authorization" , required = false) String token){

        return ResponseEntity.ok(tarefasService.gravarTarefa(token,dto));
    }

    @Operation(summary = "Buscar tarefas por período", description = "Busca tarefas cadastradas por período")
    @ApiResponse(responseCode = "200" , description = "Tarefas encontradas ")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> listarTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
           @RequestHeader(name = "Authorization" , required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscaTarefasPorPeriodo(dataInicio, dataFim, token));
    }

    @Operation(summary = "Busca Tarefas por Email", description = "Busca tarefas cadastradas por Usuário")
    @ApiResponse(responseCode = "200" , description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    @GetMapping
    public  ResponseEntity<List<TarefasDTO>> buscaTarefasPorEmail(@RequestHeader("Authorization")String token){
        List<TarefasDTO> tarefas = tarefasService.buscaTarefasPorEmail(token);
        return ResponseEntity.ok(tarefas);
    }


    @DeleteMapping
    @Operation(summary = "Deleta tarefas por ID", description = "Deleta tarefas cadastradas por ID")
    @ApiResponse(responseCode = "200" , description = "Tarefas deletadas")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id")String id,
                                                 @RequestHeader(name = "Authorization" , required = false) String token){
        tarefasService.deletarTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status de tarefas ", description = "Altera status das tarefas cadastradas")
    @ApiResponse(responseCode = "200" , description = " status da tarefa alterado")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<TarefasDTO> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                              @RequestParam("id") String id,
                                                             @RequestHeader(name = "Authorization" , required = false) String token) {
        return ResponseEntity.ok(tarefasService.alteraStatusTarefa(status,id, token));
    }

    @PutMapping
    @Operation(summary = "Altera dados das tarefas", description = "Altera dados das tarefas cadastradas")
    @ApiResponse(responseCode = "200" , description = "Tarefas Alteradas")
    @ApiResponse(responseCode = "500" , description = "Erro de servidor")
    public ResponseEntity<TarefasDTO> updateTarefa(@RequestBody TarefasDTO dto, @RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization" , required = false) String token){
        return ResponseEntity.ok(tarefasService.updateTarefas(dto,id, token));
    }

}
