package knowledge.survey.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import knowledge.survey.oxm.Preference;
import knowledge.survey.oxm.Profile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context.xml")
public class ProfileTest {
	
	@Autowired ProfileService profileService;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void ProfileServTest()
	{
		Profile profile;
		try {
			profile = profileService.getProfile("profile");
			for(Preference p:profile.getPreference())
			{
				//log.debug(p.getId() +", name: " + p.getName() +", parameter: " +p.getParameter() +", selected: " +p.isSelected());
				//log.debug("reference point distribution:");
				String distribution="";
				for(int value: p.getReferencePoint())
				{
					distribution = distribution +value +" ";
				}
				//log.debug(distribution);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	@Test
	public void ProfileStoreTest()
	{
		Profile profile;
		try {
			profile = profileService.getProfile("profile");
			for(Preference p:profile.getPreference())
			{
				if(p.getId()==2)
				{
					p.setSelected(true);
				}
				else
				{
					p.setSelected(false);
				}
			}
			profileService.storeProfile(profile, "profile");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
