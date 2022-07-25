package spring.mvc.session08.controller;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session08.entity.User;

@Controller
@RequestMapping("/hello")
public class HelloController {

	/*
	 * 1. 執行路徑: /mvc/hello/welcome /mvc 在 web.xml 中有定義 /hello 找到 HelloController
	 * /welcome 執行 welcome() 方法
	 */
	@RequestMapping("/welcome")
	@ResponseBody // 直接將資料回應給前端
	public String welcome() {
		return "Welcome SpringMVC !";
	}

	/*
	 * 2. ?後面帶參數 @RequestParam 執行路徑: /mvc/hello/sayhi?name=John&age=18
	 */
	@RequestMapping(value = "/sayhi")
	@ResponseBody
	public String sayHi(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "age", defaultValue = "10") Integer age) {

		return String.format("Hi %s %d", name, age);
	}

	/*
	 * 3. Lab 練習 執行路徑: /mvc/hello/bmi?h=170&w=60 執行結果: bmi = 20.76
	 */
	@RequestMapping(value = "/bmi")
	@ResponseBody
	public String bmi(@RequestParam(value = "h") Double h, @RequestParam(value = "w") Double w) {
		double bmi = w / Math.pow(h / 100, 2);
		return String.format("bmi = %.2f", bmi);
	}

	/*
	 * 4. 同名多參數資料 執行路徑: /mvc/hello/age?age=18&age=19&age=20 計算出: 資料筆數,總和,平均,最大值,最小值
	 */
	@RequestMapping(value = "/age")
	@ResponseBody
	public String age(@RequestParam("age") List<Integer> ageList) {
		// Int 的 統計物件
		IntSummaryStatistics stat = ageList.stream().mapToInt(Integer::intValue).summaryStatistics();
		return String.format("%s", stat.toString());
	}

	/*
	 * 5. Map 參數(常用於 form 表單) 執行路徑:
	 * /mvc/hello/person?name=Tomcat&score=95.5&age=18&pass=true
	 */
	@RequestMapping(value = "/person")
	@ResponseBody
	public String getPerson(@RequestParam Map<String, String> person) {
		return person.toString();
	}

	/*
	 * 6. pojo(entity) 參數自動配置 執行路徑:
	 * /mvc/hello/user?name=Tomcat&score=95.5&age=18&pass=true
	 */
	@RequestMapping(value = "/user")
	@ResponseBody
	public String getUser(User user) {
		return user.toString();
	}
	/*
	 * 7. 在 body 傳送 json 資料 { "name" : "John", "age" : 18, "score": 90.5, "pass" :
	 * true } 執行路徑 : /mvc/hello/create/user
	 * client Header 端 + Content-Type : application;charset=utf-8 
	 */

	@PostMapping(value = "/create/user", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public User createUser(@RequestBody User user) {
		return user;
	}
	/*
	 * 8. 路徑參數 : @PathVarible 執行路徑 : /mvc/hello/exam/75 -> 印出結果 75 pass 執行路徑 :
	 * /mvc/hello/exam/45 -> 印出結果 75 fail
	 */

	@RequestMapping("/exam/{score}")
	@ResponseBody
	public String examscore(@PathVariable("score") Integer score) {
		return String.format("%d %s", score, (score >= 60) ? "pass" : "fail");
	}

	/*
	 * 9. 路徑參數 @PathVariable(萬用字元 * 任意多字 , ? 任意一字) 執行路徑 : /mvc/hello/any/abc/java8
	 * 執行路徑 : /mvc/hello/any/defgh/java9
	 */
	@RequestMapping("/any/*/java?")
	@ResponseBody
	public String any() {
		return "any";
	}

	/*
	 * @RequsetParam + @PathVariable 
	 * 執行路徑 : /mvc/hello/calc/add?x=30&y=20 -> Result: 50 
	 * 執行路徑 : /mvc/hello/calc/sub?x=30&y=20 -> Result : 10 
	 * 執行路徑 : /mvc/hello/calc/sub?y=20 -> Result : -20 
	 * 執行路徑 : /mvc/hello/calc/add -> Result: 0
	 */

	@RequestMapping("/calc/{exp}")
	@ResponseBody
	public String calc(@PathVariable("exp") String exp,
			@RequestParam(value = "x", required = false , defaultValue = "0") Optional<Integer> x,
			@RequestParam(value = "y", required = false , defaultValue = "0") Optional<Integer> y) {
		if (x.isPresent() && y.isPresent()) {
			switch (exp) {
			case "add":
				return x.get() + y.get() + "";
			case "sub":
				if (x.get().equals(0)|| x.get()==null) {
					return -y.get()+"";
				}
				return x.get() - y.get() + "";
			default:
				return "None";
			}
		}
		if (x.isPresent()) {
			return x.get() + "";
		}
		if (y.isPresent()) {
			return y.get() + "";
		}
		return "0";
	}
}
