package knowledge.survey.service;

import java.io.IOException;

import knowledge.survey.oxm.Question;
import knowledge.survey.oxm.Questions;
import knowledge.survey.service.QuestionService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context.xml")
public class QuestionsTest {
	
	@Autowired QuestionService questionService;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void QuestionServTest()
	{
		Questions qs;
		try {
			qs = questionService.getQuestions("questions");
			for(Question q:qs.getQuestion())
			{
				log.debug(q.getName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	

}
