package spring.mvc.session08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mv")
public class MVController {

	@RequestMapping(value = {"/case01"},method = {RequestMethod.GET})
	public ModelAndView case1() {
		String data = "Hello ModelAndView";             // 資料(Model)
		String view = "/WEB-INF/view/show_data.jsp";    // 渲染(view)
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("data" , data);
	    modelAndView.setViewName(view);
	    return modelAndView;
	}
	
}
