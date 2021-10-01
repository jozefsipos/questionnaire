package sk.jojo.questionnaire.api.web.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import sk.jojo.questionnaire.domain.dto.QuestionnaireDto;

@Validated
public interface QuestionnaireAPI {

	QuestionnaireDto get(Long id);

	QuestionnaireDto create(@Valid QuestionnaireDto dto);

	QuestionnaireDto update(Long id, QuestionnaireDto dto);

	QuestionnaireDto delete(Long id);
}
