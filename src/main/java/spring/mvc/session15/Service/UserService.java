package spring.mvc.session15.Service;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import spring.mvc.session15.entity.User;

@Service
public class UserService {

	public User add() {
		Faker faker = new Faker();
		Random random = new Random();
		String name = faker.name().firstName();
		Date date = faker.date().birthday();
		User user = new User(name , "1234" , date);
		return user;
	}
	
}
