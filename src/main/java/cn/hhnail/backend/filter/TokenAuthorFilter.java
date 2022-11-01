package cn.hhnail.backend.filter;


import cn.hhnail.backend.enums.ResponseCodeEnum;
import cn.hhnail.backend.util.EncryptUtil;
import cn.hhnail.backend.util.VStringUtil;
import cn.hhnail.backend.vo.response.AppResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


/**
 * token验证拦截
 */
@Component
@Slf4j
public class TokenAuthorFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        // 设置允许跨域的配置
        // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
        resp.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的访问方法
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "token,Origin, X-Requested-With, Content-Type, Accept");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");


        AppResponse<String> respVO = new AppResponse<>();
        String token = VStringUtil.isEmpty(req.getHeader("token"))
                ? req.getParameter("token")
                : req.getHeader("token");

        if (VStringUtil.isEmpty(token)) {
            respVO.setCode(ResponseCodeEnum.PARAM_INVAILD.getCode());
            respVO.setMsg("token不得为空");
        }

        if (EncryptUtil.checkJwtToken(token)) {
            respVO.setCode(ResponseCodeEnum.SUCCESS.getCode());
            respVO.setMsg("token验证通过");
        } else {
            respVO.setCode(ResponseCodeEnum.FAIL.getCode());
            respVO.setMsg("token验证失败");
        }


        // 通过验证便放行
        if (ResponseCodeEnum.SUCCESS.getCode().equals(respVO.getCode())) {
            chain.doFilter(request, response);
        } else {
            OutputStreamWriter osw = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
            PrintWriter writer = new PrintWriter(osw, true);
            writer.write(JSON.toJSONString(respVO));
            writer.flush();
            writer.close();
            osw.close();
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}


