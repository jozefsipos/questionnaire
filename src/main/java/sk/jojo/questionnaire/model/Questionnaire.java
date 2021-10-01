package sk.jojo.questionnaire.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import sk.jojo.mycommon.model.BaseEntity;

/**
 * The type Questionnaire.
 */
@Table(name = "questionnaire")
@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Questionnaire extends BaseEntity {

	/**
	 * The Url.
	 */
	@Column(name = "url", unique = true)
	private String url;

	/**
	 * The Name.
	 */
	@NonNull
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	/**
	 * The Title.
	 */
	@NonNull
	@Column(name = "title", nullable = false, unique = true)
	private String title;

	/**
	 * The Description.
	 */
	@Column(name = "description", length = 1200)
	private String description;

	/**
	 * The Effective period start.
	 */
	@NonNull
	@Column(name = "effective_period_start", nullable = false)
	private LocalDate effectivePeriodStart;

	/**
	 * The Effective period end.
	 */
	@Column(name = "effective_period_end")
	private LocalDate effectivePeriodEnd;

	/**
	 * The Questionnaire items.
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "questionnaire_id")
	private List<QuestionnaireItem> questionnaireItems;

}
