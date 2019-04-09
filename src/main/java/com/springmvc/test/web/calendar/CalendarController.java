package com.springmvc.test.web.calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalendarController {
	
	// 목록
	@RequestMapping(value = "/Auth")
	public String auth() {
		return "calendar";
	}
	@RequestMapping(value = "/Auth2")
	public String auth2() {
		return "c2";
	}
}
