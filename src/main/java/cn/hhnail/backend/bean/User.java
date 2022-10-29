package cn.hhnail.backend.bean;

import lombok.Data;
import lombok.ToString;

/**
 * 用户。
 * 考虑到使用某个系统的（例如ehr），也不一定是员工（Staff），还是用User比较好
 */
@Data
@ToString
public class User {

    private String userName;
    private String password;
    private String token;

}
