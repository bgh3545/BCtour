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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greenart.ch1.PageHandlerAndSearchCondition.ProductPageHandler;
import com.greenart.ch1.PageHandlerAndSearchCondition.ProductSearchCondition;
import com.greenart.ch1.Product.ProductDao;
import com.greenart.ch1.Product.ProductDto;
import com.greenart.ch1.Product.ProductService;
import com.greenart.ch1.Recommend.RecommendDto;
import com.greenart.ch1.Recommend.RecommendService;
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
	@Autowired
	RecommendService recommendService;
	
	@RequestMapping(value ="/product",method=RequestMethod.GET)
	public String ProductInfo(Model m, Integer pd_num, HttpSession session) throws Exception{
		String id = (String)session.getAttribute("id");
		ProductDto select = productService.pd_reviewSelect(pd_num,id);
		
		ProductDto scoreCheck = productService.pd_scoreSelect(pd_num, id);
		if(scoreCheck==null) {
		int scoreInsert = productService.pd_scoreInsert(select,id);
		}
		
		RecommendDto recDto = recommendService.r_selectReview(pd_num, id);
		if(recDto==null) {
			m.addAttribute("reviewCheck", "write");
		}else {
			m.addAttribute("reviewCheck", "modify");
		}
		
		m.addAttribute("InfoListSelect",select);
		return "Product/BCProduct";
	}
	@PostMapping("/delete")
	public String delete(Model m,int pd_num) {
		int delete = productDao.deleteAll(pd_num);
		return "redirect:/capital?pd_city=seoul";
	}
	
	@GetMapping("/write")
	public String write(Model m) {
		return "Product/ProductWrite";
	}
	@PostMapping("/write")
	public String write2(Model m,ProductDto productDto,MultipartFile[] uploadFile,HttpSession session,RedirectAttributes reatt) throws Exception{
		try {
			String uploadFolder = "C:\\Users\\green\\git\\BCtour\\ch1\\src\\main\\webapp\\resources\\img";
			String str="";
			for (MultipartFile multipartFile : uploadFile) { 
				str = multipartFile.getOriginalFilename(); 
			}
			if(!str.equals("")) {
				productDto.setPd_img(str);
			}
			int rowCnt = productService.write(productDto);
			if(rowCnt != 1) throw new Exception("Write Error");
			reatt.addFlashAttribute("msg", "write_ok");
			return "redirect:/capital?pd_city="+productDto.getPd_city();
	}catch (Exception e) {
		e.printStackTrace();
		m.addAttribute("msg", "write_error");
		m.addAttribute("mode", "new");
		return "redirect:/capital?pd_city="+productDto.getPd_city();
	}
	}
	
	@RequestMapping(value = "/capital", method=RequestMethod.GET)
	public String ProductList(Model m, ProductSearchCondition psc, String pd_city, HttpSession session) throws Exception{
		
		int totalCnt = productDao.p_citySelectResultCnt(psc,pd_city);
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
		
		int totalCnt = productDao.p_buyCntResultCnt(psc,pd_city);
		ProductPageHandler pph = new ProductPageHandler(totalCnt,psc);
		
		String id = (String)session.getAttribute("id");
		List<WishDto> list = productDao.pd_buyCntSelect(psc, id, pd_city);
		
		m.addAttribute("list", list);
		m.addAttribute("ph",pph);
		m.addAttribute("productFilter", "buyCnt");
		return "Product/SeoulProductList";
	}
	
	@RequestMapping(value = "/searchProduct", method=RequestMethod.GET)
	public String searchProduct(Model m, ProductSearchCondition psc, HttpSession session) throws Exception{
		
		int totalCnt = productDao.searchResultCnt(psc);
		ProductPageHandler pph = new ProductPageHandler(totalCnt,psc);
		
		String id = (String)session.getAttribute("id");
		List<WishDto> list = productDao.pd_searchSelect(psc, id);
		
		m.addAttribute("list", list);
		m.addAttribute("ph",pph);
		m.addAttribute("productFilter", "searchProduct");
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
		m.addAttribute("msg", "complete");
		}
		
		ProductDto productInfoDto = productDao.select(productDto.getPd_num());
		m.addAttribute("info", productInfoDto);
		
		return "redirect:/capital?pd_city="+pd_city;
	}
	
	@GetMapping("/ProductModify")
	   public String ProductModify(HttpServletRequest request, Integer pd_num, ProductSearchCondition psc, Model m) throws Exception{
	      ProductDto modi = productDao.select(pd_num);
	      m.addAttribute("modi",modi);
	      m.addAttribute("page",psc.getPage());
	      m.addAttribute("pageSize",psc.getPageSize());
	      return "Product/ProductModify";
	   }
	
	@PostMapping("/ProductModify")
	   public String ProductModify2(Model m,ProductDto productDto,Integer del,String fileName,MultipartFile[] uploadFile,HttpSession session,RedirectAttributes reatt,HttpServletRequest request,Integer pd_num,ProductSearchCondition psc) {
	      m.addAttribute("page",psc.getPage());
	      m.addAttribute("pageSize",psc.getPageSize());
	      
	      String uploadFolder = "C:\\Users\\green\\git\\BCtour\\ch1\\src\\main\\webapp\\resources\\img";

	      String str="";
	      
	      for (MultipartFile multipartFile : uploadFile) { 
	         str = multipartFile.getOriginalFilename(); 
	      }
	      if(!str.equals("")) {
	         productDto.setPd_img(str);
	      }
	      System.out.println("�닔�젙 post"+productDto);
	      // 페이지와, pageSize 정보를 전달
	      try {
	         // 현재 form에 작성된 내용이 db에 저장됨
	         int rowCnt = productService.updateProduct(productDto);
	         if(rowCnt != 1) throw new Exception("modify Error");
	         reatt.addFlashAttribute("msg", "modify_ok");
	         return"redirect:/product?pd_num=" + productDto.getPd_num();
	      } catch (Exception e) {
	         e.printStackTrace();
	         m.addAttribute("ProductDto", productDto); // �옒紐� �옉�꽦�맂 寃쎌슦 �궡�슜 �떎�떆 �쟾�떖
	         m.addAttribute("msg", "modify_error");
	         m.addAttribute("m", "renew"); // �떎�떆 �씠�쟾�쓽 �닔�젙紐⑤뱶濡� �룎�븘媛�湲� �쐞�빐�꽌
	         return"redirect:/product?pd_num=" + productDto.getPd_num();
	      }
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
