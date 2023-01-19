package br.com.lucas.attornatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lucas.attornatus.entity.Address;
import br.com.lucas.attornatus.entity.Client;
import br.com.lucas.attornatus.repository.AddressRepository;
import br.com.lucas.attornatus.repository.ClientRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class AttornatusApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClientRepository clientRepository;

	@MockBean
	private AddressRepository addressRepository;

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

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/client/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		String jsonResponse = result.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		Client returnedClient = mapper.readValue(jsonResponse, Client.class);

		assertEquals(client.getName(), returnedClient.getName());
		assertEquals(client.getBirthdate(), returnedClient.getBirthdate());
	}

	@Test
	public void testGetAllClients() throws Exception {
		List<Client> clients = Arrays.asList(
				new Client("Lucas", "22-07-2001"),
				new Client("Attornatus", "22-07-2001"));
		when(clientRepository.findAll()).thenReturn(clients);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/client"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		String jsonResponse = result.getResponse().getContentAsString();

		ObjectMapper mapper = new ObjectMapper();
		List<Client> returnedClients = mapper.readValue(jsonResponse, new TypeReference<List<Client>>() {
		});

		assertEquals(2, returnedClients.size());
		assertEquals("Lucas", returnedClients.get(0).getName());
		assertEquals("22-07-2001", returnedClients.get(0).getBirthdate());
		assertEquals("Attornatus", returnedClients.get(1).getName());
		assertEquals("22-07-2001", returnedClients.get(1).getBirthdate());

	}

	@Test
	public void testUpdateClient() throws Exception {
		Client originalClient = new Client("Lucas", "22-07-2001");
		when(clientRepository.findById(1L)).thenReturn(Optional.of(originalClient));

		Client updatedClient = new Client("Maieski", "22-07-2001");
		when(clientRepository.save(updatedClient)).thenReturn(updatedClient);

		ObjectMapper mapper = new ObjectMapper();
		byte[] jsonContent = mapper.writeValueAsBytes(updatedClient);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/client/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonContent))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		String jsonResponse = result.getResponse().getContentAsString();
		Client returnedClient = mapper.readValue(jsonResponse, Client.class);

		assertEquals(updatedClient.getName(), returnedClient.getName());
		assertEquals(updatedClient.getBirthdate(), returnedClient.getBirthdate());
	}

	@Test
	public void testCreateAddress() throws Exception {
		Client testClient = new Client("Lucas", "22-07-2001");
		when(clientRepository.findById(1L)).thenReturn(Optional.of(testClient));

		String testStreet = "123 Main St";
		String testZip = "10001";
		Integer testNumber = 30;
		String testCity = "New York";
		Boolean testMainAddress = true;
		Address address = new Address(testStreet, testZip, testNumber, testCity, testMainAddress);

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
}
