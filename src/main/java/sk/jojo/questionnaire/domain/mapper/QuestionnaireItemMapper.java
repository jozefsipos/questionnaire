package sk.jojo.questionnaire.domain.mapper;

import org.mapstruct.Mapper;

import sk.jojo.mycommon.domain.mapper.BaseMapper;
import sk.jojo.questionnaire.domain.dto.QuestionnaireItemDto;
import sk.jojo.questionnaire.model.QuestionnaireItem;

@Mapper
public interface QuestionnaireItemMapper extends BaseMapper<QuestionnaireItem, QuestionnaireItemDto> {

}
