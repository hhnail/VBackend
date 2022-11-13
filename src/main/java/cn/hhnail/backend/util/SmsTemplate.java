package cn.hhnail.backend.util;

import cn.hhnail.backend.vo.response.AppResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 发送手机短信验证码的模板类
 * @author 尚硅谷
 *
 */
@Slf4j
@Component
public class SmsTemplate {
	
	@Value("${sms.host}")
	String host ;
	
	@Value("${sms.path}")
	String path ;
	
	@Value("${sms.method}")
	String method ;
	
	@Value("${sms.appcode}")
	String appcode ;

	public static void main(String[] args) {
		System.out.println("test @Value");
		SmsTemplate template = new SmsTemplate();
		System.out.println(template.host);
	}
	
	public AppResponse<String> sendCode(Map<String, String> querys) {
		
		log.debug("开始发送短信-参数：{}", querys);
		
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);

		Map<String, String> bodys = new HashMap<String, String>();

		try {

			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			System.out.println(response.toString());
			// 获取response的body
			HttpEntity entity = response.getEntity();
			System.out.println(EntityUtils.toString(response.getEntity()));
			
			log.debug("开始发送短信-成功：{},{}", querys.get("mobile"),querys.get("param"));
			
			return AppResponse.ok("OK");
		} catch (Exception e) {
			log.debug("开始发送短信-失败：{}", e.getMessage());
			return AppResponse.fail("fail");
		}
	}
}
