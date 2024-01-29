package amdeason.mixmuse;

import amdeason.mixmuse.cocktails.models.Cocktail;
import amdeason.mixmuse.cocktails.models.Recipe;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MixmuseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MixmuseApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;


	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port + "/api/v1/cocktails";
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetOneRecipe() {
		Recipe recipe = restTemplate.getForObject(getRootUrl() + "/1/recipe/1", Recipe.class);
		assertNotNull(recipe);
		assertEquals(recipe.getCocktail().getName(), "martini");
	}

	@Test
	public void testGetAllRecipesForCocktail() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/1/recipes", HttpMethod.GET, entity, String.class);

		Assert.assertNotNull(response.getBody());
		System.out.println(response.getBody());
	}
}
