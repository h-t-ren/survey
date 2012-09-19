package knowledge.survey.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import knowledge.survey.oxm.Results;

public interface ResultsService {
	public Results getResults(String resultsFile) throws IOException;
	public void storeResults (Results results,String resultsFile) throws FileNotFoundException, IOException;
}
