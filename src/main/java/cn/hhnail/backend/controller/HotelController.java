package cn.hhnail.backend.controller;

import cn.hhnail.backend.bean.Hotel;
import cn.hhnail.backend.service.HotelService;
import cn.hhnail.backend.vo.response.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Hhnail
 * @version 1.0
 * @description: 测试ES、酒店实体控制器
 * @date 2023/1/28 10:21
 */
@RestController
@RequestMapping("/vapi/hotel")
@Api(value = "酒店实体控制器")
public class HotelController {

    @Autowired
    HotelService hotelService;

    /**
     * 新增-酒店
     *
     * @return
     */
    @PostMapping("/addHotel")
    public Map<String, Object> addHotel(@RequestBody Hotel hotel) {

        try {

            hotelService.addHotel(hotel);

            return new ResponseResult<>()
                    .setDescription("插入成功")
                    .setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>()
                    .setDescription("插入失败")
                    .setError();
        }
    }


    /**
     * 删除-酒店
     *
     * @return
     */
    @PostMapping("/deleteHotel")
    public Map<String, Object> deleteHotel(@RequestParam("id") String id) {

        try {
            hotelService.deleteHotel(id);

            return new ResponseResult<>()
                    .setDescription("删除成功")
                    .setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>()
                    .setDescription("插入失败")
                    .setError();
        }
    }

}
