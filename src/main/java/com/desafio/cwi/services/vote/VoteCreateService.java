package com.desafio.cwi.services.vote;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.client.CpfValidationClient;
import com.desafio.cwi.enums.AnswerEnum;
import com.desafio.cwi.exceptions.ApiGenericException;
import com.desafio.cwi.exceptions.CpfNotFoundException;
import com.desafio.cwi.exceptions.PautaNotFoundException;
import com.desafio.cwi.exceptions.SessionNotFoundException;
import com.desafio.cwi.exceptions.SessionExperidException;
import com.desafio.cwi.exceptions.UnableCpfException;
import com.desafio.cwi.models.Session;
import com.desafio.cwi.models.Vote;
import com.desafio.cwi.repositories.ScheduleRepository;
import com.desafio.cwi.repositories.SessionRepository;
import com.desafio.cwi.repositories.VoteRepository;
import com.desafio.cwi.utils.FormatCPF;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j 
public class VoteCreateService {

	private static final String CPF_UNABLE_TO_VOTE = "UNABLE_TO_VOTE";

	@Autowired
	VoteRepository voteRepository;

	@Autowired
	ScheduleRepository scheduleRepository;

	@Autowired
	SessionRepository sessionRepository;

	@Autowired
	CpfValidationClient cpfValidationClient;
	
	public Vote execute(Long idSession, Vote vote) {
		if (vote.getSchedule() != null) {
			vote.setSchedule(scheduleRepository.findById(vote.getSchedule().getId())
					.orElseThrow(() -> new PautaNotFoundException("Pauta não encontrada!")));
		}
		var session = new Session();
		if (idSession != null) {
			session = sessionRepository.findById(idSession)
					.orElseThrow(() -> new SessionNotFoundException("Sessão não encontrada!"));
		}
		verifyVote(session, vote);
		voteRepository.save(vote);		
		log.info("Voto realizado com sucesso!");
		return vote;
	}

	private void verifyVote(Session session, Vote vote) {
		if (vote.getAnswer() == null) {
			throw new ApiGenericException("Resposta do voto não pode ser nula");
		}
		var deadline = session.getStartDateTime().plusMinutes(session.getSessionTime());
		if (LocalDateTime.now().isAfter(deadline)) {
			throw new SessionExperidException("Sessão expirada");
		}
		cpfAbleToVote(vote.getCpf());
		verifyCpfExistsOnSchedule(vote.getCpf(), vote.getSchedule().getId());
	}

	private void verifyCpfExistsOnSchedule(String cpf, Long idPauta) {
		Optional<Vote> voteExists = voteRepository.findByCpfAndScheduleId(cpf, idPauta);
		if (voteExists.isPresent()) {
			throw new ApiGenericException("Já existe um voto registrado nesta Pauta de nº " + idPauta
					+ " com o CPF " + cpf);
		}
	}

	private void cpfAbleToVote(String cpf) {
		if (cpf == null || cpf.isBlank()) {
			throw new CpfNotFoundException("CPF deve ser informado!");
		}
		String cpfFormated = FormatCPF.formatCpf(cpf);
		try {
			var cpfResponse = cpfValidationClient.findUserByCpf(cpfFormated);
			if (CPF_UNABLE_TO_VOTE.equals(cpfResponse.getStatus())) {
				throw new UnableCpfException("CPF sem permissão para voto");
			}
		} catch (FeignException e) {
			throw new ApiGenericException("Ocorreu um erro, tente novamente mais tarde!" + e.getMessage());
		}
	}
	
	public Boolean verifyAnswer(String answer) {
		if (answer.equals(AnswerEnum.SIM.getAnswer())) {
			return true;
		} else if (answer.equals(AnswerEnum.NAO.getAnswer())) {
			return false;
		} else {
			throw new ApiGenericException("A resposta deve ser Sim ou Não");
		}
	}
}
