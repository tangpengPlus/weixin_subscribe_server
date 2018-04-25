package com.gency.subscribe.web.controller.business;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/web/index")
public class WebIndexController {

	
	@GetMapping(value="/list")
	public ModelAndView nomocall(ModelAndView mv){
		try {
				 mv.setViewName("web/index");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	
}
