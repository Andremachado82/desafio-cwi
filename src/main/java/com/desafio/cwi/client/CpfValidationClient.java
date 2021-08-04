package com.desafio.cwi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.desafio.cwi.responses.CpfValidationResponse;

@FeignClient(url="${client.applicationName.url.cpf}", name="cpf")
public interface CpfValidationClient {
	
	@GetMapping("/{cpf}")
	CpfValidationResponse findUserByCpf(@PathVariable String cpf);
}
