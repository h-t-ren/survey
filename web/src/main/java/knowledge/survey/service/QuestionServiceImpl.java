package knowledge.survey.service;


import java.io.IOException;

import javax.xml.transform.Source;

import knowledge.survey.oxm.ObjectFactory;
import knowledge.survey.oxm.Questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.xml.transform.ResourceSource;


@Service("questionService")
public class QuestionServiceImpl implements QuestionService{
	
	private ObjectFactory objectFactory = new ObjectFactory();
	@Autowired private Jaxb2Marshaller marshaller;

	@Override
	public Questions getQuestions(String questionsFile) throws IOException {
		Questions questions=objectFactory.createQuestions();
		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		Resource questionsResource= appContext.getResource("classpath:"+questionsFile+".xml");
		Source questionsSource= new ResourceSource(questionsResource);
	    questions = (Questions) marshaller.unmarshal(questionsSource);
		 return questions;
		
	}


}
