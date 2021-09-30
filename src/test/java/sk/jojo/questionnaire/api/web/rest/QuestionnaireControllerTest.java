package sk.jojo.questionnaire.api.web.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import sk.jojo.mycommon.domain.error.EntityNotFoundException;
import sk.jojo.questionnaire.domain.dto.QuestionnaireDto;
import sk.jojo.questionnaire.domain.service.QuestionnaireService;

/**
 * The type Questionnaire controller test.
 */
@WebMvcTest(controllers = QuestionnaireController.class)
class QuestionnaireControllerTest {

  /**
   * The Mock mvc.
   */
  @Autowired
  private MockMvc mockMvc;

  /**
   * The Object mapper.
   */
  @Autowired
  private ObjectMapper objectMapper;

  /**
   * The Service mock.
   */
  @MockBean
  private QuestionnaireService serviceMock;

  /**
   * Test get.
   *
   * @throws Exception the exception
   */
  @Test
  void testGet() throws Exception {

    var expected = new QuestionnaireDto("First questionnaire", UUID.randomUUID().toString(), LocalDate.now());
    when(serviceMock.get(anyLong())).thenReturn(expected);

    var response = mockMvc.perform(get("/api/questionnaire/{id}", 1L)
                    .contentType("application/json"))
            .andExpect(status().isOk()).andReturn();
    var actual = objectMapper.readValue(response.getResponse().getContentAsString(), QuestionnaireDto.class);

    assertEquals(expected, actual);
  }

  /**
   * Test get should return 404.
   *
   * @throws Exception the exception
   */
  @Test
  void test_get_should_return_404() throws Exception {
    when(serviceMock.get(anyLong())).thenThrow(EntityNotFoundException.class);

    mockMvc.perform(get("/api/questionnaire/{id}", 1L)
                    .contentType("application/json"))
            .andExpect(status().isNotFound())
            .andExpect(result -> assertEquals(EntityNotFoundException.class, Objects.requireNonNull(result.getResolvedException()).getClass()));
  }

  /**
   * Test create.
   *
   * @throws Exception the exception
   */
  @Test
  void test_create() throws Exception {
    var expected = new QuestionnaireDto("First questionnaire", UUID.randomUUID().toString(), LocalDate.now());
    when(serviceMock.create(expected)).thenReturn(expected);

    mockMvc.perform(post("/api/questionnaire")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(expected)))
            .andExpect(status().isCreated())
            .andExpect(result -> assertEquals(expected, objectMapper.readValue(result.getResponse().getContentAsString(), QuestionnaireDto.class)));
  }

  /**
   * Test create should throw 400.
   *
   * @throws Exception the exception
   */
  @Test
  void test_create_should_throw_400() throws Exception {

    mockMvc.perform(post("/api/questionnaire")
                    .contentType("application/json"))
            .andExpect(status().isBadRequest())
            .andExpect(result -> assertEquals(HttpMessageNotReadableException.class, Objects.requireNonNull(result.getResolvedException()).getClass()));
  }

  /**
   * Test create should throw 400 validation error.
   *
   * @throws Exception the exception
   */
  @Test
  void test_create_should_throw_400_validation_error() throws Exception {
    var dto = new QuestionnaireDto();

    mockMvc.perform(post("/api/questionnaire")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(dto))
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(result -> assertEquals(HttpMessageNotReadableException.class, Objects.requireNonNull(result.getResolvedException()).getClass()));
  }

  /**
   * Test update.
   */
  @Test
  void test_update() {
  }

  /**
   * Test delete.
   */
  @Test
  void test_delete() {
  }
}
