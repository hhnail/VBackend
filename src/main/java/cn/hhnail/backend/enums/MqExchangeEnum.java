package cn.hhnail.backend.enums;

/**
 * MQ交换机枚举
 */
public enum MqExchangeEnum {

    HOTEL_EXCHANGE("hotel.topic");

    private String code;

    MqExchangeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
