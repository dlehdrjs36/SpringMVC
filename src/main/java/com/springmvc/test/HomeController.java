package com.springmvc.test;

import java.util.Locale;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
//	private static final Logger Logger = LoggerFactory.getLogger(HomeController.class);
	
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
	public String home2(Model model) {	
		return "redirect:/fileUploadView.do";
	}
	@RequestMapping(value = "/broadcast.do", method = RequestMethod.GET)
	public String broadcast() {
		return "broadcast";
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login() {
		return "member/memberLoginForm";
	}
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public void lc() {}
	
	//Rest Api, Html 부분
	@RequestMapping(value = "/users"
					,method = RequestMethod.GET
					,produces={"text/html"})
	public String restHtml() {
		return "restful";
	}
	
}
