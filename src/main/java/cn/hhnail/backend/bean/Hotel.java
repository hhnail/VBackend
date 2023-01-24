package cn.hhnail.backend.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Hhnail
 * @version 1.0
 * @description: 酒店类（测试ES）
 * @date 2023/1/24 10:21
 */
@Data
@ToString
public class Hotel implements Serializable {

    private Long id;
    // 酒店名称
    private String name;
    // 酒店地址
    private String address;
    // '酒店价格'
    private Integer price;
    // '酒店评分'
    private Integer score;
    // '酒店品牌'
    private String brand;
    // '所在城市'
    private String city;
    // '酒店星级，1星到5星，1钻到5钻'
    private String starName;
    // '商圈'
    private String business;
    // '纬度'
    private String latitude;
    // '经度'
    private String longitude;
    // '酒店图片'
    private String pic;
}
