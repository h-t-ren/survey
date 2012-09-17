package knowledge.survey.service;

import java.io.IOException;
import knowledge.survey.oxm.Reponses;

public interface ResponseService {
	public Reponses getResponses(String reponsesFile) throws IOException;
	
}
