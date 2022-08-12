package spring.mvc.session13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.mvc.session13.repository.NowDao;

@RestController
@RequestMapping("/jdbc/now")
public class NowController {

	@Autowired
	private NowDao nowDao;
	
	@RequestMapping("/")
	public String getNow() {
		return  "資料庫現在時刻 :"+nowDao.getnow();
	}
	
}
