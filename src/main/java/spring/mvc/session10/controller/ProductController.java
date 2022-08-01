package spring.mvc.session10.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mvc.session10.entity.Product;

@Controller
@RequestMapping("/product/")
public class ProductController {

	private List<Product> products = new CopyOnWriteArrayList<>();
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("products" , products);
		return "session10/product";
	}
		
	@PostMapping("/")
	public String add(Product product , RedirectAttributes attr) {
		products.add(product);
		System.out.println("新增:" + products);
		attr.addFlashAttribute("product",product); 
		// 將 product 資料傳給 /addOk 讓 success.jsp 呈現
		// 防止網頁重新整理 , 防止二次 subbmit
		return "redirect: addOk";
	}
	
	// 防止重新整理
	@GetMapping(value = {"/addOk" , "/updateOk"})
	public String success() {
		return "session10/success";
	}
	
	@GetMapping("/get/{index}")
	public String get(@PathVariable("index") int index , Model model) {
		Product product = products.get(index);
		model.addAttribute("product" , product);
		return "session10/product_update";
	}
	
	

	
	
}
