package com.levimartines.workshopmongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.levimartines.workshopmongo.dto.UserDTO;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public class WorkshopmongoApplicationTests {

	@LocalServerPort
	int randomServerPort;

	private ObjectMapper MAPPER = new ObjectMapper();

 	@Test
	public void insertAuthorReturn201() throws URISyntaxException {
		UserDTO user = new UserDTO("Levi Martines","levi@gmail.com");
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://localhost:"+randomServerPort+"/users/";
		URI uri = new URI(baseUrl);

		ResponseEntity<?> result = restTemplate.postForEntity(uri, user, String.class);
		//Verify request succeed
		Assert.assertEquals(201, result.getStatusCodeValue());
	}

	@Test
	public void getUsuariosReturn201() throws URISyntaxException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://localhost:" + randomServerPort + "/users";
		ResponseEntity<String> response = restTemplate.getForEntity(new URI(baseUrl), String.class);

		List<UserDTO> users = MAPPER.readValue(response.getBody(),
			MAPPER.getTypeFactory().constructCollectionType(List.class, UserDTO.class));
		//Verify request succeed
		Assert.assertEquals(200, response.getStatusCodeValue());
		Assertions.assertThat(users.size()).isGreaterThan(0);
	}

}
