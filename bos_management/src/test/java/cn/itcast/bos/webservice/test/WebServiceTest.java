package cn.itcast.bos.webservice.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

import cn.itcast.bos.domain.constant.Constants;
import cn.itcast.crm.domain.Customer;

public class WebServiceTest {
	
	@Test
	public void testWebService(){
		String address = "武汉市洪山区武汉光谷软件园";
		try {
			address = new String(address.getBytes(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String requestUrl = null;
			try {
				requestUrl = Constants.CRM_MANAGEMENT_URL + "/services/customerService/customer/findFixedAreaIdByAddress?address="
							+ address; //URLEncoder.encode(address,"UTF-8")
				System.out.println("详细地址2="+URLEncoder.encode(address,"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		
		// 自动分单逻辑，基于CRM地址库完全匹配，获取定区，匹配快递员
		String fixedAreaId  = WebClient
					.create(requestUrl)
					.accept(MediaType.APPLICATION_JSON).get(String.class);  
		System.out.println("对应定区=b"+fixedAreaId);
	}
	
	@Test
	public void loginTest(){
		Customer customer = WebClient.create(Constants.CRM_MANAGEMENT_URL + "/services/customerService/customer/login?telephone=18672304510&password=123123").accept(MediaType.APPLICATION_JSON).get(Customer.class);
		System.out.println("customer="+customer);
	}
}
