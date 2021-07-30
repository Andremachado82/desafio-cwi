package com.desafio.cwi.recursos;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url="https://user-info.herokuapp.com/users/", name="cpf")
@FeignClient(url="${client.applicationName.url.cpf}", name="cpf")
public interface CpfValidationClient {
	
	@GetMapping("/{cpf}")
	CpfValidationResponse getCpf(@PathVariable String cpf);

}
