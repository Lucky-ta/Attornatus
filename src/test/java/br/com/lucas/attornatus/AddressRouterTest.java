package br.com.lucas.attornatus;

import br.com.lucas.attornatus.repository.AddressRepository;
import br.com.lucas.attornatus.repository.ClientRepository;
import br.com.lucas.attornatus.entity.Address;
import br.com.lucas.attornatus.entity.Client;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.util.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class AddressRouterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private AddressRepository addressRepository;

    private Client client;

    @BeforeEach
    public void setUp() {
        String testClientName = "Lucas";
        String testClientBirthDate = "22-07-2001";
        client = new Client(testClientName, testClientBirthDate);
    }

    @Test
	public void testCreateAddress() throws Exception {
		when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

		String testStreet = "123 Main St";
		String testZip = "10001";
		Integer testNumber = 30;
		String testCity = "New York";
		Boolean testMainAddress = true;
		Address address = new Address(testStreet, testZip, testNumber, testCity, testMainAddress, 1L);

		ObjectMapper mapper = new ObjectMapper();
		byte[] jsonContent = mapper.writeValueAsBytes(address);

		when(addressRepository.save(any(Address.class))).thenReturn(address);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/address/client/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonContent))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andReturn();

		String jsonResponse = result.getResponse().getContentAsString();
		Address createdAddress = mapper.readValue(jsonResponse, Address.class);

		assertEquals(testStreet, createdAddress.getStreet());
		assertEquals(testZip, createdAddress.getZipcode());
		assertEquals(testNumber, createdAddress.getNumber());
		assertEquals(testCity, createdAddress.getCity());
		assertEquals(testMainAddress, createdAddress.getMainAddress());
	}

    @Test
    public void testGetAddressById() throws Exception {
        long testAddressId = 1L;
        String testStreet = "123 Main St";
        String testZip = "10001";
        Integer testNumber = 30;
        String testCity = "New York";
        Boolean testMainAddress = true;

        Address address = new Address(testStreet, testZip, testNumber, testCity, testMainAddress, testAddressId);

        when(addressRepository.findById(testAddressId)).thenReturn(Optional.of(address));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/address/{id}", testAddressId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        Address returnedAddress = new ObjectMapper().readValue(jsonResponse, Address.class);

        assertEquals(testAddressId, returnedAddress.getId());
        assertEquals(testStreet, returnedAddress.getStreet());
        assertEquals(testZip, returnedAddress.getZipcode());
        assertEquals(testNumber, returnedAddress.getNumber());
        assertEquals(testCity, returnedAddress.getCity());
        assertEquals(testMainAddress, returnedAddress.getMainAddress());
    }
}
