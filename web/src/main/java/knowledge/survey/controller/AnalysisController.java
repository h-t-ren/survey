package knowledge.survey.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import knowledge.survey.domain.Criterion;
import knowledge.survey.oxm.Preference;
import knowledge.survey.oxm.Question;
import knowledge.survey.oxm.Questions;
import knowledge.survey.oxm.Result;
import knowledge.survey.oxm.Results;
import knowledge.survey.service.ProfileService;
import knowledge.survey.service.QuestionService;
import knowledge.survey.service.ReferenceMethodService;
import knowledge.survey.service.ResultsService;
import knowledge.survey.util.CriterionHelper;
import knowledge.survey.util.FileNameEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnalysisController {

	@Autowired QuestionService questionService;
	@Autowired private ProfileService profileService;
	@Autowired private ReferenceMethodService referenceMethodService;
	@Autowired private ResultsService resultsService;

    @RequestMapping(value = "/analysis",
            method = RequestMethod.GET, 
            headers="Accept=application/html, application/xhtml+xml")
     public String handleGetAnalysis(Model model) {
    	model.addAttribute("criteria", CriterionHelper.loadCrieria());
    	model.addAttribute("idCriterion", 1);
    	profileService.loadPreference(model);
          return "analysis";
     }

    @RequestMapping(value = "/analysis",
            method = RequestMethod.POST, 
            headers="Accept=application/html, application/xhtml+xml")
     public String handleAnalysis(@RequestParam("criterion") String criterion,@RequestParam("numQuestion") Integer numQuestion, Model model) {
    	Preference preference=  profileService.loadPreference(model);
    	try {
			referenceMethodService.solve(preference, criterion);
			model.addAttribute("results", resultsService.getResults(criterion).getResult());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	List<Criterion> criteria = CriterionHelper.loadCrieria();
    	model.addAttribute("criteria", criteria);
    	Criterion c=  CriterionHelper.findCriterion(criteria, criterion);
    	model.addAttribute("idCriterion", c.getId());
    	model.addAttribute("criterionName", c.getName());
    	model.addAttribute("numQuestion", numQuestion);
        return "analysis";
     }


   
}
