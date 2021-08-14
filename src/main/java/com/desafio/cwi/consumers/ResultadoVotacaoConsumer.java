package com.desafio.cwi.consumers;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class ResultadoVotacaoConsumer {
	private static final String NOME_EXCHANGE = "amq.direct";
	public static final String RESULTADO_VOTACAO = "resultadoVotacao";
	
	  private AmqpAdmin amqpAdmin;

	  public ResultadoVotacaoConsumer(AmqpAdmin amqpAdmin){
	    this.amqpAdmin = amqpAdmin;
	  }

	  private Queue fila(String nomeFila){
	    return new Queue(nomeFila, true, false, false);
	  }

	  private DirectExchange trocaDireta() {
	    return new DirectExchange(NOME_EXCHANGE);
	  }

	  private Binding relacionamento(Queue fila, DirectExchange troca){
	    return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
	  }

	  @PostConstruct
	  private void adiciona(){
	    Queue resultadoVotacao = this.fila(RESULTADO_VOTACAO);

	    DirectExchange troca = this.trocaDireta();

	    Binding ligacaoResultado = this.relacionamento(resultadoVotacao, troca);

	    this.amqpAdmin.declareQueue(resultadoVotacao);

	    this.amqpAdmin.declareExchange(troca);

	    this.amqpAdmin.declareBinding(ligacaoResultado);
	  }
}
