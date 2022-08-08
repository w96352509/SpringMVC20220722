package spring.mvc.session11.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.mvc.session11.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {

  public List<User>	users = new CopyOnWriteArrayList<User>();
  {
	  users.add(new User("A01",18,new Date(),"大學","男",new String[] {"看電影","飛控"},"aaa"));
	  users.add(new User("A02",19,new Date(),"高中","女",new String[] {"看電影","飛控"},"bbb"));
	  users.add(new User("A03",20,new Date(),"國中","男",new String[] {"看電影","飛控"},"ccc"));
  }
  @GetMapping("/")
  public String index(Model model , @ModelAttribute User user) {
	  model.addAttribute("_method","POST"); // Hidden Filter
	  model.addAttribute("submitbuttonName", "新增");
	  model.addAttribute("users" , users);
	  // model.addAttribute("user",user); 因方法中有 @ModelAttribute 宣告 , 所以不用加
	  
	  return "session11/user";
  }
  
  @PostMapping("/")
  public String add(Model model , @ModelAttribute User user) {
	  users.add(user);
	  System.out.println(users);
	  return "redirect:./";
  }
	
  @GetMapping("/{index}")
  public String get(@PathVariable("index") int index , Model model , @RequestParam(name = "delete" , required = false , defaultValue = "false") boolean delete) {
	  model.addAttribute("user",users.get(index));
	  model.addAttribute("_method","PUT"); // Hidden Filter
	  model.addAttribute("submitbuttonName", "修改");
	  model.addAttribute("index", index);
	  model.addAttribute("users" , users);
	  System.out.println(delete+"123");
	  return "session11/user";
  }
  
  @PutMapping("/{index}")
  public String update(@PathVariable("index") int index , @ModelAttribute User user ) {
	 users.set(index, user);
	 return "redirect:./";
  }
  
  /*
  @DeleteMapping("/test/{index}")
  public String test(@PathVariable("index") int index,Model model) {
	  model.addAttribute("_method","DELETE");
	  System.out.println("處李");
	  return "redirect:../"+index;
  }
  */
  @RequestMapping(value = "/{index}" ,method = RequestMethod.DELETE )
  public String delete(@PathVariable("index") int index) {
	  users.remove(index);
	  // System.out.println("刪除成功");
	  return "redirect:./test";
  }
    
    @DeleteMapping("/test")
	public String test() {
		// System.out.println("處理");
		return "";
	}
  
  
  
}
