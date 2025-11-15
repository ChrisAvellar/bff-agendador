package com.christian_avellar.bff_agendador.client.client;


import com.christian_avellar.bff_agendador.business.dto.TarefasDTO;
import com.christian_avellar.bff_agendador.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDTO gravarTarefa(@RequestBody TarefasDTO dto,
                            @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefasDTO> listarTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestHeader("Authorization") String token);


    @GetMapping
    List<TarefasDTO> buscaTarefasPorEmail(@RequestHeader("Authorization") String token);


    @DeleteMapping
    void deletaTarefaPorId(@RequestParam("id") String id, @RequestHeader("Authorization") String token);


    @PatchMapping
    TarefasDTO alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                       @RequestParam("id") String id,
                                       @RequestHeader("Authorization") String token);


    @PutMapping
    TarefasDTO updateTarefa(@RequestBody TarefasDTO dto, @RequestParam("id") String id,
                            @RequestHeader("Authorization") String token);


}
