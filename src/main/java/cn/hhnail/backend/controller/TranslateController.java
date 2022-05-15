package cn.hhnail.backend.controller;

import cn.hhnail.backend.enums.Languages;
import cn.hhnail.backend.util.TranslateUtil;
import cn.hhnail.backend.vo.response.AppResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vapi")
public class TranslateController {

    @PostMapping(value = "/translate")
    public AppResponse<String> translate(@RequestParam("") String chineseStr) {
      try{
          String result = TranslateUtil.translate(chineseStr, Languages.CHINESE, Languages.ENGLISH);
          return AppResponse.ok(result);
      }catch (Exception e){
          e.printStackTrace();
          AppResponse<String> failResponse = AppResponse.fail(null);
          return failResponse;
      }
    }
}
