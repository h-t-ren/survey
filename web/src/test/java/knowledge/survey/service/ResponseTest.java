package knowledge.survey.service;

import java.io.IOException;
import java.util.List;

import knowledge.survey.oxm.Anwser;
import knowledge.survey.oxm.ItemResult;
import knowledge.survey.oxm.ObjectFactory;
import knowledge.survey.oxm.Reponse;
import knowledge.survey.oxm.Reponses;
import knowledge.survey.oxm.Result;
import knowledge.survey.oxm.School;
import knowledge.survey.oxm.Status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context.xml")
public class ResponseTest {
	
	@Autowired ResponseService responseService;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private ObjectFactory objectFactory = new ObjectFactory();
	@Test
	public void responseServTest()
	{
		
		Result result = objectFactory.createResult();
		Reponses rs;
		try {
			rs = responseService.getResponses("responses");
			for(Reponse r:rs.getReponse())
			{
				log.debug(r.getRespondent().getSchool().name());
				//result.setQuestion(value);
				List<Anwser> answers = r.getAnwsers();
				for(Anwser a:answers)
				{
					for(int itemNo:a.getItemNumber())
					{
						ItemResult ir =objectFactory.createItemResult();
						ir.setItemNumber(itemNo);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
   public void generateResultsTest()
   {
		try {
			responseService.generateResults("responses", "全部");
			for(Status status: Status.values())
			{
				responseService.generateResults("responses",status.name());
			}
			for(School s: School.values())
			{
				responseService.generateResults("responses",s.name());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
   }
}
