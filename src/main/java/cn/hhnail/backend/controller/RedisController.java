package cn.hhnail.backend.controller;

import cn.hhnail.backend.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Hhnail
 * @version 1.0
 * @description: TODO
 * @date 2023/3/4 22:05
 */
@RestController
@RequestMapping("/vapi")
public class RedisController {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @PostMapping("/lock")
    public void lock() throws Exception {

        // 分布式锁占坑
        Boolean lock = redisTemplate.opsForValue().setIfAbsent(
                "documentNoLock",
                "value",
                30,
                TimeUnit.SECONDS
        );
        if (lock) {
            // 加锁成功，执行业务逻辑
            // todo business
            String maxDocumentNo = redisTemplate.opsForValue().get("currentDocumentNo");
            // Thread.sleep(1000);
            if (maxDocumentNo == null || "".equals(maxDocumentNo)) {
                System.out.println(getAutoDocumentNo(1));
                redisTemplate.opsForValue().set("currentDocumentNo", "2");
            } else {
                int max = Integer.parseInt(maxDocumentNo);
                System.out.println(getAutoDocumentNo(max));
                max++;
                redisTemplate.opsForValue().set("currentDocumentNo", "" + max);
            }
            redisTemplate.delete("documentNoLock");   //删除key，释放锁
        } else {
            // 加锁失败，重试
            Thread.sleep(100);
            lock();
        }
    }


    private String getAutoDocumentNo(Integer maxId) {
        String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String completedMaxId = StringUtils.leftComplete(5, "0", maxId);
        return yyyyMMdd + completedMaxId;
    }


    @PostMapping("/set")
    public void set() throws Exception {
        // 占分布式锁，去redis占坑
        // 1. 分布式锁占坑
        Boolean result = redisTemplate.opsForValue().setIfAbsent(
                "documentNoLock",
                "value"
        );
        System.out.println(result);
    }


}
