package knowledge.survey.controller;

import java.io.IOException;

import knowledge.survey.service.QuestionService;

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
	
    @RequestMapping(value = "/",
            method = RequestMethod.GET, 
            headers="Accept=application/html, application/xhtml+xml")
     public String home(Model model) {
    	 model.addAttribute("sitemap", "main");
          return "main";
          
     }

    @RequestMapping(value = "/questionList",
            method = RequestMethod.GET, 
            headers="Accept=application/html, application/xhtml+xml")
     public String handleGetQuestionListRequest(Model model) {
          try {
			model.addAttribute("questions",questionService.getQuestions("questions"));
		} catch (IOException e) {
			log.debug( "no questions.xml file in the class path!");
		}
          model.addAttribute("sitemap", "questions");
          return "questions";
     }
    
    @RequestMapping(value = "/question/{id}", method = RequestMethod.GET, headers="Accept=application/html, application/xhtml+xml")
	public String handleGetQuestionRequest(@PathVariable("id") Integer id, Model model) {
		return "question";
	}
    
}
