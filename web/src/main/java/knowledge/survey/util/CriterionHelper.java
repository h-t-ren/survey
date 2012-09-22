package knowledge.survey.util;

import java.util.ArrayList;
import java.util.List;

import knowledge.survey.domain.Criterion;
import knowledge.survey.oxm.School;
import knowledge.survey.oxm.Status;

public class CriterionHelper {
	
	   public static Criterion findCriterion(List<Criterion> criteria, String criterionName)
	    {
	    	for(Criterion c: criteria)
	    	{
	    		if(c.getName().equals(criterionName))
	    		{
	    			return c;
	    		}
	    	}
	    	return null;
	    }
	   public static List<Criterion> loadCrieria()
	    {
	    	List<Criterion> criteria = new ArrayList<Criterion>();
	    	Criterion all = new Criterion(1,"全部");
	    	criteria.add(all);
	    	int i=2;
	    	for(Status s:Status.values())
	    	{
	    		criteria.add(new Criterion(i,s.name()));
	    		i++;
	    	}
	    	for(School s:School.values())
	    	{
	    		criteria.add(new Criterion(i,s.name()));
	    		i++;
	    	}
	    	return criteria;
	    }
	    

}
