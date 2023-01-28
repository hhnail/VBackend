package cn.hhnail.backend.enums;

/**
 * 枚举
 * MQ RoutingKey
 */
public enum MqRoutingKey {

    HOTEL_INSERT_KEY("hotel.insert"),
    HOTEL_DELETE_KEY("hotel.delete"),
    ;

    private String code;

    MqRoutingKey(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
