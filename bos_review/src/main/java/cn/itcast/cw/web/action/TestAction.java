package cn.itcast.cw.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import cn.itcast.cw.domain.TestBean;
import cn.itcast.cw.service.TestService;
import cn.itcast.cw.web.common.BaseAction;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class TestAction extends BaseAction<TestBean> {

	@Autowired
	private TestService testService;

	@Action(value = "test_init", results = { @Result(name = "success", type="json") })
	public String testEnvironment() {
		System.out.println("================成功访问test_init URL请求");
		TestBean test = new TestBean();
		test.setTestAddress("湖北省武汉市洪山区");
		test.setTestName("测试环境搭建是否成功");
		test.setTestAge(1);
		test.setTestSex(0);
		// 调用base类中，压入植栈的方法
		pushDataToValueStack(test);
		return SUCCESS;
	}

	
//	private String testName;
//	private int testAge;
//	public void setTestName(String testName) {
//		this.testName = testName;
//	}
//	public void setTestAge(int testAge) {
//		this.testAge = testAge;
//	}

	@Action(value = "/test_findall", results = { @Result(name = "success", type = "json") })
	public String findAll() {
		// ctrl + shift + O : 快速更新导入包
		//请求进入到Action，即可表示前端 的URL请求已经成功
		//第二步：在action中调用 Service层的实现方式 （注意：Action只能通过service来调用 dao层的方法。action永远不允许直接 访问Dao层（在集成框架开发中，我们会默认或习惯将Service层定义事务）
		//查询要 返回给前端 的结果（注意：action只做两件事：接收参数和返回结果）
		Pageable pageable = new PageRequest(page-1, rows);
		//如果是带条件 的查询，则我们需要实现一个Specification接口的实现类
		Specification<TestBean> specification = new Specification<TestBean>() {
			@Override
			//root : 获取实体类中的属性对应的表字段名 ;  query:暂时没用到时  ;  cb:拼接查询条件 的
			public Predicate toPredicate(Root<TestBean> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//首先需要 创建一个集合来保存查询的条件 
				List<Predicate> predicateList = new ArrayList<>();
				if(StringUtils.isNotBlank(model.getTestName())){
					Predicate pr1 = cb.like(root.get("testName").as(String.class), "%"+model.getTestName()+"%");
					predicateList.add(pr1);
				}
				if(model!= null && model.getTestAge() != 0 ){
					Predicate pr2 = cb.equal(root.get("testAge").as(Integer.class), model.getTestAge());
					predicateList.add(pr2);
				}
				
				return cb.and(predicateList.toArray(new Predicate[0]));
			}
		};
		
		
		Page<TestBean> pageData = testService.pageQuery(specification,pageable);
		// 将结果压入植栈
		pushDataToValueStack(pageData);
		return SUCCESS;
	}
	
	
	@Action(value="test_save",results={@Result(name="success",type="redirect",location="./test_info.html")})
	public String testSave(){
		//进行保存操作
		testService.saveTest(model);
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
}
