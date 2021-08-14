package com.desafio.cwi.services.resultVote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.dtos.ResultVoteDto;
import com.desafio.cwi.exceptions.VoteNotFoundException;
import com.desafio.cwi.models.Vote;
import com.desafio.cwi.repositories.SessionRepository;
import com.desafio.cwi.repositories.VoteRepository;
import com.desafio.cwi.v2.services.RabbitMQService;

@Service
public class ResultVoteByScheduleIdService {
	public static final String RESULT_VOTE = "resultVote";
	
	@Autowired
	VoteRepository voteRepository;
	
	@Autowired
	RabbitMQService rabbitMQService;
	
	public ResultVoteDto getResultVote(Long idSchedule){
		ResultVoteDto resultVoteDto = buildDtoVote(idSchedule);	
		rabbitMQService.enviaResultadoVotacao(RESULT_VOTE, resultVoteDto);
		return resultVoteDto;
	}	

	public ResultVoteDto buildDtoVote(Long idSchedule) {
		 List<Vote> votesBySchedule = voteRepository.findByScheduleId(idSchedule)
				    .orElseThrow(() -> new VoteNotFoundException("Voto não encontrado!"));
		var schedule = votesBySchedule.iterator().next().getSchedule();
		Integer total = votesBySchedule.size();
		Integer totalSim = calculateTotalVotesSim(votesBySchedule);
		Integer totalNao = calculateTotalVotesNao(total, totalSim);
		return ResultVoteDto.builder()
				.schedule(schedule)
				.totalVotes(total)
				.totalSim(totalSim).totalNao(totalNao)
				.build();
	}
	
	public Integer calculateTotalVotesNao(Integer total, Integer totalSim) {
		return total - totalSim;
	}
	
	public Integer calculateTotalVotesSim(List<Vote> votesBySchedule) {
		return (int) votesBySchedule.stream()
		.filter(vote -> Boolean.TRUE.equals(vote.getAnswer()))
		.count();
	}
}
