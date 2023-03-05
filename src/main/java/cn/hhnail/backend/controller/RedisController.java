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
 * @description: Redis分布式锁实现特定规则递增流水号
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
            String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String maxDocumentNo = redisTemplate.opsForValue().get(yyyyMMdd);
            // Thread.sleep(1000);
            if (maxDocumentNo == null || "".equals(maxDocumentNo)) {
                // 默认流水号从1开始
                System.out.println(getAutoDocumentNo(1));
                // 注意服务器时间回拨问题。这个过期时间，他不是一天的秒数来倒计时。而是距离当前一天的时间点。
                // 所以比如4号设置了一天，就是5号到期，如果把当前时间提前1天改到3号，这个还是5号才过期
                redisTemplate.opsForValue().set(yyyyMMdd, "2", 1, TimeUnit.DAYS);
            } else {
                int max = Integer.parseInt(maxDocumentNo);
                System.out.println(getAutoDocumentNo(max));
                redisTemplate.opsForValue().increment(yyyyMMdd);
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
