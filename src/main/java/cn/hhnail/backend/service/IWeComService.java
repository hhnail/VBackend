package cn.hhnail.backend.service;

import java.util.Map;

public interface IWeComService{

    /**
     * 获取访问令牌
     *
     * @param param
     * @return
     * @throws Exception
     */
    Map<String, Object> getAccessToken(Map<String, String> param);

    /**
     * 依据手机号获取企微userid
     *
     * @param param
     * @return
     * @throws Exception
     */
    Map<String, Object> getUserIdByMobile(Map<String, String> param);

    /**
     * 删除user
     *
     * @param param
     * @return
     * @throws Exception
     */
    Map<String, Object> deleteUser(Map<String, Object> param);

    /**
     * 创建user
     *
     * @param param
     * @return
     * @throws Exception
     */
    Map<String, Object> addUser(Map<String, Object> param);
}
