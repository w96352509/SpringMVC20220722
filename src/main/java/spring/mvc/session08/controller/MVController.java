package spring.mvc.session08.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mv")
public class MVController {

	@RequestMapping(value = {"/case01"},method = {RequestMethod.GET})
	public ModelAndView case1() {
		String data = "Hello ModelAndView";             // 資料(Model)
		String view = "show_data";                      // 渲染(view)
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("data1" , data);
	    modelAndView.setViewName(view);
	    return modelAndView;
	}
	
	@RequestMapping(value = {"/case02"},method = {RequestMethod.GET})
	public ModelAndView case2() {
		String data = "Hello ModelAndView";             // 資料(Model)
		String view = "show_data";                      // 渲染(view)
		return  new ModelAndView(view , "data1" , data);
	}
	
	
	@RequestMapping("/case03")
	public ModelAndView case03() {
		ModelAndView mv = new ModelAndView();
		String data1 = "Hello ModelAndView";             // 資料(Model)
		String data2 = "2";
		String view = "show_data";                       // 渲染(view)
		mv.setViewName(view);
		mv.addObject("data1" , data1);
		mv.addObject("data2" , data2);
		return  mv;
		}
	
	// 宣告 Model 參數 , 使用 addAttribute()
	// return = view 路徑
	@RequestMapping("case04")
	public String case04(Model model) {
		String data1 = "Hello ModelAndView";             // 資料(Model)
		String data2 = "2";
		String data3 = "3";
		String view = "show_data";     
		// 要配置 Springmvc-servlet
		// 渲染(view) , 相對路徑
		model.addAttribute("data1" , data1 );
		model.addAttribute("data2" , data2);
		model.addAttribute("data3" , data3);
		return view;
	}
	
	// Model 是 Map or List 數組或集合
	
	@RequestMapping("/case05")
	public String case05(Model model) {
		
		// Arrays.asList() 固定 不可增加
		List<String> names1 = Arrays.asList("John" , "Mary" , "Helen"); 
		// names1.add("bob"); 500 錯誤不可增加
		model.addAttribute("data1",  names1);
		
		// 動態 可以增加
		List<String> names2 = new ArrayList<>(names1);
		names2.add("bob");
		model.addAttribute("data2" , names2);
		
		Map<String, Integer> fruits = new LinkedHashMap<>();
		fruits.put("watermelon", 100);
		fruits.put("mango", 100);
		model.addAttribute("data3" , fruits);
		return "show_data";
	}
	
	// 重定向
	// 從 server 端發出 重定向命令(放在 header 中)由 client 執行
	// Header -> Location
	// 不論內外網都可指派
	@RequestMapping("/case06")
	public String case06() {
		return "redirect:/index.jsp"; // 重定向到首頁
	}
	
	// 退後路徑
	// http://localhost:8080/spring.mvc/mvc/mv/case7
    // http://localhost:8080/spring.mvc/mvc/hello/welcome
	@RequestMapping("/case07")
	public String case07() {
		return "redirect:../hello/welcome"; // 重定向到首頁
	}
	
	// 外網指派
	@RequestMapping("/case08")
	public String case08() {
		return "redirect:https://www.twitch.tv/carry_game"; // 重定向到首頁
	}
	
	@RequestMapping("/case11")
	public ModelAndView case011() {
		ModelAndView mv = new ModelAndView();
		String data1 = "Hello ModelAndView";             // 資料(Model)
		String data2 = "2";
		String view = "/WEB-INF/view/show_data.jsp";    // 渲染(view)
		mv.setViewName(view);
		Map<String, Object> map = new HashMap<>();
		map.put("data1", data1);
		map.put("data2", data2);
		mv.addAllObjects(map);
		return  mv;
		}
}
