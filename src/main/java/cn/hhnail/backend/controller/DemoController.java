package cn.hhnail.backend.controller;

import cn.hhnail.backend.bean.Test;
import cn.hhnail.backend.mapper.DemoMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vapi")
@Api(value = "测试控制器", tags = {"测试"})
public class DemoController {

	Logger logger = LoggerFactory.getLogger(DemoController.class);


	@Autowired
	DemoMapper demoMapper;

	@ApiOperation(value = "测试POST请求")
	@PostMapping("/demoPost")
	@ApiResponses(value = {
			@ApiResponse(code = 1000, message = "成功"),
			@ApiResponse(code = 1001, message = "失败"),
			@ApiResponse(code = 1002, response = String.class, message = "缺少参数")})
	public String demoPost(@ApiParam("电影名称") @RequestParam("filmName") String filmName) {
		Test testBean = new Test();
		return "demoPost success!";

	}

	@ApiOperation(value = "测试GET请求")
	@GetMapping("/demoGet")
	public String demoGet() {

		return "demoGet success!";

	}


	@ApiOperation(value = "测试mp")
	@GetMapping("/demoMp")
	public String demoMp() {
		System.out.println(("----- selectAll method test ------"));
		List<Test> userList = demoMapper.selectList(null);
		userList.forEach(System.out::println);
		return "注入成功！";
	}

	@ApiOperation(value = "测试mp")
	@PostMapping("/testAutowired")
	public final String testAutowired(@RequestBody String str1) {
		System.out.println(("----- selectAll method test ------"));
		List<Test> userList = demoMapper.selectList(null);
		userList.forEach(System.out::println);
		return "注入成功！";
	}

}
