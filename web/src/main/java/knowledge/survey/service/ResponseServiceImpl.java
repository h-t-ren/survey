package knowledge.survey.service;


import java.io.IOException;
import javax.xml.transform.Source;
import knowledge.survey.oxm.Reponses;
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
	@Override
	public Reponses getResponses(String reponsesFile) throws IOException {
		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		Resource responseResource= appContext.getResource("classpath:"+reponsesFile+".xml");
		Source responseSource= new ResourceSource(responseResource);
		Reponses reponses = (Reponses) marshaller.unmarshal(responseSource);
		return reponses;
	}


}
