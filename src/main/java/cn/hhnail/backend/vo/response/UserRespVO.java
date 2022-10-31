package cn.hhnail.backend.vo.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRespVO {

        private String userName;
        private String password;
        private String token;

}
