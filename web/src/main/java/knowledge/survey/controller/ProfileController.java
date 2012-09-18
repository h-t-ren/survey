package knowledge.survey.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import knowledge.survey.oxm.Preference;
import knowledge.survey.oxm.Profile;
import knowledge.survey.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

	@Autowired ProfileService profileService;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final String profileName="profile";
	
    @RequestMapping(value = "/profile",
            method = RequestMethod.GET, 
            headers="Accept=application/html, application/xhtml+xml")
     public String handleGetProfileRequest(Model model) {
    	  try {
			Profile profile =profileService.getProfile(profileName);
			model.addAttribute("profile", profile);
			String[] distributions= new String[profile.getPreference().size()];
			int j=0;
			for(Preference p: profile.getPreference())
			{
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
					
				}
				distributions[j] = distribution;
				j++;
				
			}
			model.addAttribute("distributions", distributions);
		} catch (IOException e) {
			log.debug("no profile xml file was defined");
		}
          return "profile";
     }

    @RequestMapping(value = "/profile",
            method = RequestMethod.POST, 
            headers="Accept=application/html, application/xhtml+xml")
     public String handleStoreProfileResponse(@RequestParam("idPref") int idPref) {
          try {
			profileService.changeProfile(profileName, idPref);
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
          return "redirect:/analysis";
     }

}
