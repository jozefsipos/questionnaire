package sk.jojo.questionnaire.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import sk.jojo.mycommon.domain.error.EntityNotFoundException;
import sk.jojo.questionnaire.domain.dto.QuestionnaireDto;
import sk.jojo.questionnaire.domain.mapper.QuestionnaireMapper;
import sk.jojo.questionnaire.domain.repository.QuestionnaireRepository;

/**
 * The type Questionnaire service.
 */
@Service
@Transactional
public class QuestionnaireService {

	/**
	 * The Repository.
	 */
	private final QuestionnaireRepository repository;
	/**
	 * The Mapper.
	 */
	private final QuestionnaireMapper mapper;

	/**
	 * Instantiates a new Questionnaire service.
	 *
	 * @param repository the repository
	 * @param mapper     the mapper
	 */
	public QuestionnaireService(QuestionnaireRepository repository, QuestionnaireMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	/**
	 * Get questionnaire dto.
	 *
	 * @param id the id
	 * @return the questionnaire dto
	 */
	public QuestionnaireDto get(Long id) {
		var questionnaire = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(this.getClass()));

		return mapper.toDto(questionnaire);
	}

	/**
	 * Create questionnaire dto.
	 *
	 * @param dto the dto
	 * @return the questionnaire dto
	 */
	public QuestionnaireDto create(QuestionnaireDto dto) {
		Assert.notNull(dto, "QuestionnaireDto == null");

		var questionnaire = repository.save(mapper.toEntity(dto));

		return mapper.toDto(questionnaire);
	}

	/**
	 * Update questionnaire dto.
	 *
	 * @param id  the id
	 * @param dto the dto
	 * @return the questionnaire dto
	 */
	public QuestionnaireDto update(Long id, QuestionnaireDto dto) {
		Assert.notNull(dto, "QuestionnaireDto == null");

		if (!repository.existsById(id)) {
			throw new EntityNotFoundException(this.getClass());
		}

		dto.setId(id);

		var questionnaire = repository.save(mapper.toEntity(dto));

		return mapper.toDto(questionnaire);
	}

	/**
	 * Delete questionnaire dto.
	 *
	 * @param id the id
	 * @return the questionnaire dto
	 */
	public QuestionnaireDto delete(Long id) {

		var questionnaire = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(this.getClass()));

		repository.delete(questionnaire);

		return mapper.toDto(questionnaire);
	}
}
