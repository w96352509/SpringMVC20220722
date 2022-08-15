package spring.mvc.session13.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session13.entity.Employee;
import spring.mvc.session13.repository.EmployeeDao;

@Controller
@RequestMapping("/jdbc/employee")
public class EmployeeController {

	@Autowired
	private EmployeeDao employeeDao;
	
	@GetMapping("/")
	public String index(Model model , @ModelAttribute Employee employee) { // 首頁
		model.addAttribute("_method","POST");
		model.addAttribute("employees", employeeDao.query());
		return "session13/employee";
	}
	
	@PostMapping("/")
	public String add(@Valid Employee employee , BindingResult result , Model model) {
      if(result.hasErrors()) {
    	model.addAttribute("_method","POST");
  		model.addAttribute("employees", employeeDao.query());
  		model.addAttribute("employee" , employee);
  		return "session13/employee";
      }
        employeeDao.add(employee);
        return "redirect:./";
	}	
	
	@GetMapping("/{eid}")
	public String get(@PathVariable("eid") Integer eid , Model model) {
		model.addAttribute("employee",employeeDao.get(eid));
		model.addAttribute("_method","PUT");
		model.addAttribute("employees", employeeDao.query());
		return "session13/employee";
	}
	
	@PutMapping("/")
	public String update(@Valid Employee employee ,BindingResult result , Model model) {
		if(result.hasErrors()) {
			model.addAttribute("employee",employee);
			model.addAttribute("_method","PUT");
			model.addAttribute("employees", employeeDao.query());
			return "session13/employee";
		}
		   employeeDao.update(employee);
		   return "redirect:./";
	}
	
	@DeleteMapping("/")
	public String delete(Employee employee , Model model) {
		try {
			employeeDao.delete(employee.getEid());
		} catch (Exception e) {
			model.addAttribute("employee",employee);
			model.addAttribute("_method","PUT");
			model.addAttribute("employees", employeeDao.query());
			model.addAttribute("message" , "員工尚有工作無法刪除");
			return "session13/employee";
		} 
	       return "redirect:./";
	}
	
}
