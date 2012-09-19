package knowledge.survey.controller;

import java.io.IOException;

import knowledge.survey.service.QuestionService;
import knowledge.survey.service.ResponseService;

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
	
  

    @RequestMapping(value = "/respondent",
            method = RequestMethod.GET, 
            headers="Accept=application/html, application/xhtml+xml")
     public String handleRespondentListRequest(Model model) {
          try {
			model.addAttribute("responses",responseService.getResponses("responses").getReponse());
		} catch (IOException e) {
			log.debug( "no reponses.xml file in the class path!");
		}
          return "respondent";
     }
    
    @RequestMapping(value = "/respondent/{id}", method = RequestMethod.GET, headers="Accept=application/html, application/xhtml+xml")
	public String handleGetQuestionRequest(@PathVariable("id") Integer id, Model model) {
		return "question";
	}
    
}
