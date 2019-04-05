package com.springmvc.test.web.locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LocaleController {
	@RequestMapping("/localeView.do")
	public String localeView() {
		return "locale/locale";
	}
}
