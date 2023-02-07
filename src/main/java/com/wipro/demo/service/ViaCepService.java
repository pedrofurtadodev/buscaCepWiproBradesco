package com.wipro.demo.service;

import com.wipro.demo.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface ViaCepService {
    @GetMapping("{cep}/json")
    Address findCep(@PathVariable("cep") String cep);
}
