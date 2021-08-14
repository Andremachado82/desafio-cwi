package com.desafio.cwi.services.voteTestes;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.desafio.cwi.exceptions.VoteNotFoundException;
import com.desafio.cwi.models.Schedule;
import com.desafio.cwi.models.Vote;
import com.desafio.cwi.repositories.VoteRepository;
import com.desafio.cwi.services.resultVote.ResultVoteByScheduleIdService;

@RunWith(MockitoJUnitRunner.class)
public class ResultadoVotacaoByPautaIdServiceTest {
	final String NAME_SCHEDULE = "Pauta 1";
	final String DESCRIPTION_SCHEDULE = "Descrição";
	
	@InjectMocks
	private ResultVoteByScheduleIdService resultadoVotacaoByPautaIdService;
	
	@Mock
	VoteRepository voteRepository;
	
	@Test(expected = VoteNotFoundException.class)
	public void deveOcorrerErroAoSalvarQuandoSessaoNãoExistir() {
		
		Vote vote = getVote();
		
		resultadoVotacaoByPautaIdService.getResultVote(vote.getSchedule().getId());
		
		when(voteRepository.findByScheduleId(vote.getSchedule().getId())).thenReturn(Optional.empty());
		
		assertThrows(VoteNotFoundException.class, () -> resultadoVotacaoByPautaIdService.getResultVote(vote.getSchedule().getId()));
	}
	
	public Vote getVote() {
		return Vote.builder()
				.id(new Random()
				.nextLong())
				.schedule(getSchedule())
				.build();
	}
	
	public Schedule getSchedule() {
		return Schedule.builder()
				.id(9l).name(NAME_SCHEDULE)
				.description(DESCRIPTION_SCHEDULE)
				.build();
	}
}
