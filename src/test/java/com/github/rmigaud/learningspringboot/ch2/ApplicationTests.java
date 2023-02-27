package com.github.rmigaud.learningspringboot.ch2;

import com.github.rmigaud.learningspringboot.ch2.models.Book;
import com.github.rmigaud.learningspringboot.ch2.contollers.IndexController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

  @Autowired // Inject instance of IndexController at runtime.
  IndexController indexController;

  @Value("${local.server.port}")
  private int port;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  void contextLoads() {
    assertThat(indexController).isNotNull();
  }

  @Test
  void getIndexRequestProduces200() {
    ResponseEntity<String> response = testRestTemplate.getForEntity(
        "http://localhost:" + port + "/", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void getIndexResultContainsLearningSpringBoot() {
    ResponseEntity<String> response = testRestTemplate.getForEntity(
        "http://localhost:" + port + "/", String.class);

    assertThat(response.getBody()).contains("Learning Spring Boot");
  }

  @Test
  void postBookAPIReturnsNewBookWhenSuccessful() {
    ResponseEntity<String> response =
        testRestTemplate.postForEntity("http://localhost:" + port +
            "/api/books", new Book("new Title"), String.class);

    assertThat(response.getBody()).contains("new Title");
  }

  @Test
  void postBookAPIListsNewBookWhenSuccessful() {
    testRestTemplate.postForEntity("http://localhost:" + port +
        "/api/books", new Book("new Title"), Object.class);

    ResponseEntity<String> response = testRestTemplate.getForEntity(
        "http://localhost:" + port + "/api/books", String.class);

    assertThat(response.getBody()).contains("new Title");
  }
}
