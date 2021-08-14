package com.desafio.cwi.v2.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.dtos.ResultadoVotacaoDto;
import com.desafio.cwi.exceptions.ApiGenericException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RabbitMQService {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public void enviaResultadoVotacao(String nomeFila, ResultadoVotacaoDto votacaoPauta) {
		try {
			rabbitTemplate.convertAndSend(nomeFila, votacaoPauta);
			log.info("Mensagem com o resultado enviada com sucesso!");
			
		} catch (Exception e) {
			throw new ApiGenericException("Erro ao enviar a mensagem com o resultado!" + e.getMessage());
		}
	}
}
