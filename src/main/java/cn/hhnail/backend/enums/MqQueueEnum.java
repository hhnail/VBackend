package cn.hhnail.backend.enums;

/**
 * MQ队列枚举
 */
public enum MqQueueEnum {

    HOTEL_INSERT_QUEUE("hotel.insert.queue"),
    HOTEL_DELETE_QUEUE("hotel.delete.queue"),
    ;

    private String code;

    MqQueueEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
