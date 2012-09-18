package knowledge.survey.service;


import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;

import knowledge.survey.oxm.Anwser;
import knowledge.survey.oxm.ItemResult;
import knowledge.survey.oxm.ObjectFactory;
import knowledge.survey.oxm.Question;
import knowledge.survey.oxm.Questions;
import knowledge.survey.oxm.Reponse;
import knowledge.survey.oxm.Reponses;
import knowledge.survey.oxm.Result;
import knowledge.survey.oxm.Results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.xml.transform.ResourceSource;

@Service("responseService")
public class ResponseServiceImpl implements ResponseService{
	
	@Autowired private Jaxb2Marshaller marshaller;
	private ObjectFactory objectFactory = new ObjectFactory();
	@Autowired QuestionService questionService;
	@Override
	public Reponses getResponses(String reponsesFile) throws IOException {
		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		Resource responseResource= appContext.getResource("classpath:"+reponsesFile+".xml");
		Source responseSource= new ResourceSource(responseResource);
		Reponses reponses = (Reponses) marshaller.unmarshal(responseSource);
		return reponses;
	}
	@Override
	public void generateResults(String reponsesFile, String filter)
			throws IOException {
		Results results = initializeResult();
		Reponses responses = getResponses(reponsesFile);
		for(Reponse reponse:responses.getReponse())
		{
			String school = reponse.getRespondent().getSchool().name();
			String status = reponse.getRespondent().getStatus().name();
			if(school.equals(filter)||status.equals(filter)||"全部".equals(filter))
			{

				for(Anwser answer:reponse.getAnwsers())
				{
					int idQuestion = answer.getIdQuestion();
                   // Result result = results.getResult().get(idQuestion-1);
					int totalNumber=0;
                    for(int itemNumber:answer.getItemNumber())
                    {
                    	int orgNumber =results.getResult().get(idQuestion-1).getItemResult().get(itemNumber-1).getNumber();
                    	results.getResult().get(idQuestion-1).getItemResult().get(itemNumber-1).setNumber(orgNumber+1);
                    	totalNumber++;
                    }
                    int preNum = results.getResult().get(idQuestion-1).getTotalNumber();
                    totalNumber = totalNumber+preNum;
                    results.getResult().get(idQuestion-1).setTotalNumber(totalNumber);
                    if(answer.getComment()!=null&&!answer.getComment().isEmpty())
                    {
                    	results.getResult().get(idQuestion-1).getComment().add(answer.getComment());
                    }
				}
			}
		}
		results = calculatePercentage(results);
		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		Resource profileResource= appContext.getResource("classpath:"+filter+".xml");
	    FileOutputStream os = new FileOutputStream(profileResource.getFile().getAbsolutePath()); 
	    marshaller.marshal(results, new StreamResult(os));
        if (os != null) {
				os.close();
	     }
	}

	private Results calculatePercentage(Results results)
	{
		int handred =100;
	    for(int i=0; i<results.getResult().size();i++)
	    {
	    	int totalNum = results.getResult().get(i).getTotalNumber();
	    	int totalPercentage=0;
	    	for(int j=0; j< results.getResult().get(i).getItemResult().size(); j++)
	    	{
	    		int num = results.getResult().get(i).getItemResult().get(j).getNumber();
	    		int percentage;
	    		if(j<results.getResult().get(i).getItemResult().size()-1)
	    		{
	    			percentage= (num*100/totalNum);
	    			System.out.println("num: "+ num+", total: " + totalNum +", per: " + percentage);
		    		totalPercentage = totalPercentage+percentage;
	    		}
	    		else
	    		{
	    			percentage = handred-totalPercentage;
	    		}
                
	    		results.getResult().get(i).getItemResult().get(j).setPercentage(percentage);
	    	}
	    }
		return results;
	}
	private Results initializeResult() throws IOException
	{
		Results results = objectFactory.createResults();
		Questions questions = questionService.getQuestions("questions");
		for(Question q:questions.getQuestion())
		{
			Result result =objectFactory.createResult();
			result.setQuestion(q);
			int i=1;
			for(String item: q.getItem())
			{
				ItemResult ir = objectFactory.createItemResult();
				ir.setItemNumber(i);
				ir.setNumber(0);
				ir.setPercentage(0);
				i++;
				result.getItemResult().add(ir);
			}
			result.setScore(0f);
			result.setSequence(0);
			result.setScore(0f);
			result.setTotalNumber(0);
			results.getResult().add(result);
		}
		return results;
	}
}
