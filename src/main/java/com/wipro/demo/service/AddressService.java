package com.wipro.demo.service;

import com.wipro.demo.exception.BadRequestException;
import com.wipro.demo.exception.ResourceNotFoundException;
import com.wipro.demo.model.Address;
import com.wipro.demo.model.dto.AddressDto;
import com.wipro.demo.model.dto.BuscaCepRequest;
import com.wipro.demo.repository.FreightRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private FreightRateRepository freightRateRepository;

    public AddressDto findCep(BuscaCepRequest cepRequest) throws ResourceNotFoundException, BadRequestException {
        verificaFormatacaoCep(cepRequest.getCep());
        Address address = viaCepService.findCep(cepRequest.getCep().toString());
        verificaSeExiste(address);
        Double frete = calculaFrete(address.getUf());
        return new AddressDto(address, frete);
    }

    private boolean verificaSeExiste(Address address) throws ResourceNotFoundException {
        if (address.getCep() != null) {
            return true;
        } else {
            throw new ResourceNotFoundException("CEP não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    private boolean verificaFormatacaoCep(String cep) throws BadRequestException {
        String cepFormatado = cep.replace("-", "");
        if (cepFormatado.length() == 8) {
            Integer.valueOf(cepFormatado);
            return true;
        }
        throw new BadRequestException("Formato de CEP inválido", HttpStatus.BAD_REQUEST);
    }

    private Double calculaFrete(String uf) {
        Double freteCalculado = freightRateRepository.findByState(uf);
        return freteCalculado;
    }

}
