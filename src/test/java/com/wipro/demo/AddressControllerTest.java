package com.wipro.demo;

import com.wipro.demo.controller.AddressController;
import com.wipro.demo.service.AddressService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AddressControllerTest {

    @InjectMocks
    private AddressController addressController;
    @Mock
    private AddressService addressService;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(addressController)
                .build();
    }

    @Test
    public void buscapCep() throws Exception {
        mockMvc.perform(post("/v1/consulta-endereco/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cep\" : \"01001000\"}"))
                .andExpect(status().isOk());

        Mockito.verify(addressService).findCep(Mockito.any());
    }
}
