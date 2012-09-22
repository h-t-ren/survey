package knowledge.survey.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import knowledge.survey.domain.Item;
import knowledge.survey.domain.QuestionCommemt;
import knowledge.survey.oxm.Anwser;
import knowledge.survey.oxm.Question;
import knowledge.survey.oxm.Questions;
import knowledge.survey.oxm.Reponse;
import knowledge.survey.service.QuestionService;
import knowledge.survey.service.ResponseService;
import knowledge.survey.util.FileNameEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RespondentController {

	@Autowired ResponseService responseService;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired QuestionService questionService;
  

    @RequestMapping(value = "/respondent",
            method = RequestMethod.GET, 
            headers="Accept=application/html, application/xhtml+xml")
     public String handleRespondentListRequest(Model model) {
          try {
			model.addAttribute("responses",responseService.getResponses(FileNameEnum.responses.name()).getReponse());
		} catch (IOException e) {
			log.debug( "no reponses.xml file in the class path!");
		}
          model.addAttribute("idQuestion",1100);
          return "respondent";
     }
    
    @RequestMapping(value = "/respondent/{id}", method = RequestMethod.GET, headers="Accept=application/html, application/xhtml+xml")
	public String handleGetQuestionRequest(@PathVariable("id") Integer id, Model model) {

    	try {
    		List<Reponse> reponses =	responseService.getResponses(FileNameEnum.responses.name()).getReponse();
    		Reponse reponse =reponses.get(id-1);
    		Questions questions = questionService.getQuestions(FileNameEnum.questions.name());
    		Map<QuestionCommemt,List<Item>> questionMap = new LinkedHashMap<QuestionCommemt, List<Item>>(0);
    		int i =1;
    		for(Question q: questions.getQuestion())
    		{
    			QuestionCommemt qc = new QuestionCommemt();
    			qc.setQuestion(q);
    			Anwser answer =responseService.getAnswer(reponse, i);
    			if(answer!=null)
    			{
    				qc.setComment(answer.getComment());
    			}
    			List<Item> items = new ArrayList<Item>();
    			List<Integer> itemNumbers = responseService.getItemNumbers(reponse, i);
    			for(String sitem: q.getItem())
    			{
    				Item item = new Item();
    				item.setItem(sitem);
    				items.add(item);

    			}
    			for(int n :itemNumbers)
				{
    				items.get(n-1).setSelected(true);
				}
				
    			questionMap.put(qc, items);
    			i++;
    		}
    		model.addAttribute("questionMap",questionMap);
    		model.addAttribute("idQuestion",id);
			model.addAttribute("responses",reponses);
		} catch (IOException e) {
			log.debug( "no reponses.xml file in the class path!");
		}
		return "respondent";
	}
    
}
