package spring.mvc.session13;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.mvc.session13.entity.Employee;
import spring.mvc.session13.repository.EmployeeDao;

public class EmployeeTest {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		EmployeeDao employeeDao = ctx.getBean(EmployeeDao.class);
		Employee employee = new Employee();
		employeeDao.delete(6);
		System.out.println(employeeDao.query());
	}
	
}
