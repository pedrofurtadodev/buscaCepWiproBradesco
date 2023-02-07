package com.wipro.demo;

import com.wipro.demo.exception.BadRequestException;
import com.wipro.demo.exception.ResourceNotFoundException;
import com.wipro.demo.model.Address;
import com.wipro.demo.model.dto.AddressDto;
import com.wipro.demo.model.dto.BuscaCepRequest;
import com.wipro.demo.repository.FreightRateRepository;
import com.wipro.demo.service.AddressService;
import com.wipro.demo.service.ViaCepService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;
    @Mock
    private ViaCepService viaCepService;
    @Mock
    private FreightRateRepository freightRateRepository;


    @Before
    public void setup() {

    }

    @Test
    public void findCepValidoComHifen() throws BadRequestException, ResourceNotFoundException {
        BuscaCepRequest request = new BuscaCepRequest("01001-000");
        AddressDto responseDto = new AddressDto("01001000", "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP", 7.85);
        Address responseViaCep = new Address("01001000", "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");

        Mockito.when(viaCepService.findCep(Mockito.anyString())).thenReturn(responseViaCep);
        Mockito.when(freightRateRepository.findByState(Mockito.anyString())).thenReturn(7.85);

        AddressDto responseTest = addressService.findCep(request);

        assertThat(responseDto).isEqualTo(responseTest);
    }

    @Test
    public void findCepValidoSemHifen() throws BadRequestException, ResourceNotFoundException {
        BuscaCepRequest request = new BuscaCepRequest("01001000");
        AddressDto responseDto = new AddressDto("01001000", "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP", 7.85);
        Address responseViaCep = new Address("01001000", "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");

        Mockito.when(viaCepService.findCep(Mockito.anyString())).thenReturn(responseViaCep);
        Mockito.when(freightRateRepository.findByState(Mockito.anyString())).thenReturn(7.85);

        AddressDto responseTest = addressService.findCep(request);

        assertThat(responseDto).isEqualTo(responseTest);
    }

    @Test(expected = BadRequestException.class)
    public void findCepInvalido() throws BadRequestException, ResourceNotFoundException {
        BuscaCepRequest request = new BuscaCepRequest("0100100003");

        AddressDto responseTest = addressService.findCep(request);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findCepNaoExistente() throws BadRequestException, ResourceNotFoundException {
        BuscaCepRequest request = new BuscaCepRequest("96810036");
        Address responseViaCep = new Address(null, null, null, null, null, null);

        Mockito.when(viaCepService.findCep(Mockito.anyString())).thenReturn(responseViaCep);

        AddressDto responseTest = addressService.findCep(request);
    }

}
