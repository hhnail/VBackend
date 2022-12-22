package cn.hhnail.backend.vo.response;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hhnail
 * @version 1.0
 * @description: 统一返回结果
 * @date 2022/12/12 16:39
 */
public class ResponseResult<T>{

    private Map<String, Object> result;

    public ResponseResult() {
        result = new HashMap<>();
    }

    public Map<String, Object> setSuccess() {
        result.put("ok", true);
        return result;
    }

    public Map<String, Object> setError() {
        result.put("ok", false);
        return result;
    }

    public ResponseResult<T> setDescription(String description) {
        result.put("desc", description);
        return this;
    }

    public ResponseResult<T> setData(T data) {
        result.put("data", data);
        return this;
    }


}
