package knowledge.survey.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import knowledge.survey.oxm.ItemResult;
import knowledge.survey.oxm.Preference;
import knowledge.survey.oxm.Question;
import knowledge.survey.oxm.QuestionType;
import knowledge.survey.oxm.Questions;
import knowledge.survey.oxm.Result;
import knowledge.survey.oxm.Results;
import knowledge.survey.service.ProfileService;
import knowledge.survey.service.QuestionService;
import knowledge.survey.service.ResultsService;
import knowledge.survey.util.CriterionHelper;
import knowledge.survey.util.FileNameEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


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
			model.addAttribute("questions",questionService.getQuestions(FileNameEnum.questions.name()));
		} catch (IOException e) {
			log.debug( "no questions.xml file in the class path!");
		}
          model.addAttribute("sitemap", "question");
          return "question";
     }
    
    @RequestMapping(value = "/question/{id}", method = RequestMethod.GET, headers="Accept=application/html, application/xhtml+xml")
	public String handleGetQuestionRequest(@PathVariable("id") Integer id, Model model) {
    	return  internal(id, null, model);
	}
    
    
    
    @RequestMapping(value = "/question/{id}", method = RequestMethod.POST, headers="Accept=application/html, application/xhtml+xml")
  	public String handlePostQuestionRequest(@PathVariable("id") Integer id, @RequestParam(value="criteria", required=false) String[] selectedCriteria, Model model) {
  		return  internal(id, selectedCriteria, model);
  	}
      
    @RequestMapping(value = "/result",
            method = RequestMethod.GET, 
            headers="Accept=application/html, application/xhtml+xml")
     public String getQuestionDetails(@RequestParam("criterion") String criterion, @RequestParam("idQuestion") Integer idQuestion,Model model) {
    	model.addAttribute("criteria", CriterionHelper.loadCrieria());

        try {
     	    Questions questions =questionService.getQuestions(FileNameEnum.questions.name());
     	    Question question =questionService.findQuestion(questions, idQuestion);
     	    model.addAttribute("question", question);
     	    List<String> criteria = new ArrayList<String>(0);
      	    criteria.add(criterion);
     	    String series = generateSeries(model,criteria, question);
     	    model.addAttribute("series", series);
     	    model.addAttribute("categories", generateCategories(question));
 			
 			Results results =resultsService.getResults(FileNameEnum.全部.name());
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
         return "result";
     }

    
    private String internal(Integer id, String[] selectedCriteria, Model model)
    {
    	
    	  model.addAttribute("sitemap", "question");
          model.addAttribute("idQuestion", id);
      	  model.addAttribute("criteria", CriterionHelper.loadCrieria());
          try {
       	    Questions questions =questionService.getQuestions(FileNameEnum.questions.name());
       	    Question question =questionService.findQuestion(questions, id);
       	    model.addAttribute("question", question);
       	    List<String> criteria = new ArrayList<String>(0);
       	    if(selectedCriteria==null)
       	    {
     	        criteria.add("全部");
       	    }
       	    else
       	    {
       		    for(String criterion:selectedCriteria)
           	    {
           	      criteria.add(criterion);
           	    }
           	  
       	    }
       	    model.addAttribute("selectedCriteria", criteria);
       	    String series = generateSeries(model,criteria, question);
       	    model.addAttribute("series", series);
       	    model.addAttribute("categories", generateCategories(question));
   			model.addAttribute("questions",questions);
   			
   			Results results =resultsService.getResults(FileNameEnum.全部.name());
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
    
    
    
    
    private String generateSeries(Model model,List<String> criteria,Question question)
    {
     	//reference point
    	Preference preference = profileService.loadPreference(model);
    	String series="";
    	if(!question.getQuestionType().equals(QuestionType.控制性))
    	{
    	    series ="{name:'参考点', data:[";
        	int i=0;
        	List<Integer> rfs = preference.getReferencePoint();
        	if(question.isReversed()!=null&&question.isReversed())
        	{
        		Collections.reverse(rfs);
        	}
        	for(int v: rfs)
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

    
    
}
