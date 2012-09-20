package knowledge.survey.domain;

import java.io.Serializable;

import knowledge.survey.oxm.Question;

public class QuestionCommemt implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Question question;
	private String comment;
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
