package cn.itcast.bos.service.elasticsearch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.bos.domain.take_delivery.WayBill;
import cn.itcast.bos.index.WayBillIndexRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class SpringDataElasticSearchTest {

	@Autowired
	private WayBillIndexRepository wayBillIndexRepository;
	@Test
	public void test1(){
		Iterable<WayBill> wayBillList = wayBillIndexRepository.findAll();
		
		while(wayBillList.iterator().hasNext()){
			System.out.println(wayBillList.iterator().next()+"\n");
		}
	}
}
