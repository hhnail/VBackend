package cn.hhnail.backend.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class TranslateResult implements Serializable {

    private List<_TranslateResult> trans_result = new ArrayList<>();

    private String from;

    private String to;

    @Data
    @ToString
    public class _TranslateResult{
        private String dst;

        private String src;
    }

}




