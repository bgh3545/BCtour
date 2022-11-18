package com.greenart.ch1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenart.ch1.Recommend.RecommendDto;
import com.greenart.ch1.Recommend.RecommendService;

@Controller
public class HomeController {

	@Autowired
	RecommendService recService;
	
	@RequestMapping(value="/", method= RequestMethod.GET)
	public String home(Model m) throws Exception{
		List<RecommendDto> topList = recService.r_getTopList();
		m.addAttribute("topList", topList);
		return "home/homepage";
	}
	@RequestMapping(value="/1", method= RequestMethod.GET)
	public String home1(Model m) throws Exception{
		return "home/homepage_v1";
	}
}
