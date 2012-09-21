package knowledge.survey.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import knowledge.survey.domain.Criterion;
import knowledge.survey.oxm.ItemResult;
import knowledge.survey.oxm.Preference;
import knowledge.survey.oxm.Profile;
import knowledge.survey.oxm.Question;
import knowledge.survey.oxm.QuestionType;
import knowledge.survey.oxm.Questions;
import knowledge.survey.oxm.Result;
import knowledge.survey.oxm.Results;
import knowledge.survey.oxm.School;
import knowledge.survey.oxm.Status;
import knowledge.survey.service.ProfileService;
import knowledge.survey.service.QuestionService;
import knowledge.survey.service.ResultsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class QuestionController {

	@Autowired QuestionService questionService;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired private ProfileService profileService;
	@Autowired private ResultsService resultsService;
    @RequestMapping(value = "/",
            method = RequestMethod.GET, 
            headers="Accept=application/html, application/xhtml+xml")
     public String home(Model model) {
    	 model.addAttribute("sitemap", "main");
          return "main";
          
     }

    @RequestMapping(value = "/question",
            method = RequestMethod.GET, 
            headers="Accept=application/html, application/xhtml+xml")
     public String handleGetQuestionListRequest(Model model) {
    	 model.addAttribute("idQuestion",1100);
          try {
			model.addAttribute("questions",questionService.getQuestions("questions"));
		} catch (IOException e) {
			log.debug( "no questions.xml file in the class path!");
		}
          model.addAttribute("sitemap", "question");
          return "question";
     }
    
    @RequestMapping(value = "/question/{id}", method = RequestMethod.GET, headers="Accept=application/html, application/xhtml+xml")
	public String handleGetQuestionRequest(@PathVariable("id") Integer id, Model model) {
       model.addAttribute("sitemap", "question");
       model.addAttribute("idQuestion", id);
   	   model.addAttribute("criteria", loadCrieria());
       try {
    	    Questions questions =questionService.getQuestions("questions");
    	    Question question =questionService.findQuestion(questions, id);
    	    model.addAttribute("question", question);
    	    List<String> criteria = new ArrayList<String>(0);
    	    criteria.add("全部");
    	    String series = generateSeries(criteria, question);
    	    model.addAttribute("series", series);
    	    model.addAttribute("categories", generateCategories(question));
			model.addAttribute("questions",questionService.getQuestions("questions"));
			
			Results results =resultsService.getResults("全部");
			for(Result result:results.getResult())
			{
				if(result.getQuestion().getId()==question.getId())
				{
					model.addAttribute("comments",result.getComment());
					break;
				}
			}
		
		} catch (IOException e) {
			log.debug( "no questions.xml file in the class path!");
		}
		return "question";
	}
    
    private String generateSeries(List<String> criteria,Question question)
    {
     	//reference point
    	Preference preference = loadPreference();
    	String series="";
    	if(!question.getQuestionType().equals(QuestionType.控制性))
    	{
    	    series ="{name:'参考点', data:[";
        	int i=0;
        	for(int v: preference.getReferencePoint())
        	{
        		if(i==0)
        		{
        			series = series +v;
        		}
        		else
        		{
        			series = series+ "," +v;
        		}
        		
        		i++;
        	}
        	series = series+"]}";
        	for(String criterion:criteria)
            {
            	series =  series+ "," +  generateData(criterion,question);
            }
        	
    	}
    	else
    	{
    		int i=0;
    		for(String criterion:criteria)
            {
    			if(i==0)
    			{
    				series =  series+ generateData(criterion,question);
    			}
    			else
    			{
    				series =  series+ "," +  generateData(criterion,question);
    			}
            
            	i++;
            }
    	}
    	
    	
    	return series;
    }
    
    private String generateData(String criterion,Question question)
    {
    	String series ="{name: '" +criterion+"', data: [";
    	try {
			Results results =resultsService.getResults(criterion);
			for(Result result:results.getResult())
			{
				if(result.getQuestion().getId()==question.getId())
				{
					int i=0;
					for(ItemResult itemResult :result.getItemResult())
					{
						
						float percentage = itemResult.getPercentage();
						if(percentage==0)
							percentage =0.5f;
						if(i==0)
						{
							series = series +percentage;
						}
						else
						{
							series = series +","+percentage;
						}
						
						i++;
					}
					series = series +"]}";
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

    	return series;
    }
    private String generateCategories(Question question)
    {
    	String categories ="";
    	int i=1;
    	for(String item:question.getItem())
    	{
    		if(i==1) {categories =categories + "'第"+i+"选项'";}
    		else
    		{
    		     categories = categories +",'第"+i+"选项'";
    		}
    		i++;
    	}
    	
    	return categories;
    }
    private Preference loadPreference()
    {
    	 try {
 			Profile profile =profileService.getProfile("profile");
 			for(Preference p: profile.getPreference())
 			{
 				if(p.isSelected())
 				{
 	 				return p;
 				}
 			}
 		} catch (IOException e) {
 			log.debug("no profile xml file was defined");
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
