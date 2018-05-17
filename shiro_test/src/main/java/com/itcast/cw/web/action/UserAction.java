package com.itcast.cw.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itcast.cw.domain.User;
import com.itcast.cw.web.common.BaseAction;

@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	@Action(value = "/user_login", results = { 
			@Result(name = "success", location = "/index.html") })
	public String login() {
		System.out.println("=============登录成功=============登录用户名："+model.getUsername()+"登录密码："+model.getPassword());

		Subject subject = SecurityUtils.getSubject();
		AuthenticationToken token = new UsernamePasswordToken(model.getUsername(), model.getPassword());
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	@RequiresRoles(value={"user","admin"},logical=Logical.OR)
	@Action(value="/test_requires",results={@Result(name="success",location="/author_success.html")})
	public String testRequires(){
		System.out.println("==========访问权限细粒度控制成功===========");
		return SUCCESS;
	}
}
