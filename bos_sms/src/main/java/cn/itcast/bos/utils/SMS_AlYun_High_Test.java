package cn.itcast.bos.utils;

import org.apache.commons.lang3.StringUtils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SMS_AlYun_High_Test {
	/** 连接超时的时间设置 **/
	private static final String CONN_TIME_OUT = "10000";
	/** 读取超时时间 */
	private static final String READ_TIME_OUT = "10000";
	/** 在控制台申请的 access_key */
	private static final String ACCESS_KEY_ID = "";
	/** 在控制台申请的 access_key_secret */
	private static final String ACCESS_KEY_SECRET = "";
	/** 短信签名的名称,在“管理控制台”中，“签名管理”界面中的列表界面里 签名名称  */
	private static final String SIGN_NAME = "传智bos项目";
	/** 短信模板的 模版CODE */
	private static final String TEMPLATE_CODE = "SMS_82180012";
	/** 生成短信完整信息时，传递的变量名 */
	private static final String RANDOM_CODE = "smsCode";
	/**
	 * 封装阿里云的短信发送接口后，调用下面这个方法只需传入 接口必传参数即可
	 * @param telPhone 待接收验证码的手机号
	 * @param code 后台生成的验证码
	 * @return
	 * @throws Exception
	 */
	public static boolean sendSms(String telPhone,String code) throws Exception{
		return sendSms(new String[]{telPhone}, "{"+RANDOM_CODE+":'"+code+"'}");//{code:'123456'}
	}
	public static boolean sendSms(String[] telPhone,String paramJson) throws Exception {
		// 设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", CONN_TIME_OUT);
		System.setProperty("sun.net.client.defaultReadTimeout", READ_TIME_OUT);
		// 初始化ascClient需要的几个参数
		final String product = "Dysmsapi";// 短信API产品名称（短信产品名固定，无需修改）
		final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名（接口地址固定，无需修改）
		// 替换成你的AK
		// 初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		// 组装请求对象
		SendSmsRequest request = new SendSmsRequest();
		// 使用post提交
		request.setMethod(MethodType.POST);
		// 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		request.setPhoneNumbers(StringUtils.join(telPhone, ","));
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(SIGN_NAME);
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(TEMPLATE_CODE);
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		// 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		request.setTemplateParam(paramJson);
		// 请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		System.out.println(sendSmsResponse.getMessage());
		if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			// 请求成功
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) throws Exception {
		sendSms("18672304510","5201314");
	}
}
