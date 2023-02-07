package com.wipro.demo.controller;

import com.wipro.demo.exception.BadRequestException;
import com.wipro.demo.exception.ResourceNotFoundException;
import com.wipro.demo.model.dto.AddressDto;
import com.wipro.demo.model.dto.BuscaCepRequest;
import com.wipro.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/v1/consulta-endereco/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<AddressDto> buscapCep(@RequestBody BuscaCepRequest request) throws ResourceNotFoundException, BadRequestException {
        AddressDto response = addressService.findCep(request);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
