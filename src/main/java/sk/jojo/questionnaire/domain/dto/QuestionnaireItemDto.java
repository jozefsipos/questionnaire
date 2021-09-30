package sk.jojo.questionnaire.domain.dto;

import javax.validation.constraints.NotEmpty;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * The type Questionnaire item dto.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionnaireItemDto {

    /**
     * The Link id.
     */
    @NonNull
    @NotEmpty
    private String linkID;

    /**
     * The Text.
     */
    private String text;

    /**
     * The Required.
     */
    private Boolean required;

    /**
     * The Questionnaire item type.
     */
    @NonNull
    @NotEmpty
    private String questionnaireItemType;

    /**
     * The Items.
     */
    private List<QuestionnaireItemDto> items;
}
