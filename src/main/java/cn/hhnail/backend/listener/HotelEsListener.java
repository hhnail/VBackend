package cn.hhnail.backend.listener;

import cn.hhnail.backend.enums.MqQueueEnum;
import cn.hhnail.backend.service.HotelService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Hhnail
 * @version 1.0
 * @description: ES监听器-酒店实体
 * @date 2023/1/28 11:40
 */
@Component
public class HotelEsListener {

    @Autowired
    HotelService hotelService;


    // @RabbitListener(queues = MqQueueEnum.HOTEL_INSERT_QUEUE.getCode())
    @RabbitListener(queues = "${spring.rabbitmq.my-queue.hotel-insert-queue}")
    // @RabbitListener(queues = "hotel.insert.queue")
    public void listenerHotelInsertOrUpdate(Long id) {
        hotelService.doHotelInsertOrUpdate4Es(id);
    }

    @RabbitListener(queues = "${spring.rabbitmq.my-queue.hotel-delete-queue}")
    // @RabbitListener(queues = "hotel.delete.queue")
    public void listenerHotelDelete(Long id) {
        hotelService.doHotelDelete4Es(id);

    }


}
