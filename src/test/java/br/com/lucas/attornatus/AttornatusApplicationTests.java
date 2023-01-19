package br.com.lucas.attornatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
}
