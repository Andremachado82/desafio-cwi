package com.desafio.cwi.services.voto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.SessaoVotacao;
import com.desafio.cwi.models.Voto;
import com.desafio.cwi.repositories.VotoRepository;
import com.desafio.cwi.services.exceptions.ObjectNotFoundException;
import com.desafio.cwi.services.sessao.SessaoVotacaoGetByIdService;

@Service
public class VotoCreateService {
	
	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
	SessaoVotacaoGetByIdService sessaoVotacaoGetByIdService;
	
	public Voto create(Long idPauta, Long idSessao, Voto voto) {
		SessaoVotacao sessao = sessaoVotacaoGetByIdService.findByIdAndPautaId(idSessao, idPauta);
		if (!idPauta.equals(sessao.getPauta().getId())) {
			throw new ObjectNotFoundException("Sessão inválida");
		}
		
		voto.setPauta(sessao.getPauta());
		
		return votoRepository.save(voto);
	}
}
