package com.springmvc.test;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
/*		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);	
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );*/
		
		return "redirect:/fileUploadView.do";
	}
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String home2(Locale locale, Model model) {	
		return "redirect:/fileUploadView.do";
	}
	@RequestMapping(value = "/broadcast.do", method = RequestMethod.GET)
	public String broadcast() {
		return "broadcast";
	}
	
	@RequestMapping(value = "/restful.do", method = RequestMethod.GET)
	public String rest() {
		return "restful";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String restHtml() {
		return "restful";
	}
	
}
