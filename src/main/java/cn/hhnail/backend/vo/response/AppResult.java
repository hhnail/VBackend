package cn.hhnail.backend.vo.response;

import cn.hhnail.backend.enums.ResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回数据结构
 * @Author:芋道源码
 * https://mp.weixin.qq.com/s/R8zKtNosTaxuYSR_TOJd-g
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppResult<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> AppResult<T> success(T data) {
        return new AppResult<>(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> AppResult<T> success(String message, T data) {
        return new AppResult<>(ResponseCodeEnum.SUCCESS.getCode(), message, data);
    }

    public static AppResult<?> failed() {
        return new AppResult<>(ResponseCodeEnum.FAIL.getCode(), ResponseCodeEnum.FAIL.getMsg(), null);
    }

    public static AppResult<?> failed(String message) {
        return new AppResult<>(ResponseCodeEnum.FAIL.getCode(), message, null);
    }

    public static AppResult<?> failed(IResult errorResult) {
        return new AppResult<>(errorResult.getCode(), errorResult.getMessage(), null);
    }

    public static <T> AppResult<T> instance(Integer code, String message, T data) {
        AppResult<T> result = new AppResult<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
