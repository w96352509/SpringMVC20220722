package spring.mvc.session15.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

import spring.mvc.session15.entity.User;
import spring.mvc.session15.repostory.UserRepository;

@RestController
@RequestMapping("/rest/user")
public class UserRestContoller {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/auto_add") // 自動新增 user 資料 , 並查找全資料
	public List<User> addAuto() {
		User user = new User();
		Faker faker = new Faker();
		user.setName(faker.name().firstName());
		user.setPassword(String.format("%04d", new Random().nextInt(1000)));
		user.setBirth(faker.date().birthday());
		userRepository.save(user);
		return userRepository.findAll();
	}
	
}