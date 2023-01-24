package cn.hhnail.backend.service;

import cn.hhnail.backend.bean.Hotel;

import java.util.List;

public interface HotelService {

    Hotel getHotelById(Long id);

    List<Hotel> getHotelList();
}
