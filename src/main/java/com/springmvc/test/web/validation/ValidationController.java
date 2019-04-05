package com.springmvc.test.web.validation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.springmvc.test.web.jackson.UserDTO;

@Controller
public class ValidationController {
	@RequestMapping("/validator.do")
	public String validator() {
		return "validation/validation";
	}
	
	@RequestMapping("/validationView.do")
	public String validationView(UserDTO user) {
		return "validation/userValidation";
	}
}
