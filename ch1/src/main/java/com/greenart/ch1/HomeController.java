package com.greenart.ch1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@Autowired
	RecommendService recService;
	
	@RequestMapping(value="/", method= RequestMethod.GET)
	public String home(){
		return "homepage";
	}
	@RequestMapping(value="/1", method= RequestMethod.GET)
	public String home1(Model m) throws Exception{
		
		List<RecommendDto> topList = recService.r_getTopList();
		m.addAttribute("topList", topList);
		return "homepage_v1";
	}
}
