package spring.mvc.session14.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {
	
	@ExceptionHandler({RuntimeException.class})
	public String catchException(Exception ex, Model model, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		model.addAttribute("ex", "GlobalController 全局異常捕獲: " + ex);
		model.addAttribute("referer", referer);
		model.addAttribute("message","123");
		return "session14/error";
	}
	
	@ModelAttribute(name = "global_map")
	public Map<String, Object> mydata() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("公司名稱", "ABC股份有限公司");
		map.put("聯絡人", "Jack");
		map.put("電話", "02-1234-5678");
		return map;
	}
}
