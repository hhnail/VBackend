package cn.hhnail.backend.config;

/**
 * @author Hhnail
 * @version 1.0
 * @description: MQ配置
 * @date 2023/1/28 10:41
 */

import cn.hhnail.backend.enums.MqExchangeEnum;
import cn.hhnail.backend.enums.MqQueueEnum;
import cn.hhnail.backend.enums.MqRoutingKey;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    /**
     * 声明交换机
     *
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(
                MqExchangeEnum.HOTEL_EXCHANGE.getCode(),
                true,
                false
        );
    }


    /**
     * 声明插入队列
     *
     * @return
     */
    @Bean
    public Queue insertQueue() {
        return new Queue(
                MqQueueEnum.HOTEL_INSERT_QUEUE.getCode(),
                true
        );
    }


    /**
     * 声明删除队列
     *
     * @return
     */
    @Bean
    public Queue deleteQueue() {
        return new Queue(
                MqQueueEnum.HOTEL_DELETE_QUEUE.getCode(),
                true
        );
    }


    /**
     * 绑定队列和交换机
     *
     * @return
     */
    @Bean
    public Binding insertQueueBinding() {
        return BindingBuilder
                .bind(insertQueue())
                .to(topicExchange())
                .with(MqRoutingKey.HOTEL_INSERT_KEY.getCode());
    }

    @Bean
    public Binding deleteQueueBinding() {
        return BindingBuilder
                .bind(deleteQueue())
                .to(topicExchange())
                .with(MqRoutingKey.HOTEL_DELETE_KEY.getCode());
    }

}
