package sk.jojo.questionnaire.api.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sk.jojo.questionnaire.domain.dto.QuestionnaireDto;
import sk.jojo.questionnaire.domain.service.QuestionnaireService;

/**
 * The type Questionnaire controller.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(QuestionnaireController.RESOURCE)
public class QuestionnaireController implements QuestionnaireAPI {

  /**
   * The constant RESOURCE.
   */
  public static final String RESOURCE = "/api/questionnaire";

  /**
   * The Service.
   */
  private final QuestionnaireService service;

  /**
   * Get questionnaire dto.
   *
   * @param id the id
   * @return the questionnaire dto
   */
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @Override
  public QuestionnaireDto get(@PathVariable Long id) {
    return service.get(id);
  }

  /**
   * Create questionnaire dto.
   *
   * @param dto the dto
   * @return the questionnaire dto
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  @Override
  public QuestionnaireDto create(@RequestBody QuestionnaireDto dto) {
    return service.create(dto);
  }

  /**
   * Update questionnaire dto.
   *
   * @param id the id
   * @param dto the dto
   * @return the questionnaire dto
   */
  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @Override
  public QuestionnaireDto update(@PathVariable Long id, @RequestBody QuestionnaireDto dto) {
    return service.update(id, dto);
  }

  /**
   * Delete questionnaire dto.
   *
   * @param id the id
   * @return the questionnaire dto
   */
  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @Override
  public QuestionnaireDto delete(@PathVariable Long id) {
    return service.delete(id);
  }
}
