package com.desafio.cwi.consumers;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class ResultVoteConsumer {
	private static final String NOME_EXCHANGE = "amq.direct";
	public static final String RESULT_VOTE = "resultVote";
	
	  private AmqpAdmin amqpAdmin;

	  public ResultVoteConsumer(AmqpAdmin amqpAdmin){
	    this.amqpAdmin = amqpAdmin;
	  }

	  private Queue queue(String nameQueue){
	    return new Queue(nameQueue, true, false, false);
	  }

	  private DirectExchange directExchange() {
	    return new DirectExchange(NOME_EXCHANGE);
	  }

	  private Binding binding(Queue queue, DirectExchange exchange){
	    return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
	  }

	  @PostConstruct
	  private void add(){
	    Queue resultVote = this.queue(RESULT_VOTE);

	    DirectExchange exchange = this.directExchange();

	    Binding binding = this.binding(resultVote, exchange);

	    this.amqpAdmin.declareQueue(resultVote);

	    this.amqpAdmin.declareExchange(exchange);

	    this.amqpAdmin.declareBinding(binding);
	  }
}
