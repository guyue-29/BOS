package cn.itcast.cw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.cw.dao.TestBeanRepository;
import cn.itcast.cw.domain.TestBean;
import cn.itcast.cw.service.TestService;
@Service
@Transactional
public class TestServiceImpl implements TestService {
	@Autowired
	private TestBeanRepository testRepository;

	@Override
	public Page<TestBean> pageQuery(Specification<TestBean> spec,Pageable pageable) {
		Page<TestBean> page = testRepository.findAll(spec, pageable);
		return page;
	}

	@Override
	public void saveTest(TestBean model) {
		testRepository.save(model);
	}

}
