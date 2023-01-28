package cn.hhnail.backend.service;

import cn.hhnail.backend.bean.Hotel;

import java.util.List;

public interface HotelService {

    Hotel getHotelById(Long id);

    List<Hotel> getHotelList();

    /**
     * 新增酒店（数据库）
     */
    void addHotel(Hotel hotel);

    /**
     * 删除酒店（数据库）
     */
    void deleteHotel(String id);

    /**
     * 新增酒店（ES）
     */
    void doHotelInsertOrUpdate4Es(Long id);

    /**
     * 删除酒店（ES）
     */
    void doHotelDelete4Es(Long id);
}
