package br.com.lucas.attornatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lucas.attornatus.entity.Client;
import br.com.lucas.attornatus.repository.ClientRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class AttornatusApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClientRepository clientRepository;

	@Test
	public void testCreateClient() throws Exception {
		String testClientName = "Lucas";
		String testClientBirthDate = "22-07-2001";
		Client client = new Client(testClientName, testClientBirthDate);

		ObjectMapper mapper = new ObjectMapper();
		byte[] jsonContent = mapper.writeValueAsBytes(client);

		when(clientRepository.save(any(Client.class))).thenReturn(client);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/client")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonContent))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andReturn();

		String jsonResponse = result.getResponse().getContentAsString();
		Client createdClient = mapper.readValue(jsonResponse, Client.class);

		assertEquals("Lucas", createdClient.getName());
		assertEquals("22-07-2001", createdClient.getBirthdate());
	}

	@Test
	public void testGetClientById() throws Exception {
		String testClientName = "Lucas";
		String testClientBirthDate = "22-07-2001";
		Client client = new Client(testClientName, testClientBirthDate);

		when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

		// Realizando a requisição GET
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/client/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		// Convertendo a resposta em JSON para objeto
		String jsonResponse = result.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		Client returnedClient = mapper.readValue(jsonResponse, Client.class);

		// Verificando se os dados do client retornado estão corretos
		assertEquals(client.getName(), returnedClient.getName());
		assertEquals(client.getBirthdate(), returnedClient.getBirthdate());
	}
}
