package com.greenart.ch1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenart.ch1.PageHandlerAndSearchCondition.ProductPageHandler;
import com.greenart.ch1.PageHandlerAndSearchCondition.ProductSearchCondition;
import com.greenart.ch1.ProductList.ListDao;
import com.greenart.ch1.ProductList.ListDto;
import com.greenart.ch1.ProductList.ListService;
import com.greenart.ch1.ProductList.ProductInfoDao;
import com.greenart.ch1.ProductList.ProductInfoDto;

@Controller
public class ProductController {
	@Autowired
	ProductInfoDao productInfoDao;
	@Autowired
	ListService listService;
	@Autowired
	ListDao listDao;
	
	@RequestMapping(value ="/product",method=RequestMethod.GET)
	public String ProductInfo(Model m,int pd_num) throws Exception{
		ProductInfoDto select = productInfoDao.select(pd_num);
		m.addAttribute("InfoListSelect",select);
		return "Product/BCProduct";
	}
	@PostMapping("/delete")
	public String delete(Model m,int pd_num) {
		int delete = listDao.deleteAll(pd_num);
		return"redirect:/capital";
	}
	
	@GetMapping("/write")
	public String write(Model m) {
		return "Product/ProductWrite";
	}
	@PostMapping("/write")
	public String write2(Model m,ListDto listDto) throws Exception{
		
		int rowCnt = listService.write(listDto);
		return"redirect:/capital";
	}
	@RequestMapping(value = "/capital", method=RequestMethod.GET)
	public String ProductList(String pd_title,Model m,ProductSearchCondition psc,String pd_city) throws Exception{
		
		int totalCnt = listDao.searchResultCnt(psc,pd_city);
		List<ListDto> list = listDao.searchSelectPage(psc,pd_city);
		ProductPageHandler pph = new ProductPageHandler(totalCnt,psc);
		m.addAttribute("seoulList",list);
		m.addAttribute("ph",pph);
		m.addAttribute("page", psc.getPage());
		return "Product/SeoulProductList";
	}
	
	
	
}
