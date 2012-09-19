package knowledge.survey.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import knowledge.survey.oxm.ObjectFactory;
import knowledge.survey.oxm.Preference;
import knowledge.survey.oxm.Result;
import knowledge.survey.util.Quicksort;
import knowledge.survey.util.ResultComparator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context.xml")
public class ReferencePointsMethodTest {
	
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private ObjectFactory objectFactory = new ObjectFactory();
	@Autowired private ReferenceMethodService referenceMethodService;
	@Autowired private ProfileService profileService;
	@Test
	public void referencePointsMethodTest()
	{
		try {
			Preference	preference=profileService.getProfile("profile").getPreference().get(5);
			referenceMethodService.solve(preference, "全部");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
