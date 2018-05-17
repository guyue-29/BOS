package cn.itcast.crm.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import cn.itcast.crm.domain.Customer;

/**
 * 客户操作
 * 
 * @author itcast
 *
 */

public interface CustomerService {

	// 查询所有未关联客户列表
	@Path("/noassociationcustomers")
	@GET
	@Produces({ "application/xml", "application/json" })
	public List<Customer> findNoAssociationCustomers();

	// 已经关联到指定定区的客户列表
	@Path("/associationfixedareacustomers/{fixedareaid}")
	@GET
	@Produces({ "application/xml", "application/json" })
	public List<Customer> findHasAssociationFixedAreaCustomers(
			@PathParam("fixedareaid") String fixedAreaId);

	// 将客户关联到定区上 ， 将所有客户id 拼成字符串 1,2,3
	@Path("/associationcustomerstofixedarea")
	@PUT
	public void associationCustomersToFixedArea(
			@QueryParam("customerIdStr") String customerIdStr,
			@QueryParam("fixedAreaId") String fixedAreaId);

	@Path("/customer")
	@POST
	@Consumes({ "application/xml", "application/json" })
	/*
	 * postman中，测试时，传入的custormer数据：
	 * 	{
	 * 		"参数对象中@XmlRootElement中配置的name":
	 * 		{
	 * 			属性名:属性值（key-value)
	 * 		}
	 * }
	 * 
	 * 
	 * 
	 */
	public void regist(Customer customer);

	@Path("/customer/telephone/{telephone}")
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Customer findByTelephone(@PathParam("telephone") String telephone);

	@Path("/customer/updatetype/{telephone}")
	@GET
	public void updateType(@PathParam("telephone") String telephone);

	@Path("customer/login")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Customer login(@QueryParam("telephone") String telephone,
			@QueryParam("password") String password);

	@Path("/customer/findFixedAreaIdByAddress")
	@GET
	@Consumes({ "application/json", "application/xml" })
	public String findFixedAreaIdByAddress(@QueryParam("address") String address);
	
	@Path("/queryAllCustomer")
	@GET
	@Produces({"application/json", "application/xml"})
	public List<Customer> queryAllCustomer();

}