package spring.mvc.session13.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import spring.mvc.session13.entity.Job;
import spring.mvc.session13.repository.EmployeeDao;
import spring.mvc.session13.repository.JobDao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/jdbc/job")
public class JobController {

	@Autowired
	private JobDao jobDao;
	@Autowired
	private EmployeeDao employeeDao;

	private int getPageCount() {
		int pageCount = (int)Math.ceil((double)jobDao.getCount()/jobDao.LIMIT);
	    return pageCount;
	}
	
	@GetMapping("/")
	public String index(Model model, @ModelAttribute Job job) {
		model.addAttribute("_method", "POST");
		model.addAttribute("jobs", jobDao.query());
		model.addAttribute("pageCount",getPageCount());
		model.addAttribute("employees", employeeDao.query());
		return "session13/job";
	}

	@GetMapping("/page/{num}")
	public String page(@PathVariable("num") Integer num , @ModelAttribute Job job , Model model) {
		int offset = (num-1) * JobDao.LIMIT; // -1 是因為 offset是(index)
		model.addAttribute("_method", "POST");
		model.addAttribute("jobs", jobDao.queryPage(offset));
		model.addAttribute("pageCount",getPageCount());
		model.addAttribute("employees", employeeDao.query());
		return "session13/job";
	}
	
	@GetMapping("/{jid}")
	public String get(@PathVariable("jid") Integer jid, Model model) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("jobs", jobDao.query());
		model.addAttribute("job", jobDao.get(jid));
		model.addAttribute("pageCount",getPageCount());
		model.addAttribute("employees", employeeDao.query());
		return "session13/job";
	}

	@PostMapping("/")
	public String add(@Valid Job job, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("_method", "POST");
			model.addAttribute("jobs", jobDao.query());
			model.addAttribute("pageCount",getPageCount());
			model.addAttribute("job", job);
			return "session13/job";
		}
		jobDao.add(job);
		return "redirect:./";
	}

	@PutMapping("/")
	public String update(@Valid Job job, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("_method", "PUT");
			model.addAttribute("jobs", jobDao.query());
			model.addAttribute("pageCount",getPageCount());
			model.addAttribute("job", job);
			return "session13/job";
		}
		jobDao.update(job);
		return "redirect:./";
	}

	@DeleteMapping("/")
	public String delete(Job job , Model model) {
		try {
			jobDao.delete(job.getJid());
		} catch (Exception e) {
			model.addAttribute("_method", "PUT");
			model.addAttribute("jobs", jobDao.query());
			model.addAttribute("pageCount",getPageCount());
			model.addAttribute("job", job);
			return "session13/job";
		}
		return "redirect:./";
	}
}
