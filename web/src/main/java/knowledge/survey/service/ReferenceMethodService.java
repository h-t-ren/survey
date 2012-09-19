package knowledge.survey.service;

import java.io.IOException;

import knowledge.survey.oxm.Preference;

public interface ReferenceMethodService {
	
	public void solve(Preference preference,String dataSet) throws IOException;
	

}
