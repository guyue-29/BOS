package cn.itcast.cw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_test")
public class TestBean {
	@Id
	@GeneratedValue
	@Column(name = "C_ID")
	private Integer id; // 主键
	@Column
	private String testName;
	@Column
	private String testAddress;
	@Column
	private int testAge;
	@Column
	private int testSex;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestAddress() {
		return testAddress;
	}

	public void setTestAddress(String testAddress) {
		this.testAddress = testAddress;
	}

	public int getTestAge() {
		return testAge;
	}

	public void setTestAge(int testAge) {
		this.testAge = testAge;
	}

	public int getTestSex() {
		return testSex;
	}

	public void setTestSex(int testSex) {
		this.testSex = testSex;
	}

}
