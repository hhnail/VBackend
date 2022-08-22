package cn.hhnail.backend.controller;

import cn.hhnail.backend.bean.Test;
import cn.hhnail.backend.mapper.DemoMapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vapi")
@Api(value = "测试控制器", tags = {"测试"})
public class DemoController {

    @Autowired
    DemoMapper demoMapper;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

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
    public void demoMp() {
        System.out.println(("----- selectAll method test ------"));
        List<Test> userList = demoMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @ApiOperation(value = "测试redis")
    @GetMapping("/testRedis")
    public void testRedis() {
        redisTemplate.opsForValue().set("user1", "zhangsan");
    }


}
