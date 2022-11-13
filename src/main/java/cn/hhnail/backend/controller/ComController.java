package cn.hhnail.backend.controller;

import cn.hhnail.backend.service.ComService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vapi")
@Api(value = "公共控制器")
public class ComController {

    Logger logger = LoggerFactory.getLogger(ComController.class);

    @Autowired
    ComService comService;

    @PostMapping("/test")
    public List<Object> test() {
        List<Object> response = comService.test();
        logger.info("v101= test", response);
        return response;
    }


}
