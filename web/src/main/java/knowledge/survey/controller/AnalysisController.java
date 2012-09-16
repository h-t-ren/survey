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
public class AnalysisController {

	@Autowired QuestionService questionService;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
    @RequestMapping(value = "/analysis",
            method = RequestMethod.GET, 
            headers="Accept=application/html, application/xhtml+xml")
     public String home(Model model) {
          return "analysis";
     }


}
