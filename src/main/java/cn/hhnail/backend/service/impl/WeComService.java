package cn.hhnail.backend.service.impl;


import cn.hhnail.backend.service.IWeComService;
import cn.hhnail.backend.vo.response.ResponseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author r221587
 * @version 1.0
 * @description: 企微相关服务
 * @date 2023/3/11 13:34
 */
public class WeComService implements IWeComService {

    private final static String PREFIX_QW_TOKNE = "wx-qw-token:";

    private final static String PREFIX_JSTICKET_MS = "prefix-wx-jsticket-ms:";


    private ResponseEntity<Map> doPost(String url, Map<String, Object> param) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode params = objectMapper.createObjectNode();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            params.put(entry.getKey(), entry.getValue() == null ? null : entry.getValue().toString());
        }
        org.springframework.http.HttpEntity<String> request = new org.springframework.http.HttpEntity<String>(params.toString(), headers);
        ResponseEntity<Map> forEntity = new RestTemplate().postForEntity(url, request, Map.class);
        return forEntity;
    }

    @Override
    public Map<String, Object> getAccessToken(Map<String, String> param) {
        try {
            String corpid = "wwf135267502fb31ca";
            String corpsecret = "wxXVL8-68CKXgWyPhgUTJoISWneHHdJTPBFuq1mENf4";
            String accessToken = null;
            // accessToken = (String) redisUtil.get(PREFIX_QW_TOKNE + corpid);
            if (StringUtils.isEmpty(accessToken)) {
                ResponseEntity<Map> forEntity = new RestTemplate().getForEntity("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret, Map.class);
                Map body = forEntity.getBody();
                if (body.get("errcode") != null && Integer.parseInt(body.get("errcode").toString()) != 0) {
                    //调用失败
                    throw new Exception("调用token失败，错误码{}，错误信息，{}" + body.get("errcode") + body.get("errmsg"));
                }

                accessToken = body.get("access_token").toString();
                // redisUtil.set(PREFIX_QW_TOKNE + corpid, accessToken, 6700L);
            }
            return new ResponseResult<String>()
                    .setData(accessToken)
                    .setDescription("查询成功")
                    .setSuccess();
        } catch (Exception e) {
            return new ResponseResult<String>()
                    .setDescription("查询失败，" + e.getMessage())
                    .setError();
        }
    }

    @Override
    public Map<String, Object> getUserIdByMobile(Map<String, String> param) {
        try {

            Object accessToken = getAccessToken(null).get("data");
            String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserid?access_token=" + accessToken;
            ResponseEntity<Map> forEntity = doPost(url, new HashMap<String, Object>() {{
                put("mobile", param.get("mobile"));
            }});
            Map body = forEntity.getBody();
            if (body.get("errcode") != null && Integer.parseInt(body.get("errcode").toString()) != 0) {
                throw new Exception(String.format("调用失败，错误码%s，错误信息，%s", body.get("errcode"), body.get("errmsg")));
            }

            return new ResponseResult<String>()
                    .setData(body.get("userid").toString())
                    .setDescription("查询成功")
                    .setSuccess();
        } catch (Exception e) {
            return new ResponseResult<String>()
                    .setDescription("查询失败，" + e.getMessage())
                    .setError();
        }
    }


    @Override
    public Map<String, Object> deleteUser(Map<String, Object> param) {
        try {

            Object accessToken = getAccessToken(null).get("data");

            String url = "https://qyapi.weixin.qq.com/cgi-bin/user/delete"
                    + "?access_token=" + accessToken
                    + "&userid=" + param.get("userid");
            ResponseEntity<Map> forEntity = doPost(url, null);
            Map body = forEntity.getBody();
            if (body.get("errcode") != null && Integer.parseInt(body.get("errcode").toString()) != 0) {
                throw new Exception(String.format("调用失败，错误码%s，错误信息，%s", body.get("errcode"), body.get("errmsg")));
            }

            return new ResponseResult<>()
                    .setData(param.get("userid"))
                    .setDescription("删除成功")
                    .setSuccess();
        } catch (Exception e) {
            return new ResponseResult<String>()
                    .setDescription("删除失败，" + e.getMessage())
                    .setError();
        }
    }


    @Override
    public Map<String, Object> addUser(Map<String, Object> param) {
        try {

            Object accessToken = getAccessToken(null).get("data");

            String url = "https://qyapi.weixin.qq.com/cgi-bin/user/delete"
                    + "?access_token=" + accessToken
                    + "&userid=" + param.get("userid");
            ResponseEntity<Map> forEntity = doPost(url, param);
            Map body = forEntity.getBody();
            if (body.get("errcode") != null && Integer.parseInt(body.get("errcode").toString()) != 0) {
                throw new Exception(String.format("调用失败，错误码%s，错误信息，%s", body.get("errcode"), body.get("errmsg")));
            }

            return new ResponseResult<>()
                    .setData(param.get("userid"))
                    .setDescription("创建成功")
                    .setSuccess();
        } catch (Exception e) {
            return new ResponseResult<String>()
                    .setDescription("创建失败，" + e.getMessage())
                    .setError();
        }
    }


}
