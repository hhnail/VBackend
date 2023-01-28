package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.bean.Hotel;
import cn.hhnail.backend.bean.HotelDTO;
import cn.hhnail.backend.enums.MqExchangeEnum;
import cn.hhnail.backend.enums.MqRoutingKey;
import cn.hhnail.backend.mapper.HotelMapper;
import cn.hhnail.backend.service.HotelService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author Hhnail
 * @version 1.0
 * @description: 【酒店】服务实现类
 * @date 2023/1/24 14:59
 */
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelMapper hotelMapper;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    RestHighLevelClient esClient;

    @Override
    public Hotel getHotelById(Long id) {
        return hotelMapper.selectById(id);
    }

    @Override
    public List<Hotel> getHotelList() {
        QueryWrapper queryWrapper = null;
        return hotelMapper.selectList(queryWrapper);
    }

    @Override
    public void addHotel(Hotel hotel) {

        int affectedRows = hotelMapper.insert(hotel);

        if (affectedRows > 0) {
            // 通知mq
            rabbitTemplate.convertAndSend(
                    MqExchangeEnum.HOTEL_EXCHANGE.getCode(),
                    MqRoutingKey.HOTEL_INSERT_KEY.getCode(),
                    hotel.getId()
            );
        }

    }

    @Override
    public void deleteHotel(String id) {
        int affectedRows = hotelMapper.deleteById(id);

        if (affectedRows > 0) {
            // 通知mq
            rabbitTemplate.convertAndSend(
                    MqExchangeEnum.HOTEL_EXCHANGE.getCode(),
                    MqRoutingKey.HOTEL_DELETE_KEY.getCode(),
                    id
            );
        }
    }

    @Override
    public void doHotelInsertOrUpdate4Es(Long id) {
        try {
            Hotel hotelDO = getHotelById(id);
            HotelDTO hotelDTO = new HotelDTO(hotelDO);

            IndexRequest request = new IndexRequest("hotel").id(id.toString());
            request.source(JSON.toJSONString(hotelDTO), XContentType.JSON);
            esClient.index(request, RequestOptions.DEFAULT);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doHotelDelete4Es(Long id) {
        try {

            DeleteRequest request = new DeleteRequest(
                    "hotel", // ES索引库
                    id.toString() // 要删除数据的id
            );

            esClient.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
