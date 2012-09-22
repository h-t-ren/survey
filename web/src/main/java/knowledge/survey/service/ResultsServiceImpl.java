package knowledge.survey.service;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import knowledge.survey.oxm.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.xml.transform.ResourceSource;


@Service("resultsService")
public class ResultsServiceImpl implements ResultsService{
	
	@Autowired private Jaxb2Marshaller marshaller;

	@Override
	public Results getResults(String resultsFile) throws IOException {
		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		Resource resultsResource= appContext.getResource("classpath:"+resultsFile+".xml");
		Source resultsSource= new ResourceSource(resultsResource);
		Results results = (Results) marshaller.unmarshal(resultsSource);
		return results;
	}

	@Override
	public void storeResults(Results results, String resultsFile)
			throws FileNotFoundException, IOException {
		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		Resource resultsResource= appContext.getResource("classpath:"+resultsFile+".xml");
	     FileOutputStream os = new FileOutputStream(resultsResource.getFile().getAbsolutePath()); 
	     marshaller.marshal(results, new StreamResult(os));
        if (os != null) {
				os.close();
	      }
		
	}


}
