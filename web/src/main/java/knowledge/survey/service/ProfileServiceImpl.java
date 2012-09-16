package knowledge.survey.service;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;

import knowledge.survey.oxm.Preference;
import knowledge.survey.oxm.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.xml.transform.ResourceSource;


@Service("profileService")
public class ProfileServiceImpl implements ProfileService{
	
	@Autowired private Jaxb2Marshaller marshaller;
	@Override
	public  Profile getProfile(String profileFile) throws IOException{
		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		Resource profileResource= appContext.getResource("classpath:"+profileFile+".xml");
		Source profileSource= new ResourceSource(profileResource);
		Profile profile = (Profile) marshaller.unmarshal(profileSource);
		return profile;
		
	}

	@Override
	public void storeProfile(Profile profile, String profileFile) throws FileNotFoundException, IOException {
		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		Resource profileResource= appContext.getResource("classpath:"+profileFile+".xml");
	     FileOutputStream os = new FileOutputStream(profileResource.getFile().getAbsolutePath()); 
	     marshaller.marshal(profile, new StreamResult(os));
         if (os != null) {
				os.close();
	      }
	}

	@Override
	public void changeProfile(String profileFile, int idPref)
			throws FileNotFoundException, IOException {
		Profile profile = getProfile(profileFile);
		for(Preference p:profile.getPreference())
		{
			if(p.getId()==idPref)
			{
				p.setSelected(true);
			}
			else
			{
				p.setSelected(false);
			}
		}
		storeProfile(profile, profileFile);
		
	}


}
