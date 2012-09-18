package knowledge.survey.service;

import java.io.IOException;
import knowledge.survey.oxm.Reponses;

public interface ResponseService {
	public Reponses getResponses(String reponsesFile) throws IOException;
	public void generateResults(String reponsesFile, String filter) throws IOException;
	
}
