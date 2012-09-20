package knowledge.survey.service;

import java.io.IOException;
import java.util.List;

import knowledge.survey.oxm.Anwser;
import knowledge.survey.oxm.Reponse;
import knowledge.survey.oxm.Reponses;

public interface ResponseService {
	public Reponses getResponses(String reponsesFile) throws IOException;
	public void generateResults(String reponsesFile, String filter) throws IOException;
	public List<Integer>  getItemNumbers(Reponse reponse,int idQuestion);
	public Anwser  getAnswer(Reponse reponse,int idQuestion);
	
}
