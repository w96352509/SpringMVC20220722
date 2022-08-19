package spring.mvc.session14.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

// 自訂全局例外異常
@Component
public class MyGlobalHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		String referer = request.getHeader("Referer");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("session14/error");
		mv.addObject("referer", referer);
		mv.addObject("ex", "全局異常捕獲: " + ex);
		mv.addObject("message" , "你好");
		return mv;
	}
	
}
