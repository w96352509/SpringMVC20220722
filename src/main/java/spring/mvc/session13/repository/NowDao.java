package spring.mvc.session13.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NowDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public String getnow() {
	  String sql ="select NOW() as now"	;
	  return jdbcTemplate.queryForObject(sql,String.class);
	}
	
}
