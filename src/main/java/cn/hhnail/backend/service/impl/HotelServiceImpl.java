package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.bean.Hotel;
import cn.hhnail.backend.mapper.HotelMapper;
import cn.hhnail.backend.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Hotel getHotelById(Long id) {
        return hotelMapper.selectById(id);
    }
}
