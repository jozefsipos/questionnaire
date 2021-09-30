package sk.jojo.questionnaire.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sk.jojo.questionnaire.domain.dto.QuestionnaireDto;
import sk.jojo.questionnaire.domain.mapper.QuestionnaireMapper;
import sk.jojo.questionnaire.domain.repository.QuestionnaireRepository;

@Service
@Transactional
public class QuestionnaireService {

  private final QuestionnaireRepository repository;
  private final QuestionnaireMapper mapper;

  public QuestionnaireService(QuestionnaireRepository repository, QuestionnaireMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public QuestionnaireDto get(Long id) {
    return null;
  }

  public QuestionnaireDto create(QuestionnaireDto dto) {
    return null;
  }

  public QuestionnaireDto update(Long id, QuestionnaireDto dto) {
    return null;
  }

  public QuestionnaireDto delete(Long id) {
    return null;
  }
}
