package sk.jojo.questionnaire.domain.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * The type Questionnaire dto.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionnaireDto {

    /**
     * The Id.
     */
    private Long id;

    /**
     * The Url.
     */
    private String url;

    /**
     * The Name.
     */
    @NonNull
    @NotEmpty
    private String name;

    /**
     * The Title.
     */
    @NonNull
    @NotEmpty
    private String title;

    /**
     * The Description.
     */
    private String description;

    /**
     * The Effective period start.
     */
    @NonNull
    @NotNull
    private LocalDate effectivePeriodStart;

    /**
     * The Effective period end.
     */
    private LocalDate effectivePeriodEnd;

    /**
     * The Items.
     */
    private List<QuestionnaireItemDto> items;
}
