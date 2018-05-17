package com.itcast.cw.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

import com.itcast.cw.domain.User;

// 自定义Realm ，实现安全数据 连接
@Service("bosRealm")
public class BosRealm extends AuthorizingRealm {

	@Override
	// 认证...
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		System.out.println("shiro 认证管理... ");
		// 转换token
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		System.out.println("---------登录的用户名：" + usernamePasswordToken.getUsername() + "------------");
		User user = new User("admin", "admin");
		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
	}

	@Override
	// 授权...
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		System.out.println("shiro 授权管理...");
		//用于给当前认证成功后的用户添加角色权限和其他权限的类对象
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addRole("user");
//		authorizationInfo.addRole("admin");
		
		return authorizationInfo;
	}

}
