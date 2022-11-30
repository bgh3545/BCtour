package com.greenart.ch1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenart.ch1.Product.ProductDto;
import com.greenart.ch1.Product.ProductService;
import com.greenart.ch1.Recommend.RecommendDto;
import com.greenart.ch1.Recommend.RecommendService;

@Controller
public class HomeController {

	@Autowired
	RecommendService recService;
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/", method= RequestMethod.GET)
	public String home(Model m) throws Exception{
		List<RecommendDto> topList = recService.r_getTopList();
		m.addAttribute("topList", topList);
		List<ProductDto> list = productService.pd_buyCntTop();
		m.addAttribute("list", list);
		return "home/homepage";
	}
}