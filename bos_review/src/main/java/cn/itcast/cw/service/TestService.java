package cn.itcast.cw.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.cw.domain.TestBean;

public interface TestService {
	Page<TestBean> pageQuery(Specification<TestBean> spec,Pageable pageable);

	void saveTest(TestBean model);

}
