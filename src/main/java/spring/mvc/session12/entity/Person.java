package spring.mvc.session12.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;



public class Person {

	// @NotEmpty(message = "姓名不可是空值")
	// @Size(min = 2 , max = 50 , message = "名字範圍必須介於2~50之間")
	@NotEmpty(message = "{person.name.empty}")
	@Size(min = 2 , max = 50 , message = "{person.name.range}")
	private String name;    // 姓名
	
	// @NotNull(message = "年齡不可以是空值")
	// @Range(min = 18 , max = 99 , message = "名字範圍必須介於18~99之間") // hibernate
	@NotNull(message ="{person.age.empty}")
	@Range(min = 18 , max = 99 , message = "person.age.range") // hibernate
	private Integer age;    // 年齡
	
	// @NotNull(message = "需勾選")
	@NotNull(message = "{person.member.empty}")
	private Boolean member; // 是否是會員
	
	// @NotNull(message = "生日不可是空值")
	// @Past(message    = "生日不可大於現在日期") // 輸日時間不可大於現在日期 
	@NotNull(message = "{person.birth.empty}")
	@Past(message    = "{person.birth.range}") // 輸日時間不可大於現在日期
	@JsonFormat(pattern = "yyyy-MM-dd" , timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;     // 生日

	public Person() {
		
	}
	
	public Person(String name, Integer age, Boolean member, Date birth) {
		this.name = name;
		this.age = age;
		this.member = member;
		this.birth = birth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getMember() {
		return member;
	}

	public void setMember(Boolean member) {
		this.member = member;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", member=" + member + ", birth=" + birth + "]";
	}
	
}
