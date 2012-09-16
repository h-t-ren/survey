package knowledge.survey.service;

import java.io.IOException;

import knowledge.survey.oxm.Questions;

public interface QuestionService {
	public Questions getQuestions(String questionsFile) throws IOException;
}
