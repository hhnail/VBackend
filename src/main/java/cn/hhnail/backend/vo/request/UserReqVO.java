package cn.hhnail.backend.vo.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserReqVO {

    private String userName;
    private String password;
    private String token;


}
