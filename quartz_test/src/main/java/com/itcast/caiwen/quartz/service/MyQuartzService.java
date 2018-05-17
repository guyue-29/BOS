package com.itcast.caiwen.quartz.service;

import org.springframework.stereotype.Service;

@Service
public class MyQuartzService {
	public void sayHello() {
		System.out.println("hello,这里是实现定时业务逻辑的 service层方法 !!!~~");
	}
}
