package spring.mvc.session13.repository;

import java.util.List;

import spring.mvc.session13.entity.Employee;


public interface EmployeeDao {
    // 每頁筆數
	int LIMIT = 5;
	
	// 新增
	int add(Employee employee);
	
	// 修改
	int update(Employee employee);
	
	// 刪除
	int delete(Integer eid);
	
	// 查詢所有筆數
	int getCount();
	
	// 單筆查詢
	Employee get(Integer eid);
	
	// 不分頁查詢(全部)
	List<Employee> query();
	
	// 分頁查詢(判斷 HttpSession值 決定分頁狀態)
	List<Employee> query(Object httpSessionValue);
	
	// 分頁查詢
	List<Employee> queryPage(int offset);
	
	
}
