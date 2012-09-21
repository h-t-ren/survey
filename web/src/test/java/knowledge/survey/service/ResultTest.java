package knowledge.survey.service;


import java.util.ArrayList;
import java.util.List;
import knowledge.survey.oxm.ObjectFactory;
import knowledge.survey.oxm.Result;
import knowledge.survey.util.Quicksort;
import knowledge.survey.util.ResultComparator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context.xml")
public class ResultTest {
	
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private ObjectFactory objectFactory = new ObjectFactory();
	@Test
	public void ResultSortTest()
	{
		Result r1=objectFactory.createResult();
		r1.setScore(-112.4f);
		
		Result r2=objectFactory.createResult();
		r2.setScore(-11.4f);
		
		Result r3=objectFactory.createResult();
		r3.setScore(112.4f);
		
		Result r4=objectFactory.createResult();
		r4.setScore(-1112.4f);
		List<Result> rs = new ArrayList<Result>();
		rs.add(r1);
		rs.add(r2);
		rs.add(r3);
		rs.add(r4);
		Quicksort.sort(rs, new ResultComparator());
		
		for(Result r:rs)
		{
		//	log.debug("sore: " +r.getScore());
		}
		
		
	}
	

}
