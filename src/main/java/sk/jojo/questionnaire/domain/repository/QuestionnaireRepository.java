package sk.jojo.questionnaire.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.jojo.questionnaire.model.Questionnaire;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {

}
