package cn.itcast.cw.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.itcast.cw.domain.TestBean;

public interface TestBeanRepository extends JpaRepository<TestBean, Integer>, 
JpaSpecificationExecutor<TestBean> {
	/*
	 * 
	 * 直接继承JpaRepository就能实现数据库的基本操作原因：
	 * 
	 * 实现原理：动态代理（AOP)
	 * aop检测接口实例化之前，是否是继承的JpaRepository，如果是，则会自动创建SimpJpaRepository实现类
	 * 
	 * 继承Jpa接口时，有两个核心 接口： 增、删、改及（基本查询）： JpaRepository 分页查询和带条件 查询 ：
	 * JpaSpecificationExecutor
	 * 
	 * 
	 * 
	 */
}
