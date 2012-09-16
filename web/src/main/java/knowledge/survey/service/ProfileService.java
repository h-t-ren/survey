package knowledge.survey.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import knowledge.survey.oxm.Profile;

public interface ProfileService {
	public Profile getProfile(String profileFile) throws IOException;
	public void storeProfile (Profile profile,String profileFile) throws FileNotFoundException, IOException;
	public void changeProfile (String profileFile, int idPref) throws FileNotFoundException, IOException;
}
