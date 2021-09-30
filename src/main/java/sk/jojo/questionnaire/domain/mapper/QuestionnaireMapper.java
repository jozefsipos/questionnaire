package sk.jojo.questionnaire.domain.mapper;

import org.mapstruct.Mapper;

import sk.jojo.mycommon.domain.mapper.BaseMapper;
import sk.jojo.questionnaire.domain.dto.QuestionnaireDto;
import sk.jojo.questionnaire.model.Questionnaire;

@Mapper(uses = { QuestionnaireItemMapper.class })
public interface QuestionnaireMapper extends BaseMapper<Questionnaire, QuestionnaireDto> {

}
