package com.greenart.ch1.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greenart.ch1.PageHandlerAndSearchCondition.ProductPageHandler;
import com.greenart.ch1.PageHandlerAndSearchCondition.ProductSearchCondition;
import com.greenart.ch1.Product.ProductDao;
import com.greenart.ch1.Product.ProductDto;
import com.greenart.ch1.Product.ProductService;
import com.greenart.ch1.Reservation.ReservationDao;
import com.greenart.ch1.Reservation.ReservationDto;
import com.greenart.ch1.Reservation.ReservationService;
import com.greenart.ch1.WishList.WishDao;
import com.greenart.ch1.WishList.WishDto;
import com.greenart.ch1.WishList.WishService;

@Controller
public class ProductController {
	
	@Autowired
	ProductDao productDao;
	@Autowired
	ProductService productService;
	@Autowired
	WishDao wishDao;
	@Autowired
	WishService wishService;
	@Autowired
	ReservationDao reservationDao;
	@Autowired
	ReservationService reservationService;
	
	@RequestMapping(value ="/product",method=RequestMethod.GET)
	public String ProductInfo(Model m,int pd_num) throws Exception{
		ProductDto select = productDao.select(pd_num);
		m.addAttribute("InfoListSelect",select);
		return "Product/BCProduct";
	}
	@PostMapping("/delete")
	public String delete(Model m,int pd_num) {
		int delete = productDao.deleteAll(pd_num);
		return"redirect:/capital";
	}
	
	@GetMapping("/write")
	public String write(Model m) {
		return "Product/ProductWrite";
	}
	@PostMapping("/write")
	public String write2(Model m,ProductDto listDto) throws Exception{
		
		int rowCnt = productService.write(listDto);
		return"redirect:/capital";
	}
	
	@RequestMapping(value = "/capital", method=RequestMethod.GET)
	public String ProductList(Model m, ProductSearchCondition psc, String pd_city, HttpSession session) throws Exception{
		
		int totalCnt = productDao.searchResultCnt(psc,pd_city);
		ProductPageHandler pph = new ProductPageHandler(totalCnt,psc);
		
		String id = (String)session.getAttribute("id");
		List<WishDto> list = productDao.selectWish(psc, id, pd_city);
		
		m.addAttribute("list", list);
		m.addAttribute("ph",pph);
		m.addAttribute("productFilter", "capital");
		return "Product/SeoulProductList";
	}
	
	@RequestMapping(value = "/buyCnt", method=RequestMethod.GET)
	public String buycnt(Model m, ProductSearchCondition psc, String pd_city, HttpSession session) throws Exception{
		
		int totalCnt = productDao.searchResultCnt(psc,pd_city);
		ProductPageHandler pph = new ProductPageHandler(totalCnt,psc);
		
		String id = (String)session.getAttribute("id");
		List<WishDto> list = productDao.pd_buyCntSelect(psc, id, pd_city);
		
		m.addAttribute("list", list);
		m.addAttribute("ph",pph);
		m.addAttribute("productFilter", "capital");
		return "Product/SeoulProductList";
	}
	
	@ResponseBody
	@PostMapping("/addWish")
	public String addWish(HttpServletRequest request, HttpSession session, @RequestBody WishDto wishDto,Integer pd_num) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		String id = (String)session.getAttribute("id");
		WishDto wlist = wishService.w_read(pd_num, id);
		wishDto.setId(id);
		if(wlist == null) {
		int wishCnt = wishService.w_writer(wishDto);
		}
		int addWish = wishService.w_addWish(pd_num, id);
		return "";
	}
	@ResponseBody
	@PostMapping("/delWish")
	public String delWish(HttpServletRequest request,@RequestBody WishDto wishDto , Integer pd_num, HttpSession session) throws Exception {
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		String id = (String)session.getAttribute("id");
		int delWish = wishService.w_remove(pd_num, id);
		return "";
	}
	
	@GetMapping("/purchase")
	public String purchase1(Model m, Integer pd_num, HttpServletRequest request,String pd_city) throws Exception {
		
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		if(pd_num == null) {
			return "redirect:/capital?pd_city="+pd_city;
		}
		
		ProductDto productInfoDto = productDao.select(pd_num);
		m.addAttribute("info", productInfoDto);
		
		return "reservation/purchase";
	}
	
	@PostMapping("/purchase")
	public String purchase2(Model m, ProductDto productDto, ReservationDto reservationDto, HttpSession session, HttpServletRequest request, String pd_city) throws Exception {
		
		if(!loginCheck(request))
			return "redirect:/logIn/logIn?toURL="+request.getRequestURL();
		
		String mem_id = (String)session.getAttribute("id");
		
		ReservationDto resCheck = reservationService.res_select(mem_id, reservationDto.getPd_num());
		
		if(resCheck ==null) {
		int reservationCnt = reservationService.res_insert(mem_id, reservationDto);
		}
		
		if(resCheck !=null && (resCheck.getState() ==0 || resCheck.getState() ==3)) {
		int reservationCnt = reservationService.res_modify(mem_id, reservationDto);
		}
		
		if(resCheck.getState() ==1) {
			m.addAttribute("msg", "reservated");
		}
		
		if(resCheck.getState() ==2) {
			m.addAttribute("msg", "cancleRequest");
		}
		
		ProductDto productInfoDto = productDao.select(productDto.getPd_num());
		m.addAttribute("info", productInfoDto);
		
		return "redirect:/capital?pd_city="+pd_city;
	}
	
	private boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("id")!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
