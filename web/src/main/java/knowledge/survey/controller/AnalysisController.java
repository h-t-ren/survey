package knowledge.survey.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import knowledge.survey.domain.Criterion;
import knowledge.survey.oxm.ObjectFactory;
import knowledge.survey.oxm.School;
import knowledge.survey.oxm.Status;
import knowledge.survey.service.QuestionService;

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
    @RequestMapping(value = "/analysis",
            method = RequestMethod.GET, 
            headers="Accept=application/html, application/xhtml+xml")
     public String handleGetAnalysis(Model model) {
    	model.addAttribute("criteria", loadCrieria());
          return "analysis";
     }

    @RequestMapping(value = "/analysis",
            method = RequestMethod.POST, 
            headers="Accept=application/html, application/xhtml+xml")
     public String handleAnalysis(@RequestParam("criterion") String criterion, Model model) {
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
}
