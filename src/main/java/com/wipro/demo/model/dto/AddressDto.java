package com.wipro.demo.model.dto;

import com.wipro.demo.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private String cep;
    private String rua;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private Double frete;

    public AddressDto(Address address, Double freteCalculado) {
        this.cep = address.getCep();
        this.rua = address.getLogradouro();
        this.complemento = address.getComplemento();
        this.bairro = address.getBairro();
        this.cidade = address.getLocalidade();
        this.estado = address.getUf();
        this.frete = freteCalculado;
    }

}
