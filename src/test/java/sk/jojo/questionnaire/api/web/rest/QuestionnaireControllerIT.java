package sk.jojo.questionnaire.api.web.rest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import sk.jojo.mycommon.test.BaseTest;
import sk.jojo.questionnaire.domain.dto.QuestionnaireDto;
import sk.jojo.questionnaire.domain.dto.QuestionnaireItemDto;

/**
 * The type Questionnaire controller it.
 */
@SpringBootTest(classes = QuestionnaireControllerIT.QuestionnaireControllerITConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuestionnaireControllerIT extends BaseTest {

    /**
     * The Questionnaire.
     */
    QuestionnaireDto questionnaire = null;

    /**
     * Test create questionnaire.
     */
    @Test
    @Order(1)
    void test_create_questionnaire() {
        // Given
        var dto = new QuestionnaireDto(UUID.randomUUID().toString(), UUID.randomUUID().toString(), LocalDate.now());

        // send request
        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        var httpEntity = new HttpEntity<>(dto, headers);
        var response = getRestTemplate().exchange(createURL(QuestionnaireController.RESOURCE), HttpMethod.POST, httpEntity,
                new ParameterizedTypeReference<QuestionnaireDto>() {
                });

        // Then
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());

        questionnaire = response.getBody();
    }

    /**
     * Test get questionnaire.
     */
    @Test
    @Order(2)
    void test_get_questionnaire() {
        // send request
        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        var httpEntity = new HttpEntity<>(null, headers);
        var response = getRestTemplate().exchange(createURL(QuestionnaireController.RESOURCE) + "/" + questionnaire.getId(), HttpMethod.GET, httpEntity,
                new ParameterizedTypeReference<QuestionnaireDto>() {
                });

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(questionnaire, response.getBody());
    }

    /**
     * Test update questionnaire add questionnaire items.
     */
    @Test
    @Order(3)
    void test_update_questionnaire_add_questionnaire_items() {
        questionnaire.setQuestionnaireItems(new ArrayList<>());

        var item = new QuestionnaireItemDto(UUID.randomUUID().toString(), "What's your name?", true, "string", null);
        item.setQuestionnaireItems(List.of(new QuestionnaireItemDto(UUID.randomUUID().toString(), "Another question?", true, "string", null)));

        questionnaire.getQuestionnaireItems().add(item);

        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        var httpEntity = new HttpEntity<>(questionnaire, headers);
        var response = getRestTemplate().exchange(createURL(QuestionnaireController.RESOURCE) + "/" + questionnaire.getId(), HttpMethod.PUT, httpEntity,
                new ParameterizedTypeReference<QuestionnaireDto>() {
                });

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(questionnaire, response.getBody());
    }

    /**
     * Test delete questionniare.
     */
    @Test
    @Order(4)
    void test_delete_questionniare() {

        var response = getRestTemplate().exchange(createURL(QuestionnaireController.RESOURCE) + "/" + questionnaire.getId(), HttpMethod.DELETE,
                new HttpEntity<QuestionnaireDto>(null, new HttpHeaders()), new ParameterizedTypeReference<QuestionnaireDto>() {
                });

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(questionnaire, response.getBody());
    }

    /**
     * Test get after delete.
     */
    @Test
    @Order(5)
    void test_get_after_delete() {

        assertThrows(RuntimeException.class, () -> getRestTemplate().exchange(createURL(QuestionnaireController.RESOURCE) + "/" + questionnaire.getId(),
                HttpMethod.GET, new HttpEntity<QuestionnaireDto>(null, null), new ParameterizedTypeReference<QuestionnaireDto>() {
                }));
    }

    /**
     * The type Questionnaire controller it configuration.
     */
    @Configuration
    @ComponentScan("sk.jojo.questionnaire")
    static class QuestionnaireControllerITConfiguration {
    }
}
