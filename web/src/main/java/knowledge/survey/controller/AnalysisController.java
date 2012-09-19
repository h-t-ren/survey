package knowledge.survey.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import knowledge.survey.domain.Criterion;
import knowledge.survey.oxm.ObjectFactory;
import knowledge.survey.oxm.Preference;
import knowledge.survey.oxm.Profile;
import knowledge.survey.oxm.School;
import knowledge.survey.oxm.Status;
import knowledge.survey.service.ProfileService;
import knowledge.survey.service.QuestionService;
import knowledge.survey.service.ReferenceMethodService;
import knowledge.survey.service.ResultsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnalysisController {

	@Autowired QuestionService questionService;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private ObjectFactory objectFactory = new ObjectFactory();
	@Autowired private ProfileService profileService;
	@Autowired private ReferenceMethodService referenceMethodService;
	@Autowired private ResultsService resultsService;
	private final String profileName="profile";
	
    @RequestMapping(value = "/analysis",
            method = RequestMethod.GET, 
            headers="Accept=application/html, application/xhtml+xml")
     public String handleGetAnalysis(Model model) {
    	model.addAttribute("criteria", loadCrieria());
    	model.addAttribute("idCriterion", 1);
        loadPreference(model);
          return "analysis";
     }

    @RequestMapping(value = "/analysis",
            method = RequestMethod.POST, 
            headers="Accept=application/html, application/xhtml+xml")
     public String handleAnalysis(@RequestParam("criterion") String criterion, Model model) {
    	Preference preference=  loadPreference(model);
    	try {
			referenceMethodService.solve(preference, criterion);
			model.addAttribute("results", resultsService.getResults(criterion).getResult());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	List<Criterion> criteria = loadCrieria();
    	model.addAttribute("criteria", criteria);
    	Criterion c=  findCriterion(criteria, criterion);
    	model.addAttribute("idCriterion", c.getId());
          return "analysis";
     }

    private Criterion findCriterion(List<Criterion> criteria, String criterionName)
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
    private List<Criterion> loadCrieria()
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
    
    private Preference loadPreference(Model model)
    {
    	 try {
 			Profile profile =profileService.getProfile(profileName);
 			for(Preference p: profile.getPreference())
 			{
 				if(p.isSelected())
 				{
 					model.addAttribute("preference", p);
 					String distribution="";
 	 				int i=0;
 	 				for(int point: p.getReferencePoint())
 	 				{
 	 					i++;
 	 					if(i<5)
 	 					{
 	 						distribution = distribution+point +",";
 	 					}
 	 					else
 	 					{
 	 						distribution = distribution+point;
 	 					}
 	 					model.addAttribute("distribution", distribution);
 	 				}
 	 				return p;
 				}
 				
 				
 			}
 		} catch (IOException e) {
 			log.debug("no profile xml file was defined");
 		}
		return null;
           
    }
}
