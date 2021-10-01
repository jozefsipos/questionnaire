package sk.jojo.questionnaire.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import sk.jojo.mycommon.domain.mapper.BaseMapper;
import sk.jojo.questionnaire.domain.dto.QuestionnaireItemDto;
import sk.jojo.questionnaire.model.QuestionnaireItem;

@Mapper
public interface QuestionnaireItemMapper extends BaseMapper<QuestionnaireItem, QuestionnaireItemDto> {

	@Override
	@Mapping(target = "questionnaireItems", expression = "java(source.getQuestionnaireItems() != null && source.getQuestionnaireItems().isEmpty() ? null : toDtos(source.getQuestionnaireItems()))")
	QuestionnaireItemDto toDto(QuestionnaireItem source);
}
