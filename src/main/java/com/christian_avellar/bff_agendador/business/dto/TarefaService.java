package com.christian_avellar.bff_agendador.business.dto;


import com.christian_avellar.bff_agendador.business.enums.StatusNotificacaoEnum;
import com.christian_avellar.bff_agendador.client.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefasClient tarefasClient;


    public TarefasDTO gravarTarefa(String token, TarefasDTO dto) {
       return  tarefasClient.gravarTarefa(dto, token);
    }

    public List<TarefasDTO> buscaTarefasPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim,
                                                   String token) {
        return tarefasClient.listarTarefasPorPeriodo(dataInicio, dataFim, token);

    }

    public List<TarefasDTO> buscaTarefasPorEmail(String token) {

        return tarefasClient.buscaTarefasPorEmail(token);
    }

    public void deletarTarefaPorId(String id, String token) {
       tarefasClient.deletaTarefaPorId(id, token);
    }

    public TarefasDTO alteraStatusTarefa(StatusNotificacaoEnum status, String id,
                                         String token) {
       return tarefasClient.alteraStatusNotificacao(status, id, token);

    }

    public TarefasDTO updateTarefas(TarefasDTO dto, String id, String token) {
        return tarefasClient.updateTarefa(dto, id, token);

    }
}
