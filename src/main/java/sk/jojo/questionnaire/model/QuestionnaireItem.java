package sk.jojo.questionnaire.model;

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
 * The type Questionnaire item.
 */
@Table(name = "questionnaire_item")
@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class QuestionnaireItem extends BaseEntity {

	/**
	 * The Link id.
	 */
	@NonNull
	@Column(name = "link_id", nullable = false)
	private String linkID;

	/**
	 * The Text.
	 */
	@NonNull
	@Column(name = "text", nullable = false, length = 1000)
	private String text;

	/**
	 * The Required.
	 */
	@Column(name = "required", columnDefinition = "boolean default false")
	private Boolean required;

	/**
	 * The Questionnaire item type.
	 */
	@NonNull
	@Column(name = "questionnaire_item_type", nullable = false)
	private String questionnaireItemType;

	/**
	 * The Questionnaire items.
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "questionnaire_item_id")
	private List<QuestionnaireItem> questionnaireItems;

}
